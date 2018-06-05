package com.chow.dao.mongo;

import com.chow.model.*;
import com.chow.model.mongoPo.MShopSaleDetail;
import com.chow.model.mongoPo.McheckTicket;
import com.chow.model.mongoPo.MticketSaleDetail;

import java.util.Map;

public interface DataHandleDao {
    //总收入注入，分为售票收入和商品销售收入
    int saveAllSale() throws Exception;
    //检票注入
    int saveCheckTicket() throws Exception;
    //批次注入
    int saveBatchInfo();
    //分析统计检票数据
    void AnalyzeData4CheckTicket();
    //分析统计销售收入数据
    void AnalyzeData4ShopSale();

    //清空所有表
    void clearData();
}
