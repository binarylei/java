import com.herolei.bean.Region;
import com.herolei.mongodb.serviceImpl.RegionService;
import com.herolei.mongodb.serviceImpl.RegionServiceMongo;
import com.herolei.mybatis.serviceImpl.RegionServiceSql;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-mvc.xml","classpath:mybatis/spring-mybatis.xml","classpath:mongodb/spring-mongodb.xml"})
public class MongodbTest {

    @Autowired
    private RegionServiceSql regionServiceSql;

    @Autowired
    private RegionServiceMongo regionServiceMongo;

    @Autowired
    private RegionService regionService;

    @Test
    public void convertTest() {
        regionService.convert();
    }

    @Test
    public void getAllRegionSqlTest() {
        List<Region> regions = regionServiceSql.getRegion();
        for (Region region : regions) {
            System.out.println(region);
        }
    }

    @Test
    public void getAllRegionMongoTest() {
        List<Region> regions = regionServiceMongo.getAllRegion();
        for(Region region : regions) {
            System.out.println(region);
        }
    }

    @Test
    public void getRegionByIdMongoTest() {
        Region region = regionServiceMongo.getRegionById(100);
        System.out.println(region);
    }

    @Test
    public void getRegionByProvinceMongoTest() {
        List<Region> regions = regionServiceMongo.getRegionByProvince(100);
        for(Region region : regions) {
            System.out.println(region);
        }
    }

    @Test
    public void updateRegionMongoTest() {
        int row = regionServiceMongo.updateRegionById(100);
        System.out.println(row);
        System.out.println(regionServiceMongo.getRegionById(100));
    }

    @Test
    public void removeRegionByIdMongoTest() {
        int id = 102;
        System.out.println(regionServiceMongo.getRegionById(id));
        regionServiceMongo.removeRegionById(id);
        System.out.println(regionServiceMongo.getRegionById(id));
    }

    @Test
    public void removeRegionAllMongoTest() {
        System.out.println(regionServiceMongo.getRegionCount());
        regionServiceMongo.removeRegionAll();
        System.out.println(regionServiceMongo.getRegionCount());
    }
}
