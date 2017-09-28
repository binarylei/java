package com.herolei.mybatis.serviceImpl;

import com.herolei.bean.Region;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceSql {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Region> getRegion() {
        return sqlSessionTemplate.selectList("getRegion");
    }
}
