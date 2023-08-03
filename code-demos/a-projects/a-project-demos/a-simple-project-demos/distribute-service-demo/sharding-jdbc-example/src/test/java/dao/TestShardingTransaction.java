package dao;

import com.itbaizhan.RunBoot;
import com.itbaizhan.entity.Position;
import com.itbaizhan.entity.PositionDetail;
import com.itbaizhan.repository.PositionDetailRepository;
import com.itbaizhan.repository.PositionRepository;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestShardingTransaction {

    @Resource
    private PositionRepository positionRepository;

    @Resource
    private PositionDetailRepository positionDetailRepository;


    @Test
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void testAdd2(){
//        TransactionTypeHolder.set(TransactionType.XA);
        for (int i = 0; i < 20; i++) {
            Position position = new Position();
            position.setName("itbaizhan" + i);
            position.setSalary(20000d);
            position.setCity("beijing");
            positionRepository.save(position);

            if(i==3){
                throw new RuntimeException("人为制造的异常");
            }

            //保存position_detail表的数据
            PositionDetail positionDetail = new PositionDetail();
            positionDetail.setPid(position.getId());
            positionDetail.setDescription("this is a message.." + i);
            positionDetailRepository.save(positionDetail);
        }
    }
}
