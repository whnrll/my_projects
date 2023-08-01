package com.itbaizhan.idempotentdemo2.controller;

import com.itbaizhan.idempotentdemo2.config.ApiIdempotentAnn;
import com.itbaizhan.idempotentdemo2.entity.User;
import com.itbaizhan.idempotentdemo2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itbaizhan
 * @since 06-07
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 跳转到首页
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(){
        // 查询所有用户
        List<User> users = iUserService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("users",users);
        return modelAndView;
    }

    /**
     * 跳转注册页面
     * @return
     */
    @GetMapping("/toadduser")
    public ModelAndView  toadduser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add");
        // 生成Token令牌
        String token = UUID.randomUUID().toString().replace("-", "");
        // 保存token 到redis
        stringRedisTemplate.opsForValue().set(token,Thread.currentThread().getId()+"");
        modelAndView.addObject("token",token);
        return modelAndView;
    }


    /**
     * 注册用户
     * @return
     */
    @ApiIdempotentAnn
    @PostMapping("/create")
    public String  createUser(String name,Integer age) throws InterruptedException {
        Thread.sleep(3000);
        Integer integer = iUserService.create(name, age);
        if (integer >= 1){
            return "redirect:/user/index";
        }
        return "add";
    }


    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return
     */
    @GetMapping("/findById")
    public ModelAndView getUser(Long id){
        User byId = iUserService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        modelAndView.addObject("user",byId);
        return modelAndView;
    }


    /**
     * 更新
     * @param user
     * @return
     */
    @PostMapping("/update")
    public String update(User user) throws InterruptedException {
        Thread.sleep(3000);
        Integer update = iUserService.updateAge(user);
        if (update >= 1){
            return "redirect:/user/index";
        }
        return "update";
    }
}
