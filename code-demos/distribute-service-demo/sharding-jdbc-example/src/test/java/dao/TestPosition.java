package dao;

import com.itbaizhan.RunBoot;
import com.itbaizhan.entity.City;
import com.itbaizhan.entity.Position;
import com.itbaizhan.entity.PositionDetail;
import com.itbaizhan.repository.CityRepository;
import com.itbaizhan.repository.PositionDetailRepository;
import com.itbaizhan.repository.PositionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestPosition {

    @Resource
    private PositionRepository positionRepository;

    @Resource
    private PositionDetailRepository positionDetailRepository;

    @Resource
    private CityRepository cityRepository;

    @Test
    public void testAdd(){
        for (int i = 0; i < 20; i++) {
            Position position = new Position();
//            position.setId((long)i);
            position.setName("itbaizhan" + i);
            position.setSalary(20000d);
            position.setCity("beijing");
            positionRepository.save(position);
        }
    }

    @Test
    public void testAdd2(){
        for (int i = 0; i < 20; i++) {
            Position position = new Position();
            position.setName("itbaizhan" + i);
            position.setSalary(20000d);
            position.setCity("beijing");
            positionRepository.save(position);

            //保存position_detail表的数据
            PositionDetail positionDetail = new PositionDetail();
            positionDetail.setPid(position.getId());
            positionDetail.setDescription("this is a message.." + i);
            positionDetailRepository.save(positionDetail);
        }
    }

    @Test
    public void testQuery(){
        Object object = positionRepository.findPositionById(707985538270887936l);
        Object[] position = (Object[]) object;
        System.out.println(position[0] + " " + position[1] + " " + position[2] + " " +
                position[3] + " " + position[4]);
    }

    @Test
    public void testBroadCast(){
        City city = new City();
        city.setName("beijing");
        city.setProvince("beijing");
        cityRepository.save(city);
    }
}
