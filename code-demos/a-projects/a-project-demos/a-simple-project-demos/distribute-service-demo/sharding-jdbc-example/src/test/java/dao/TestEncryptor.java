package dao;

import com.itbaizhan.RunBoot;
import com.itbaizhan.entity.CUser;
import com.itbaizhan.repository.CUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestEncryptor {

    @Resource
    private CUserRepository cUserRepository;

    @Test
    public void testAdd(){
        CUser cUser = new CUser();
        cUser.setName("tiger");
        cUser.setPwd("abc");
        cUserRepository.save(cUser);
    }

    @Test
    public void testFind(){
        List<CUser> list = cUserRepository.findByPwd("abc");
        list.forEach(cUser -> {
            System.out.println(cUser.getId() + " " + cUser.getName() + " " + cUser.getPwd());
        });

    }
}
