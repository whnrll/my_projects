package dao;

import com.itbaizhan.RunBoot;
import com.itbaizhan.entity.City;
import com.itbaizhan.repository.CityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestMasterSlave {

    @Resource
    private CityRepository cityRepository;

    @Test
    public void testAdd(){
        City city = new City();
        city.setName("shanghai");
        city.setProvince("shanghai");
        cityRepository.save(city);
    }

    @Test
    public void testFind(){
        List<City> all = cityRepository.findAll();
        all.forEach(city->{
            System.out.println(city.getId() + " " + city.getName() + " " + city.getProvince());
        });
    }
}
