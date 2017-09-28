package com.herolei.mongodb.serviceImpl;

import com.herolei.bean.Region;
import com.herolei.mybatis.serviceImpl.RegionServiceSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    RegionServiceSql regionServiceSql;

    @Autowired
    RegionServiceMongo regionServiceMongo;

    public void convert() {
        List<Region> regions = regionServiceSql.getRegion();
        regionServiceMongo.addRegion(regions);
    }
}
