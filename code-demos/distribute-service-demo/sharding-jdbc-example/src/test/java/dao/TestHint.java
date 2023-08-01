package dao;

import com.itbaizhan.RunBoot;
import com.itbaizhan.entity.City;
import com.itbaizhan.repository.CityRepository;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestHint {

    @Resource
    private CityRepository cityRepository;

    @Test
    public void test1(){
        HintManager hintManager = HintManager.getInstance();
        hintManager.setDatabaseShardingValue(1l);    //强制路由到ds${xx%2}
        List<City> cityList = cityRepository.findAll();
        cityList.forEach(city -> {
            System.out.println(city.getId() + " " + city.getName() + " " + city.getProvince());
        });

    }
}
