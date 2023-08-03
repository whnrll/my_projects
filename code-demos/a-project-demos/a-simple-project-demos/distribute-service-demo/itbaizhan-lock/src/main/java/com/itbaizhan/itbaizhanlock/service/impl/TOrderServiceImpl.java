package com.itbaizhan.itbaizhanlock.service.impl;

import com.itbaizhan.itbaizhanlock.entity.OrderItem;
import com.itbaizhan.itbaizhanlock.entity.Product;
import com.itbaizhan.itbaizhanlock.entity.TOrder;
import com.itbaizhan.itbaizhanlock.mapper.OrderItemMapper;
import com.itbaizhan.itbaizhanlock.mapper.ProductMapper;
import com.itbaizhan.itbaizhanlock.mapper.TOrderMapper;
import com.itbaizhan.itbaizhanlock.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itbaizhan.itbaizhanlock.utils.DistributedRedisLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 05-26
 */
@Slf4j
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

    @Resource
    ProductMapper productMapper;

    @Resource
    OrderItemMapper orderItemMapper;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private DistributedRedisLock distributedRedisLock;

    @Autowired
    private CuratorFramework curatorFramework;


    /**
     * 创建订单
     *
     * @param produceId   商品id
     * @param purchaseNum 购买数量
     * @return
     */
    @Override
    public synchronized String createOrder(Integer produceId, Integer purchaseNum) {
        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        // 1、根据商品id获取商品信息
        Product product = productMapper.selectById(produceId);
        // 2、判断商品是否存在
        if (product == null) {
            platformTransactionManager.rollback(transaction);
            throw new RuntimeException("购买商品不存在");
        }
        log.info(Thread.currentThread().getName() + "库存数量" + product.getCount());
        // 3、校验库存
        if (purchaseNum > product.getCount()) {
            platformTransactionManager.rollback(transaction);
            throw new RuntimeException("库存不足");
        }
        // 4、更新库存操作
        int count = product.getCount() - purchaseNum;
        product.setCount(count);
        productMapper.updateById(product);
        // 5、创建订单
        TOrder order = new TOrder();
        order.setOrderStatus(1);//订单状态
        order.setReceiverName("张三");
        order.setReceiverMobile("18587781058");
        order.setOrderAmount(product.getPrice().multiply(new BigDecimal(purchaseNum)));//订单价格
        baseMapper.insert(order);
        // 6、创建订单和商品关联
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());//订单id
        orderItem.setProduceId(product.getId());// 商品id
        orderItem.setPurchasePrice(product.getPrice());// 购买价格
        orderItem.setPurchaseNum(purchaseNum);// 购买数量
        orderItemMapper.insert(orderItem);
        //提交事务
        platformTransactionManager.commit(transaction);
        return order.getId();
    }

    /**
     * 创建订单 悲观锁
     *
     * @param produceId
     * @param purchaseNum
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createOrderPessimisticlock(Integer produceId, Integer purchaseNum) {
        // 1、根据商品id获取商品信息
        Product product = productMapper.findById(produceId);
        // 2、判断商品是否存在
        if (product == null) {
            throw new RuntimeException("购买商品不存在");
        }
        log.info(Thread.currentThread().getName() + "库存数量" + product.getCount());
        // 3、校验库存
        if (purchaseNum > product.getCount()) {
            throw new RuntimeException("库存不足");
        }
        // 4、更新库存操作
        int count = product.getCount() - purchaseNum;
        product.setCount(count);
        productMapper.updateById(product);
        // 5、创建订单
        TOrder order = new TOrder();
        order.setOrderStatus(1);//订单状态
        order.setReceiverName("张三");
        order.setReceiverMobile("18587781058");
        order.setOrderAmount(product.getPrice().multiply(new BigDecimal(purchaseNum)));//订单价格
        baseMapper.insert(order);
        // 6、创建订单和商品关联
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());//订单id
        orderItem.setProduceId(product.getId());// 商品id
        orderItem.setPurchasePrice(product.getPrice());// 购买价格
        orderItem.setPurchaseNum(purchaseNum);// 购买数量
        orderItemMapper.insert(orderItem);
        return order.getId();
    }

    /**
     * 创建订单 乐观锁
     *
     * @param produceId
     * @param purchaseNum
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createOrderOptimisticlock(Integer produceId, Integer purchaseNum) {
        int retryCount = 0; // 重试次数
        int update = 0; // 更新结果
        // 1、根据商品id获取商品信息
        Product product = productMapper.selectById(produceId);
        // 2、判断商品是否存在
        if (product == null) {
            throw new RuntimeException("购买商品不存在");
        }
        log.info(Thread.currentThread().getName() + "库存数量" + product.getCount());
        // 3、校验库存
        if (purchaseNum > product.getCount()) {
            throw new RuntimeException("库存不足");
        }


        /**
         * 乐观锁更新库存
         * 更新失败 说明其他线程已经修改过数据，本地扣减库存失败了。 可以重试
         * 最多重试3次
         */

        while (retryCount < 3 && update == 0) {
            // 更新操作
            update = this.reduceCount(produceId, purchaseNum);
            retryCount++;
        }
        if (update == 0) {
            throw new RuntimeException("库存不够");
        }
        // 4、创建订单
        TOrder order = new TOrder();
        order.setOrderStatus(1);//订单状态
        order.setReceiverName("张三");
        order.setReceiverMobile("18587781058");
        order.setOrderAmount(product.getPrice().multiply(new BigDecimal(purchaseNum)));//订单价格
        baseMapper.insert(order);
        // 5、创建订单和商品关联
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());//订单id
        orderItem.setProduceId(product.getId());// 商品id
        orderItem.setPurchasePrice(product.getPrice());// 购买价格
        orderItem.setPurchaseNum(purchaseNum);// 购买数量
        orderItemMapper.insert(orderItem);
        return order.getId();
    }


    /**
     * 创建订单
     *
     * @param produceId
     * @param purchaseNum
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createOrderRedis(Integer produceId, Integer purchaseNum) {

        // 1、获得锁  setnx  key   value   time   type
        String key = "lock:";
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(key + produceId, Thread.currentThread().getId() + "", 30, TimeUnit.SECONDS);
        // 2、判断是否能获取锁
        if (!result) {
            return "不允许重复下单";
        }
        try {
            // 1、根据商品id获取商品信息
            Product product = productMapper.selectById(produceId);
            // 2、判断商品是否存在
            if (product == null) {
                throw new RuntimeException("购买商品不存在");
            }
            log.info(Thread.currentThread().getName() + "库存数量" + product.getCount());
            // 3、校验库存
            if (purchaseNum > product.getCount()) {
                throw new RuntimeException("库存不足");
            }
            // 4、更新库存操作
            int count = product.getCount() - purchaseNum;
            product.setCount(count);
            productMapper.updateById(product);
            // 5、创建订单
            TOrder order = new TOrder();
            order.setOrderStatus(1);//订单状态
            order.setReceiverName("张三");
            order.setReceiverMobile("18587781058");
            order.setOrderAmount(product.getPrice().multiply(new BigDecimal(purchaseNum)));//订单价格
            baseMapper.insert(order);
            // 6、创建订单和商品关联
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());//订单id
            orderItem.setProduceId(product.getId());// 商品id
            orderItem.setPurchasePrice(product.getPrice());// 购买价格
            orderItem.setPurchaseNum(purchaseNum);// 购买数量
            orderItemMapper.insert(orderItem);
            return order.getId();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 1. 获取锁标识
            String theadIdFlag = stringRedisTemplate.opsForValue().get(key + produceId);
            // 2. 获取到当前线程id
            String id = Thread.currentThread().getId() + "";
            // 3. 判断标识是否一致
            if (id.equals(theadIdFlag)) {
                stringRedisTemplate.delete(key + produceId);
            }
        }
        return "创建失败";
    }


    /**
     * 创建创建
     *
     * @param produceId
     * @param purchaseNum
     * @return
     */
    @Override
    public String createOrderRedission(Integer produceId, Integer purchaseNum) {
        // 1. 加锁
        String key = "lock:";
        Boolean lock = distributedRedisLock.lock(key.concat(produceId + ""));
        // 2. 判断是否获取到锁
        if (!lock) {
            return "失败";
        }

        try {
            // 1、根据商品id获取商品信息
            Product product = productMapper.selectById(produceId);
            // 2、判断商品是否存在
            if (product == null) {
                throw new RuntimeException("购买商品不存在");
            }
            log.info(Thread.currentThread().getName() + "库存数量" + product.getCount());
            // 3、校验库存
            if (purchaseNum > product.getCount()) {
                throw new RuntimeException("库存不足");
            }
            // 4、更新库存操作
            int count = product.getCount() - purchaseNum;
            product.setCount(count);
            productMapper.updateById(product);
            // 5、创建订单
            TOrder order = new TOrder();
            order.setOrderStatus(1);//订单状态
            order.setReceiverName("张三");
            order.setReceiverMobile("18587781058");
            order.setOrderAmount(product.getPrice().multiply(new BigDecimal(purchaseNum)));//订单价格
            baseMapper.insert(order);
            // 6、创建订单和商品关联
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());//订单id
            orderItem.setProduceId(product.getId());// 商品id
            orderItem.setPurchasePrice(product.getPrice());// 购买价格
            orderItem.setPurchaseNum(purchaseNum);// 购买数量
            orderItemMapper.insert(orderItem);
            return order.getId();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            distributedRedisLock.unlock(key.concat(produceId + ""));
        }

        return "失败";
    }

    /**
     * 创建订单
     *
     * @param produceId   商品id
     * @param purchaseNum 购买数量
     * @return
     */
    @Override
    public String createOrderZookeeper(Integer produceId, Integer purchaseNum) throws Exception {
        // InterProcessMutex公平锁
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/lockPath");
        // 尝试获取锁  第一参数 定义时间数字  第二个参数时间单位
        if (lock.acquire(5, TimeUnit.SECONDS)) {
            try {
                // 1、根据商品id获取商品信息
                Product product = productMapper.selectById(produceId);
                // 2、判断商品是否存在
                if (product == null) {
                    throw new RuntimeException("购买商品不存在");
                }
                log.info(Thread.currentThread().getName() + "库存数量" + product.getCount());
                // 3、校验库存
                if (purchaseNum > product.getCount()) {
                    throw new RuntimeException("库存不足");
                }
                // 4、更新库存操作
                int count = product.getCount() - purchaseNum;
                product.setCount(count);
                productMapper.updateById(product);
                // 5、创建订单
                TOrder order = new TOrder();
                order.setOrderStatus(1);//订单状态
                order.setReceiverName("张三");
                order.setReceiverMobile("18587781058");
                order.setOrderAmount(product.getPrice().multiply(new BigDecimal(purchaseNum)));//订单价格
                baseMapper.insert(order);
                // 6、创建订单和商品关联
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(order.getId());//订单id
                orderItem.setProduceId(product.getId());// 商品id
                orderItem.setPurchasePrice(product.getPrice());// 购买价格
                orderItem.setPurchaseNum(purchaseNum);// 购买数量
                orderItemMapper.insert(orderItem);
                return order.getId();
            } finally {
                lock.release();
            }
        }
        return "创建失败";
    }


    /**
     * 减库存
     * mysql 默认事务隔离级别可重复读，会导致在同一个事务里面查询3次productMapper.selectById(produceId);
     * 得到的数据始终都是相同的。 所以我们就提供一个reduceCount 。每次循环都启动一个新的事务扣减库存操作 。
     *
     * @param id    商品id
     * @param count 购买数量
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int reduceCount(int id, int count) {
        int result = 0;
        // 1. 查询商品库存
        Product product = productMapper.selectById(id);
        // 2. 判断库存
        if (product.getCount() >= count) {
            //3. 减库存  乐观锁更新库存
            result = productMapper.updateProduct(product.getId(), count, product.getVersion());

        }
        return result;
    }


}
