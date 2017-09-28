package com.herolei.mongodb.serviceImpl;


import com.herolei.bean.Region;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceMongo {

    @Autowired
    protected MongoTemplate mongoTemplate;

    protected Class<Region> getEntityClass() {
        return Region.class;
    }

    public void addRegion(Region region) {
        mongoTemplate.save(region, "region");
    }

    public void addRegion(List<Region> regions) {
        for (Region region : regions) {
            mongoTemplate.save(region, "region");
        }
    }

    //查找个数 count
    public long getRegionCount() {
        return mongoTemplate.count(new Query(), getEntityClass());
    }

    //查找全部 findAll
    public List<Region> getAllRegion() {
        return mongoTemplate.findAll(getEntityClass());
    }

    //查找一个 findOne
    public Region getRegionById(int id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, getEntityClass());
    }

    //按条件查找 find
    public List<Region> getRegionByProvince(int parent_id) {
        Query query = new Query(Criteria.where("name").is("aaa"));
        return mongoTemplate.find(query, getEntityClass());
    }

    //更新 update
    public int updateRegionById(int id){
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("name", "aaa");
        WriteResult result = mongoTemplate.updateFirst(query, update, getEntityClass());
        return result.getN();
    }

    //删除 按条件
    public void removeRegionById(int id){
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, getEntityClass());
    }

    //删除全部
    public void removeRegionAll(){
        Query query = new Query();
        mongoTemplate.remove(query, getEntityClass());
    }
}
