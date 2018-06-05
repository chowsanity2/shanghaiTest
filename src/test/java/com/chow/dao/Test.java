package com.chow.dao;

import com.chow.dao.mongo.DataHandleDao;
import com.chow.dao.mongo.impl.AbstractBaseMongoTemplete;
import com.chow.model.IncomeReport;
import com.chow.model.mongoPo.MShopSaleDetail;
import com.chow.utils.ConvertUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class Test extends AbstractBaseMongoTemplete {
    @Autowired
    private DataHandleDao dataHandleDao;
    @Autowired
    private IncomeReportMapper incomeReportMapper;
    @org.junit.Test
    public void fun1(){
        dataHandleDao.saveBatchInfo();
    }
    @org.junit.Test
    public void fun2(){
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("4");

        Criteria c = Criteria.where("layoutId").in(list);
        Query query = new Query();
        query.addCriteria(c);
        List<MShopSaleDetail> saleDetailList = mongoTemplate.find(query, MShopSaleDetail.class);
        System.out.println(saleDetailList.size());
        for (MShopSaleDetail m:saleDetailList) {
            System.out.println();

        }
    }

    @org.junit.Test
    public void fun3() throws  Exception{
        String str = "2018-02-03 18:00:00";
        Date date = ConvertUtils.convertStr2Date(str);
        System.out.println(date);

    }

    @org.junit.Test
    public void injectSale() throws  Exception{
        dataHandleDao.saveAllSale();
    }

   @org.junit.Test
    public void clearData() throws  Exception{
        dataHandleDao.clearData();
    }

    @org.junit.Test
    public void fun6() throws  Exception{
        dataHandleDao.saveCheckTicket();
    }

    @org.junit.Test
    public void injectTicket() throws  Exception{
        dataHandleDao.saveCheckTicket();
    }

    @org.junit.Test
    public void inject() throws  Exception{
        dataHandleDao.saveAllSale();
        dataHandleDao.saveCheckTicket();
    }

    @org.junit.Test
    public void fun10() throws  Exception{
        List<IncomeReport> test = incomeReportMapper.test();
        System.out.println(test);
    }


}
