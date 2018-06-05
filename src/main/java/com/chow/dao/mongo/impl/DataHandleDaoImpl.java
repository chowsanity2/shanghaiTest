package com.chow.dao.mongo.impl;

import com.alibaba.fastjson.JSONArray;
import com.chow.dao.*;
import com.chow.dao.mongo.DataHandleDao;
import com.chow.model.*;
import com.chow.model.mongoPo.MShopSaleDataSeq;
import com.chow.model.mongoPo.MShopSaleDetail;
import com.chow.model.mongoPo.McheckTicket;
import com.chow.model.mongoPo.MticketSaleDetail;
import com.chow.model.vo.Con4Budget;
import com.chow.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.util.*;

/**
 * 拉取数据
 * "0:失败 1:成功"
 */
public class DataHandleDaoImpl extends AbstractBaseMongoTemplete implements DataHandleDao {
    @Autowired
    private BatchMapper batchMapper;
    @Autowired
    private LayoutMapper layoutMapper;
    @Autowired
    private TicketSaleMapper ticketSaleMapper;
    @Autowired
    private BudgetMapper budgetMapper;
    @Autowired
    private ShopSaleMapper shopSaleMapper;
    @Autowired
    private IncomeReportMapper incomeReportMapper;
    @Autowired
    private  TicketReportMapper ticketReportMapper;
    @Autowired
    private CheckTicketMapper checkTicketMapper;
    @Override
    public int saveAllSale() throws Exception{
        //准备存储售票和商品销售信息存放的容器，每条不重复数据会分为3*4份注入到报表
        //根据Project_Level划分
        Map<String,Map<String,IncomeReport>> layoutSaleMap = new HashMap<>();
        Map<String,Map<String,IncomeReport>> projectSaleMap = new HashMap<>();
        Map<String,Map<String,IncomeReport>> buildingSaleMap = new HashMap<>();
        //根据日期划分
        Map<String,IncomeReport> projectSaleMap4Date = new HashMap<>();
        Map<String,IncomeReport> layoutSaleMap4Date = new HashMap<>();
        Map<String,IncomeReport> buildingSaleMap4Date = new HashMap<>();


        //获取批次表layout为3信息
        List<String> batchNumbers = batchMapper.findBatchByLayoutId(3);
        Query query = new Query();
        Criteria criteria = Criteria.where("seqId").in(batchNumbers);
        query.addCriteria(criteria);
        List<MticketSaleDetail> mtList = mongoTemplate.find(query, MticketSaleDetail.class);
        for (MticketSaleDetail mticketSaleDetail:mtList) {
            String data = mticketSaleDetail.getData();
            //json转集合
            List<Map<String,Object>> dataList = (List<Map<String,Object>>)JSONArray.parse(data);
            for (Map<String,Object> map:dataList) {
                TicketSale ticketSale = new TicketSale();
                ticketSale.setProjectId((String)map.get("PROJECT_ID"));
                ticketSale.setProjectName((String)map.get("PROJECT_NAME"));
                ticketSale.setDealId(String.valueOf((int)map.get("DEAL_ID")));
//                ticketSale.setDealType((String)map.get("DEAL_TYPE"));
                ticketSale.setAgencyName((String) map.get("AGENCY_NAME"));
                ticketSale.setTerminalName((String)map.get("TERMINAL_NAME"));
                ticketSale.setSaleDate(((String)map.get("SALE_DATE")));
                ticketSale.setLayoutId(Integer.valueOf(mticketSaleDetail.getLayoutId()));
                Layout layout = layoutMapper.selectByPrimaryKey(Integer.valueOf(mticketSaleDetail.getLayoutId()));
                ticketSale.setLayoutName(layout.getLayoutName());
                ticketSale.setTicketPrice((BigDecimal) map.get("TICKET_PRICE"));
                ticketSale.setTicketCount((int)map.get("TICKET_COUNT"));
                ticketSale.setTicketAmount(((BigDecimal)map.get("TICKET_AMOUNT")));
                ticketSale.setDiscount((String)map.get("DISCOUNT"));
                ticketSale.setTicketPeople((int)map.get("TICKET_PEOPLE"));
                ticketSale.setEmpName((String)map.get("EMP_NAME"));
                ticketSale.setCollectTerminalName((String)map.get("COLLECT_TERMINAL_NAME"));
                ticketSale.setBarcode((String)map.get("BARCODE"));
                ticketSale.setEmpName((String)map.get("EMP_NAME"));
                ticketSale.setTicketCode((String)map.get("TICKET_CODE"));
                ticketSale.setGzone((String)map.get("GZONE"));
                ticketSale.setTicketType((String)map.get("TICKET_TYPE"));
                ticketSaleMapper.insert(ticketSale);
                //分析数据存入报表
                //将日期分类转换为固定格式
                String dateStr = ticketSale.getSaleDate();
                Date date = ConvertUtils.convertStr2Date(dateStr);
                String yearFormat = ConvertUtils.convertDate2Year(date);
                String quarterFormat = ConvertUtils.convertDate2Quarter(date);
                String monthFormat = ConvertUtils.convertDate2Month(date);
                String dayFormat = ConvertUtils.convertDate2Day(date);

                //准备IncomeReport
                IncomeReport income4year = new IncomeReport();
                IncomeReport income4quarter = new IncomeReport();
                IncomeReport income4month = new IncomeReport();
                IncomeReport income4day = new IncomeReport();

                IncomeReport income4Layoutyear = new IncomeReport();
                IncomeReport income4Layoutquarter = new IncomeReport();
                IncomeReport income4Layoutmonth = new IncomeReport();
                IncomeReport income4Layoutday = new IncomeReport();

                //暂时没数据
                IncomeReport income4Buildyear = new IncomeReport();
                IncomeReport income4Buildquarter = new IncomeReport();
                IncomeReport income4Buildmonth = new IncomeReport();
                IncomeReport income4Buildday = new IncomeReport();

                //根据项目分类 Project_Level为2
                //判断Map中是否存在该项目
                if(projectSaleMap.containsKey(ticketSale.getProjectId())){
                    /*
                    存在，判断日期是否存在,需要累加的两个参数
                    1.该业态销售总额TypeAmount
                    2.售票数量BookingCount
                    */
                    if(projectSaleMap4Date.containsKey(ticketSale.getProjectId()+","+yearFormat)){
                        //System.out.println("==============项目年份存在数据累加");
                        comp(projectSaleMap4Date,ticketSale,yearFormat,2,mticketSaleDetail);
                    }else{
                        //年份不存在，添加
                        //System.out.println("==============项目年份不存在添加");
                        comp2(projectSaleMap4Date,ticketSale,income4year,yearFormat,2,4,mticketSaleDetail);
                    }
                    if(projectSaleMap4Date.containsKey(ticketSale.getProjectId()+","+quarterFormat)){
                        //System.out.println("==============项目季度存在数据累加");
                        //季度存在，累加数据
                        comp(projectSaleMap4Date,ticketSale,quarterFormat,2,mticketSaleDetail);
                    }else{
                        //季度不存在，添加
                        //System.out.println("==============季度不存在添加");
                        comp2(projectSaleMap4Date,ticketSale,income4quarter,quarterFormat,2,3,mticketSaleDetail);
                    }
                    if(projectSaleMap4Date.containsKey(ticketSale.getProjectId()+","+monthFormat)){
                        //System.out.println("==============项目月份存在数据累加");
                        //月份存在，累加数据
                        comp(projectSaleMap4Date,ticketSale,monthFormat,2,mticketSaleDetail);
                    }else{
                        //System.out.println("==============月份不存在添加");
                        //月份不存在，添加
                        comp2(projectSaleMap4Date,ticketSale,income4month,monthFormat,2,2,mticketSaleDetail);
                    }
                    if(projectSaleMap4Date.containsKey(ticketSale.getProjectId()+","+dayFormat)){
                        //System.out.println("==============项目天存在数据累加");
                        //天存在，累加数据
                        comp(projectSaleMap4Date,ticketSale,dayFormat,2,mticketSaleDetail);
                    }else{
                        //System.out.println("==============天不存在添加");
                        //天不存在，添加
                        comp2(projectSaleMap4Date,ticketSale,income4day,dayFormat,2,1,mticketSaleDetail);
                    }
                }else{
                    //System.out.println("==============1初始化数据");
                    //项目不存在，初始化数据
                    comp2(projectSaleMap4Date,ticketSale,income4year,yearFormat,2,4,mticketSaleDetail);
                    comp2(projectSaleMap4Date,ticketSale,income4quarter,quarterFormat,2,3,mticketSaleDetail);
                    comp2(projectSaleMap4Date,ticketSale,income4month,monthFormat,2,2,mticketSaleDetail);
                    comp2(projectSaleMap4Date,ticketSale,income4day,dayFormat,2,1,mticketSaleDetail);

                    //装载
                    projectSaleMap4Date.put(ticketSale.getProjectId()+","+yearFormat,income4year);
                    projectSaleMap4Date.put(ticketSale.getProjectId()+","+quarterFormat,income4quarter);
                    projectSaleMap4Date.put(ticketSale.getProjectId()+","+monthFormat,income4month);
                    projectSaleMap4Date.put(ticketSale.getProjectId()+","+dayFormat,income4day);
                }
                projectSaleMap.put(ticketSale.getProjectId(),null);

                //根据项目分类 Project_Level为1
                //判断Map中是否存在该商铺
                if(buildingSaleMap.containsKey(mticketSaleDetail.getBuildingCode())){
                    /*
                    存在，判断日期是否存在,需要累加的两个参数
                    1.该商铺销售总额TypeAmount
                    2.售票数量BookingCount
                    */
                    if(buildingSaleMap4Date.containsKey(mticketSaleDetail.getBuildingCode()+","+yearFormat)){
                        //System.out.println("==============商铺年份存在数据累加");
                        comp(buildingSaleMap4Date,ticketSale,yearFormat,1,mticketSaleDetail);
                    }else{
                        //年份不存在，添加
                        //System.out.println("==============年份不存在添加");
                        comp2(buildingSaleMap4Date,ticketSale,income4Buildyear,yearFormat,1,4,mticketSaleDetail);
                    }
                    if(buildingSaleMap4Date.containsKey(mticketSaleDetail.getBuildingCode()+","+quarterFormat)){
                        //System.out.println("==============商铺季度存在数据累加");
                        //季度存在，累加数据
                        comp(buildingSaleMap4Date,ticketSale,quarterFormat,1,mticketSaleDetail);
                    }else{
                        //季度不存在，添加
                        //System.out.println("==============季度不存在添加");
                        comp2(buildingSaleMap4Date,ticketSale,income4Buildquarter,quarterFormat,1,3,mticketSaleDetail);
                    }
                    if(buildingSaleMap4Date.containsKey(mticketSaleDetail.getBuildingCode()+","+monthFormat)){
                        //System.out.println("==============商铺月份存在数据累加");
                        //月份存在，累加数据
                        comp(buildingSaleMap4Date,ticketSale,monthFormat,1,mticketSaleDetail);
                    }else{
                        //System.out.println("==============月份不存在添加");
                        //月份不存在，添加
                        comp2(buildingSaleMap4Date,ticketSale,income4Buildmonth,monthFormat,1,2,mticketSaleDetail);
                    }
                    if(buildingSaleMap4Date.containsKey(mticketSaleDetail.getBuildingCode()+","+dayFormat)){
                        //System.out.println("==============商铺天存在数据累加");
                        //天存在，累加数据
                        comp(buildingSaleMap4Date,ticketSale,dayFormat,1,mticketSaleDetail);
                    }else{
                        //System.out.println("==============天不存在添加");
                        //天不存在，添加
                        comp2(buildingSaleMap4Date,ticketSale,income4Buildday,dayFormat,1,1,mticketSaleDetail);
                    }
                }else{
                    //System.out.println("==============2初始化数据");
                    //项目不存在，初始化数据
                    comp2(buildingSaleMap4Date,ticketSale,income4Buildyear,yearFormat,1,4,mticketSaleDetail);
                    comp2(buildingSaleMap4Date,ticketSale,income4Buildquarter,quarterFormat,1,3,mticketSaleDetail);
                    comp2(buildingSaleMap4Date,ticketSale,income4Buildmonth,monthFormat,1,2,mticketSaleDetail);
                    comp2(buildingSaleMap4Date,ticketSale,income4Buildday,dayFormat,1,1,mticketSaleDetail);

                    //装载
                    buildingSaleMap4Date.put(mticketSaleDetail.getBuildingCode()+","+yearFormat,income4Buildyear);
                    buildingSaleMap4Date.put(mticketSaleDetail.getBuildingCode()+","+quarterFormat,income4Buildquarter);
                    buildingSaleMap4Date.put(mticketSaleDetail.getBuildingCode()+","+monthFormat,income4Buildmonth);
                    buildingSaleMap4Date.put(mticketSaleDetail.getBuildingCode()+","+dayFormat,income4Buildday);
                }
                buildingSaleMap.put(mticketSaleDetail.getBuildingCode(),null);

                //根据业态分类 Project_Level为0
                //判断Map中是否存在该业态
                if(layoutSaleMap.containsKey(ticketSale.getLayoutId().toString())){
                    /*
                    存在，判断日期是否存在,需要累加的两个参数
                    1.该业态销售总额TypeAmount
                    2.售票数量BookingCount
                    */
                    if(layoutSaleMap4Date.containsKey(ticketSale.getLayoutId().toString()+","+yearFormat)){
                        //System.out.println("==============业态年份存在数据累加");
                        comp(layoutSaleMap4Date,ticketSale,yearFormat,0,mticketSaleDetail);
                    }else{
                        //年份不存在，添加
                        //System.out.println("==============年份不存在添加");
                        comp2(layoutSaleMap4Date,ticketSale,income4Layoutyear,yearFormat,0,4,mticketSaleDetail);
                    }
                    if(layoutSaleMap4Date.containsKey(ticketSale.getLayoutId().toString()+","+quarterFormat)){
                        //System.out.println("==============业态季度存在数据累加");
                        //季度存在，累加数据
                        comp(layoutSaleMap4Date,ticketSale,quarterFormat,0,mticketSaleDetail);
                    }else{
                        //季度不存在，添加
                        //System.out.println("==============季度不存在添加");
                        comp2(layoutSaleMap4Date,ticketSale,income4Layoutquarter,quarterFormat,0,3,mticketSaleDetail);
                    }
                    if(layoutSaleMap4Date.containsKey(ticketSale.getLayoutId().toString()+","+monthFormat)){
                        //System.out.println("==============业态月份存在数据累加");
                        //月份存在，累加数据
                        comp(layoutSaleMap4Date,ticketSale,monthFormat,0,mticketSaleDetail);
                    }else{
                        //System.out.println("==============月份不存在添加");
                        //月份不存在，添加
                        comp2(layoutSaleMap4Date,ticketSale,income4Layoutmonth,monthFormat,0,2,mticketSaleDetail);
                    }
                    if(layoutSaleMap4Date.containsKey(ticketSale.getLayoutId().toString()+","+dayFormat)){
                        //System.out.println("==============业态天存在数据累加");
                        //天存在，累加数据
                        comp(layoutSaleMap4Date,ticketSale,dayFormat,0,mticketSaleDetail);
                    }else{
                        //System.out.println("==============天不存在添加");
                        //天不存在，添加
                        comp2(layoutSaleMap4Date,ticketSale,income4Layoutday,dayFormat,0,1,mticketSaleDetail);
                    }
                }else{
                    //业态不存在，初始化数据
                    comp2(layoutSaleMap4Date,ticketSale,income4Layoutyear,yearFormat,0,4,mticketSaleDetail);
                    comp2(layoutSaleMap4Date,ticketSale,income4Layoutquarter,quarterFormat,0,3,mticketSaleDetail);
                    comp2(layoutSaleMap4Date,ticketSale,income4Layoutmonth,monthFormat,0,2,mticketSaleDetail);
                    comp2(layoutSaleMap4Date,ticketSale,income4Layoutday,dayFormat,0,1,mticketSaleDetail);
                    //装载
                    layoutSaleMap4Date.put(ticketSale.getLayoutId().toString()+","+yearFormat,income4Layoutyear);
                    layoutSaleMap4Date.put(ticketSale.getLayoutId().toString()+","+quarterFormat,income4Layoutquarter);
                    layoutSaleMap4Date.put(ticketSale.getLayoutId().toString()+","+monthFormat,income4Layoutmonth);
                    layoutSaleMap4Date.put(ticketSale.getLayoutId().toString()+","+dayFormat,income4Layoutday);
                 }
                layoutSaleMap.put(ticketSale.getLayoutId().toString(),null);
            }
        }

        //获取批次表layout不为3和6,10的信息
        batchNumbers = batchMapper.findBatchNotLayoutId(3);
        query = new Query();
        Criteria criteria1 = Criteria.where("seqId").in(batchNumbers);
        query.addCriteria(criteria1);
        List<MShopSaleDetail> mticketSaleDetailList = mongoTemplate.find(query, MShopSaleDetail.class);
        for (MShopSaleDetail mShopSaleDetail:mticketSaleDetailList) {
            String dataList = mShopSaleDetail.getData();
            List<Map<String,Object>> data = (List<Map<String,Object>>)JSONArray.parse(dataList);
            for (Map<String,Object> map:data) {
                ShopSale sale = new ShopSale();
                sale.setProjectId((String) map.get("PROJECT_ID"));
                sale.setProjectName((String)map.get("PROJECT_NAME"));
                sale.setSaleType((String)map.get("SALE_TYPE"));
                sale.setShopId((int)map.get("SHOP_ID"));
                sale.setSalePrice((BigDecimal)map.get("SALE_PRICE"));
                sale.setSaleCount(String.valueOf((BigDecimal)map.get("SALE_COUNT")));
                sale.setSaleDate(ConvertUtils.convertDate((String)map.get("SALE_DATE")));
                sale.setShopPrice((BigDecimal) map.get("SHOP_PRICE"));
                sale.setLayoutId(Integer.parseInt(mShopSaleDetail.getLayoutId()));
                Layout layout = layoutMapper.selectByPrimaryKey(Integer.valueOf(mShopSaleDetail.getLayoutId()));
                sale.setLayoutName(layout.getLayoutName());
                shopSaleMapper.insert(sale);
                //分析数据存入报表
                //将日期分类转换为固定格式
                Date date = sale.getSaleDate();
                String yearFormat = ConvertUtils.convertDate2Year(date);
                String quarterFormat = ConvertUtils.convertDate2Quarter(date);
                String monthFormat = ConvertUtils.convertDate2Month(date);
                String dayFormat = ConvertUtils.convertDate2Day(date);

                //准备IncomeReport
                IncomeReport income4year = new IncomeReport();
                IncomeReport income4quarter = new IncomeReport();
                IncomeReport income4month = new IncomeReport();
                IncomeReport income4day = new IncomeReport();

                IncomeReport income4Layoutyear = new IncomeReport();
                IncomeReport income4Layoutquarter = new IncomeReport();
                IncomeReport income4Layoutmonth = new IncomeReport();
                IncomeReport income4Layoutday = new IncomeReport();

                IncomeReport income4Buildyear = new IncomeReport();
                IncomeReport income4Buildquarter = new IncomeReport();
                IncomeReport income4Buildmonth = new IncomeReport();
                IncomeReport income4Buildday = new IncomeReport();

                //根据业态分类 Project_Level为0
                //判断Map中是否存在该业态
                if(layoutSaleMap.containsKey(sale.getLayoutId().toString())){
                    /*
                    存在，判断日期是否存在,需要累加的一个参数
                    1.该业态销售总额TypeAmount
                    */
                    if(layoutSaleMap4Date.containsKey(sale.getLayoutId().toString()+","+yearFormat)){
                        //System.out.println("==============业态年份存在数据累加");
                        comp3(layoutSaleMap4Date,sale,yearFormat,0,mShopSaleDetail);
                    }else{
                        //年份不存在，添加
                        //System.out.println("==============年份不存在添加");
                        comp4(layoutSaleMap4Date,sale,income4Layoutyear,yearFormat,0,4,mShopSaleDetail);
                    }
                    if(projectSaleMap4Date.containsKey(sale.getLayoutId().toString()+","+quarterFormat)){
                        //System.out.println("==============业态季度存在数据累加");
                        //季度存在，累加数据
                        comp3(layoutSaleMap4Date,sale,quarterFormat,0,mShopSaleDetail);
                    }else{
                        //季度不存在，添加
                        //System.out.println("==============季度不存在添加");
                        comp4(layoutSaleMap4Date,sale,income4Layoutquarter,quarterFormat,0,3,mShopSaleDetail);
                    }
                    if(layoutSaleMap4Date.containsKey(sale.getLayoutId().toString()+","+monthFormat)){
                        //System.out.println("==============业态月份存在数据累加");
                        //月份存在，累加数据
                        comp3(layoutSaleMap4Date,sale,monthFormat,0,mShopSaleDetail);
                    }else{
                        //System.out.println("==============月份不存在添加");
                        //月份不存在，添加
                        comp4(layoutSaleMap4Date,sale,income4Layoutmonth,monthFormat,0,2,mShopSaleDetail);
                    }
                    if(layoutSaleMap4Date.containsKey(sale.getLayoutId().toString()+","+dayFormat)){
                        //System.out.println("==============业态天存在数据累加");
                        //天存在，累加数据
                        comp3(layoutSaleMap4Date,sale,dayFormat,0,mShopSaleDetail);
                    }else{
                        //System.out.println("==============天不存在添加");
                        //天不存在，添加
                        comp4(layoutSaleMap4Date,sale,income4Layoutday,dayFormat,0,1,mShopSaleDetail);
                    }
                }else{
                    //System.out.println("==============2初始化数据");
                    //业态不存在，初始化数据
                    comp4(layoutSaleMap4Date,sale,income4Layoutyear,yearFormat,0,4,mShopSaleDetail);
                    comp4(layoutSaleMap4Date,sale,income4Layoutquarter,quarterFormat,0,3,mShopSaleDetail);
                    comp4(layoutSaleMap4Date,sale,income4Layoutmonth,monthFormat,0,2,mShopSaleDetail);
                    comp4(layoutSaleMap4Date,sale,income4Layoutday,dayFormat,0,1,mShopSaleDetail);
                    //装载
                    layoutSaleMap4Date.put(sale.getLayoutId().toString()+","+yearFormat,income4Layoutyear);
                    layoutSaleMap4Date.put(sale.getLayoutId().toString()+","+quarterFormat,income4Layoutquarter);
                    layoutSaleMap4Date.put(sale.getLayoutId().toString()+","+monthFormat,income4Layoutmonth);
                    layoutSaleMap4Date.put(sale.getLayoutId().toString()+","+dayFormat,income4Layoutday);
                }
                layoutSaleMap.put(sale.getLayoutId().toString(),null);

                //根据商铺分类 Project_Level为1
                //判断Map中是否存在该商铺
                if(layoutSaleMap.containsKey(mShopSaleDetail.getBuildingCode())){
                    /*
                    存在，判断日期是否存在,需要累加的一个参数
                    1.该商铺销售总额TypeAmount
                    */
                    if(buildingSaleMap4Date.containsKey(mShopSaleDetail.getBuildingCode()+","+yearFormat)){
                        //System.out.println("==============商铺年份存在数据累加");
                        comp3(buildingSaleMap4Date,sale,yearFormat,1,mShopSaleDetail);
                    }else{
                        //年份不存在，添加
                        //System.out.println("==============年份不存在添加");
                        comp4(buildingSaleMap4Date,sale,income4Buildyear,yearFormat,1,4,mShopSaleDetail);
                    }
                    if(buildingSaleMap4Date.containsKey(mShopSaleDetail.getBuildingCode()+","+quarterFormat)){
                        //System.out.println("==============商铺季度存在数据累加");
                        //季度存在，累加数据
                        comp3(buildingSaleMap4Date,sale,quarterFormat,1,mShopSaleDetail);
                    }else{
                        //季度不存在，添加
                        //System.out.println("==============季度不存在添加");
                        comp4(buildingSaleMap4Date,sale,income4Buildquarter,quarterFormat,1,3,mShopSaleDetail);
                    }
                    if(buildingSaleMap4Date.containsKey(mShopSaleDetail.getBuildingCode()+","+monthFormat)){
                        //System.out.println("==============商铺月份存在数据累加");
                        //月份存在，累加数据
                        comp3(buildingSaleMap4Date,sale,monthFormat,1,mShopSaleDetail);
                    }else{
                        //System.out.println("==============月份不存在添加");
                        //月份不存在，添加
                        comp4(buildingSaleMap4Date,sale,income4Buildmonth,monthFormat,1,2,mShopSaleDetail);
                    }
                    if(buildingSaleMap4Date.containsKey(mShopSaleDetail.getBuildingCode()+","+dayFormat)){
                        //System.out.println("==============商铺天存在数据累加");
                        //天存在，累加数据
                        comp3(buildingSaleMap4Date,sale,dayFormat,1,mShopSaleDetail);
                    }else{
                        //System.out.println("==============天不存在添加");
                        //天不存在，添加
                        comp4(buildingSaleMap4Date,sale,income4Buildday,dayFormat,1,1,mShopSaleDetail);
                    }
                }else{
                    //System.out.println("==============3初始化数据");
                    //业态不存在，初始化数据
                    comp4(buildingSaleMap4Date,sale,income4Buildyear,yearFormat,1,4,mShopSaleDetail);
                    comp4(buildingSaleMap4Date,sale,income4Buildquarter,quarterFormat,1,3,mShopSaleDetail);
                    comp4(buildingSaleMap4Date,sale,income4Buildmonth,monthFormat,1,2,mShopSaleDetail);
                    comp4(buildingSaleMap4Date,sale,income4Buildday,dayFormat,1,1,mShopSaleDetail);
                    //装载
                    buildingSaleMap4Date.put(mShopSaleDetail.getBuildingCode()+","+yearFormat,income4Buildyear);
                    buildingSaleMap4Date.put(mShopSaleDetail.getBuildingCode()+","+quarterFormat,income4Buildquarter);
                    buildingSaleMap4Date.put(mShopSaleDetail.getBuildingCode()+","+monthFormat,income4Buildmonth);
                    buildingSaleMap4Date.put(mShopSaleDetail.getBuildingCode()+","+dayFormat,income4Buildday);
                }
                buildingSaleMap.put(mShopSaleDetail.getBuildingCode(),null);

                //根据项目分类 Project_Level为2
                //判断Map中是否存在该项目
                if(projectSaleMap.containsKey(sale.getProjectId())){
                    /*
                    存在，判断日期是否存在,需要累加的一个参数
                    1.该业态销售总额TypeAmount
                    */
                    if(projectSaleMap4Date.containsKey(sale.getProjectId()+","+yearFormat)){
                        //System.out.println("==============项目年份存在数据累加");
                        comp3(projectSaleMap4Date,sale,yearFormat,2,mShopSaleDetail);
                    }else{
                        //年份不存在，添加
                        //System.out.println("==============项目年份不存在添加");
                        comp4(projectSaleMap4Date,sale,income4year,yearFormat,2,4,mShopSaleDetail);
                    }
                    if(projectSaleMap4Date.containsKey(sale.getProjectId()+","+quarterFormat)){
                        //System.out.println("==============项目季度存在数据累加");
                        //季度存在，累加数据
                        comp3(projectSaleMap4Date,sale,quarterFormat,2,mShopSaleDetail);
                    }else{
                        //季度不存在，添加
                        //System.out.println("==============季度不存在添加");
                        comp4(projectSaleMap4Date,sale,income4quarter,quarterFormat,2,3,mShopSaleDetail);
                    }
                    if(projectSaleMap4Date.containsKey(sale.getProjectId()+","+monthFormat)){
                        //System.out.println("==============项目月份存在数据累加");
                        //月份存在，累加数据
                        comp3(projectSaleMap4Date,sale,monthFormat,2,mShopSaleDetail);
                    }else{
                        //System.out.println("==============月份不存在添加");
                        //月份不存在，添加
                        comp4(projectSaleMap4Date,sale,income4month,monthFormat,2,2,mShopSaleDetail);
                    }
                    if(projectSaleMap4Date.containsKey(sale.getProjectId()+","+dayFormat)){
                        //System.out.println("==============项目天存在数据累加");
                        //天存在，累加数据
                        comp3(projectSaleMap4Date,sale,dayFormat,2,mShopSaleDetail);
                    }else{
                        //System.out.println("==============天不存在添加");
                        //天不存在，添加
                        comp4(projectSaleMap4Date,sale,income4day,dayFormat,2,1,mShopSaleDetail);
                    }
                }else{
                    //System.out.println("==============1初始化数据");
                    //业态不存在，初始化数据
                    comp4(projectSaleMap4Date,sale,income4year,yearFormat,2,4,mShopSaleDetail);
                    comp4(projectSaleMap4Date,sale,income4quarter,quarterFormat,2,3,mShopSaleDetail);
                    comp4(projectSaleMap4Date,sale,income4month,monthFormat,2,2,mShopSaleDetail);
                    comp4(projectSaleMap4Date,sale,income4day,dayFormat,2,1,mShopSaleDetail);

                    //装载
                    projectSaleMap4Date.put(sale.getProjectId()+","+yearFormat,income4year);
                    projectSaleMap4Date.put(sale.getProjectId()+","+quarterFormat,income4quarter);
                    projectSaleMap4Date.put(sale.getProjectId()+","+monthFormat,income4month);
                    projectSaleMap4Date.put(sale.getProjectId()+","+dayFormat,income4day);
                }
                projectSaleMap.put(sale.getProjectId(),null);
            }

        }
        //计算salePercent
        //project
        salePercentCalc(projectSaleMap4Date);
        //layout
        salePercentCalc(layoutSaleMap4Date);
        //building
        salePercentCalc(buildingSaleMap4Date);
        //装载saleMap
        //project
        filterMap(projectSaleMap,projectSaleMap4Date);
        //layout
        filterMap(layoutSaleMap,layoutSaleMap4Date);
        //building
        filterMap(buildingSaleMap,buildingSaleMap4Date);

        //注入到mysql
        for (Map.Entry<String,Map<String,IncomeReport>> layout:layoutSaleMap.entrySet()) {
            for (Map.Entry<String,IncomeReport> layout4Date:layout.getValue().entrySet()) {
                IncomeReport income = layout4Date.getValue();
                incomeReportMapper.insert(income);
            }
        }
        for (Map.Entry<String,Map<String,IncomeReport>> project:projectSaleMap.entrySet()) {
            for (Map.Entry<String,IncomeReport> project4Date:project.getValue().entrySet()) {
                IncomeReport income = project4Date.getValue();
                incomeReportMapper.insert(income);
            }
        }
        for (Map.Entry<String,Map<String,IncomeReport>> build:buildingSaleMap.entrySet()) {
            for (Map.Entry<String,IncomeReport> build4Date:build.getValue().entrySet()) {
                IncomeReport income = build4Date.getValue();
                incomeReportMapper.insert(income);
            }
        }

        return 0;
    }



    @Override
    public int saveCheckTicket() throws Exception{
        //准备检票信息存放的容器，每条不重复数据会分为3*4份注入到报表
        //根据Project_Level划分
        Map<String,Map<String,TicketReport>> layoutCheckMap = new HashMap<>();
        Map<String,Map<String,TicketReport>> projectCheckMap = new HashMap<>();
        Map<String,Map<String,TicketReport>> buildingCheckMap = new HashMap<>();
        //根据日期划分
        Map<String,TicketReport> projectCheckMap4Date = new HashMap<>();
        Map<String,TicketReport> layoutCheckMap4Date = new HashMap<>();
        Map<String,TicketReport> buildingCheckMap4Date = new HashMap<>();


        //获取批次表layout为6信息，检票
        List<String> batchNumbers = batchMapper.findBatchByLayoutId(6);
        Query query = new Query();
        Criteria criteria = Criteria.where("seqId").in(batchNumbers);
        query.addCriteria(criteria);
        List<McheckTicket> mtList = mongoTemplate.find(query, McheckTicket.class);
        for (McheckTicket mcheckTicket:mtList) {
            String data = mcheckTicket.getData();
            if(data==null){
                continue;
            }
            List<Map<String,Object>> dataList = (List<Map<String, Object>>)JSONArray.parse(data);
            for (Map<String,Object> map:dataList) {
                CheckTicket checkTicket = new CheckTicket();
                checkTicket.setProjectId((String) map.get("PROJECT_ID"));
                checkTicket.setProjectName((String) map.get("PROJECT_NAME"));
                checkTicket.setBatchNumber(mcheckTicket.getSeqId());
                checkTicket.setDealId(String.valueOf((int) map.get("DEAL_ID")));
                checkTicket.setTicketType((String) map.get("TICKET_TYPE"));
                checkTicket.setBarcode((String) map.get("BARCODE"));
                //checkTicket.setTicketName((String) map.get("PROJECT_ID"));
                checkTicket.setGzone((String) map.get("GZONE"));
                checkTicket.setInGardenDate(ConvertUtils.convertDate((String)map.get("IN_GARDEN_DATE")));
                checkTicket.setDeviceName((String) map.get("DEVICE_NAME"));
                checkTicket.setIncount((Integer) map.get("INCOUNT"));
                checkTicket.setCheckCount((Integer) map.get("INCOUNT"));
                checkTicket.setType((String) map.get("TYPE"));
                checkTicket.setTicketType((String) map.get("TICKET_TYPE"));
                checkTicket.setTicketCode((String) map.get("TICKET_CODE"));
                checkTicket.setCheckDate(ConvertUtils.convertDate((String) map.get("CHECK_DATE")));
                checkTicket.setTicketPrice( (BigDecimal)map.get("TICKET_PRICE"));
                checkTicket.setDiscount(String.valueOf(map.get("DISCOUNT")) );
                checkTicket.setTicketAmount( (BigDecimal)map.get("TICKET_AMOUNT"));
                checkTicket.setTicketPeople((int)map.get("TICKET_PEOPLE"));
                checkTicketMapper.insert(checkTicket);
                //准备TicketReport
                TicketReport check4projectYear = new TicketReport();
                TicketReport check4projectQuarter = new TicketReport();
                TicketReport check4projectMonth = new TicketReport();
                TicketReport check4projectDay = new TicketReport();

                TicketReport check4buildingYear = new TicketReport();
                TicketReport check4buildingQuarter = new TicketReport();
                TicketReport check4buildingMonth = new TicketReport();
                TicketReport check4buildingDay = new TicketReport();

                TicketReport check4layoutYear = new TicketReport();
                TicketReport check4layoutQuarter = new TicketReport();
                TicketReport check4layoutMonth = new TicketReport();
                TicketReport check4layoutDay = new TicketReport();

                //将日期分类转换为固定格式
                Date date = checkTicket.getCheckDate();
                String yearFormat = ConvertUtils.convertDate2Year(date);
                String quarterFormat = ConvertUtils.convertDate2Quarter(date);
                String monthFormat = ConvertUtils.convertDate2Month(date);
                String dayFormat = ConvertUtils.convertDate2Day(date);

                //优化注入，缓存预算表信息 key->project_id,layout_id,type_date value->budget
                Map<String,Integer> budgetMap = new HashMap<>();
                List<Budget> budgetList = budgetMapper.getAllBudget2Map();
                for (Budget budget:budgetList) {
                    budgetMap.put(budget.getProjectId()+","+budget.getLayoutId()+","+budget.getTypeDate(),
                            Integer.valueOf(budget.getBudget()));
                }
                //根据业态分类 Project_Level为0
                //判断Map中是否存在该业态
                if(layoutCheckMap.containsKey(mcheckTicket.getLayoutId())){
                    /*
                    存在，判断日期是否存在,需要累加的一个参数
                    1.检票数量InCount->TicketCount
                    */
                    if(layoutCheckMap4Date.containsKey(mcheckTicket.getLayoutId()+","+yearFormat)){
                        //System.out.println("==============业态年份存在数据累加");
                        comp5(layoutCheckMap4Date,checkTicket,yearFormat,0,mcheckTicket,budgetMap);
                    }else{
                        //年份不存在，添加
                        //System.out.println("==============年份不存在添加");
                        comp6(layoutCheckMap4Date,checkTicket,check4layoutYear,yearFormat,0,4,mcheckTicket,budgetMap);
                    }
                    if(layoutCheckMap4Date.containsKey(mcheckTicket.getLayoutId()+","+quarterFormat)){
                        //System.out.println("==============业态季度存在数据累加");
                        //季度存在，累加数据
                        comp5(layoutCheckMap4Date,checkTicket,quarterFormat,0,mcheckTicket,budgetMap);
                    }else{
                        //季度不存在，添加
                        //System.out.println("==============季度不存在添加");
                        comp6(layoutCheckMap4Date,checkTicket,check4layoutQuarter,quarterFormat,0,3,mcheckTicket,budgetMap);
                    }
                    if(layoutCheckMap4Date.containsKey(mcheckTicket.getLayoutId()+","+monthFormat)){
                        //System.out.println("==============业态月份存在数据累加");
                        //月份存在，累加数据
                        comp5(layoutCheckMap4Date,checkTicket,monthFormat,0,mcheckTicket,budgetMap);
                    }else{
                        //System.out.println("==============月份不存在添加");
                        //月份不存在，添加
                        comp6(layoutCheckMap4Date,checkTicket,check4layoutMonth,monthFormat,0,2,mcheckTicket,budgetMap);
                    }
                    if(layoutCheckMap4Date.containsKey(mcheckTicket.getLayoutId()+","+dayFormat)){
                        //System.out.println("==============业态天存在数据累加");
                        //天存在，累加数据
                        comp5(layoutCheckMap4Date,checkTicket,dayFormat,0,mcheckTicket,budgetMap);
                    }else{
                        //System.out.println("==============天不存在添加");
                        //天不存在，添加
                        comp6(layoutCheckMap4Date,checkTicket,check4layoutDay,dayFormat,0,1,mcheckTicket,budgetMap);
                    }
                }else{
                    //System.out.println("==============0检票业态初始化数据");
                    //业态不存在，初始化数据
                    comp6(layoutCheckMap4Date,checkTicket,check4layoutYear,yearFormat,0,4,mcheckTicket,budgetMap);
                    comp6(layoutCheckMap4Date,checkTicket,check4layoutQuarter,quarterFormat,0,3,mcheckTicket,budgetMap);
                    comp6(layoutCheckMap4Date,checkTicket,check4layoutMonth,monthFormat,0,2,mcheckTicket,budgetMap);
                    comp6(layoutCheckMap4Date,checkTicket,check4layoutDay,dayFormat,0,1,mcheckTicket,budgetMap);
                    //装载
                    layoutCheckMap4Date.put(mcheckTicket.getLayoutId()+","+yearFormat,check4layoutYear);
                    layoutCheckMap4Date.put(mcheckTicket.getLayoutId()+","+quarterFormat,check4layoutQuarter);
                    layoutCheckMap4Date.put(mcheckTicket.getLayoutId()+","+monthFormat,check4layoutMonth);
                    layoutCheckMap4Date.put(mcheckTicket.getLayoutId()+","+dayFormat,check4layoutDay);
                }
                layoutCheckMap.put(mcheckTicket.getLayoutId(),null);

                //根据业态分类 Project_Level为1
                //判断Map中是否存在该商铺
                if(buildingCheckMap.containsKey(mcheckTicket.getBuildingCode())){
                    /*
                    存在，判断日期是否存在,需要累加的一个参数
                    1.检票数量InCount->TicketCount
                    */
                    if(buildingCheckMap4Date.containsKey(mcheckTicket.getBuildingCode()+","+yearFormat)){
                        //System.out.println("==============商铺年份存在数据累加");
                        comp5(buildingCheckMap4Date,checkTicket,yearFormat,1,mcheckTicket,budgetMap);
                    }else{
                        //年份不存在，添加
                        //System.out.println("==============年份不存在添加");
                        comp6(buildingCheckMap4Date,checkTicket,check4buildingYear,yearFormat,1,4,mcheckTicket,budgetMap);
                    }
                    if(buildingCheckMap4Date.containsKey(mcheckTicket.getBuildingCode()+","+quarterFormat)){
                        //System.out.println("==============商铺季度存在数据累加");
                        //季度存在，累加数据
                        comp5(buildingCheckMap4Date,checkTicket,quarterFormat,1,mcheckTicket,budgetMap);
                    }else{
                        //季度不存在，添加
                        //System.out.println("==============季度不存在添加");
                        comp6(buildingCheckMap4Date,checkTicket,check4buildingQuarter,quarterFormat,1,3,mcheckTicket,budgetMap);
                    }
                    if(buildingCheckMap4Date.containsKey(mcheckTicket.getBuildingCode()+","+monthFormat)){
                        //System.out.println("==============商铺月份存在数据累加");
                        //月份存在，累加数据
                        comp5(buildingCheckMap4Date,checkTicket,monthFormat,1,mcheckTicket,budgetMap);
                    }else{
                        //System.out.println("==============月份不存在添加");
                        //月份不存在，添加
                        comp6(buildingCheckMap4Date,checkTicket,check4buildingMonth,monthFormat,1,2,mcheckTicket,budgetMap);
                    }
                    if(buildingCheckMap4Date.containsKey(mcheckTicket.getBuildingCode()+","+dayFormat)){
                        //System.out.println("==============商铺天存在数据累加");
                        //天存在，累加数据
                        comp5(buildingCheckMap4Date,checkTicket,dayFormat,1,mcheckTicket,budgetMap);
                    }else{
                        //System.out.println("==============天不存在添加");
                        //天不存在，添加
                        comp6(buildingCheckMap4Date,checkTicket,check4buildingDay,dayFormat,1,1,mcheckTicket,budgetMap);
                    }
                }else{
                    //System.out.println("==============1检票业态初始化数据");
                    //业态不存在，初始化数据
                    comp6(buildingCheckMap4Date,checkTicket,check4buildingYear,yearFormat,1,4,mcheckTicket,budgetMap);
                    comp6(buildingCheckMap4Date,checkTicket,check4buildingQuarter,quarterFormat,1,3,mcheckTicket,budgetMap);
                    comp6(buildingCheckMap4Date,checkTicket,check4buildingMonth,monthFormat,1,2,mcheckTicket,budgetMap);
                    comp6(buildingCheckMap4Date,checkTicket,check4buildingDay,dayFormat,1,1,mcheckTicket,budgetMap);
                    //装载
                    buildingCheckMap4Date.put(mcheckTicket.getBuildingCode()+","+yearFormat,check4buildingYear);
                    buildingCheckMap4Date.put(mcheckTicket.getBuildingCode()+","+quarterFormat,check4buildingQuarter);
                    buildingCheckMap4Date.put(mcheckTicket.getBuildingCode()+","+monthFormat,check4buildingMonth);
                    buildingCheckMap4Date.put(mcheckTicket.getBuildingCode()+","+dayFormat,check4buildingDay);
                }
                buildingCheckMap.put(mcheckTicket.getBuildingCode(),null);

                //根据业态分类 Project_Level为2
                //判断Map中是否存在该项目
                if(projectCheckMap.containsKey(checkTicket.getProjectId())){
                    /*
                    存在，判断日期是否存在,需要累加的一个参数
                    1.检票数量InCount->TicketCount
                    */
                    if(projectCheckMap4Date.containsKey(checkTicket.getProjectId()+","+yearFormat)){
                        //System.out.println("==============项目年份存在数据累加");
                        comp5(projectCheckMap4Date,checkTicket,yearFormat,2,mcheckTicket,budgetMap);
                    }else{
                        //年份不存在，添加
                        //System.out.println("==============年份不存在添加");
                        comp6(projectCheckMap4Date,checkTicket,check4projectYear,yearFormat,2,4,mcheckTicket,budgetMap);
                    }
                    if(projectCheckMap4Date.containsKey(checkTicket.getProjectId()+","+quarterFormat)){
                        //System.out.println("==============项目季度存在数据累加");
                        //季度存在，累加数据
                        comp5(projectCheckMap4Date,checkTicket,quarterFormat,2,mcheckTicket,budgetMap);
                    }else{
                        //季度不存在，添加
                        //System.out.println("==============季度不存在添加");
                        comp6(projectCheckMap4Date,checkTicket,check4projectQuarter,quarterFormat,2,3,mcheckTicket,budgetMap);
                    }
                    if(projectCheckMap4Date.containsKey(checkTicket.getProjectId()+","+monthFormat)){
                        //System.out.println("==============项目月份存在数据累加");
                        //月份存在，累加数据
                        comp5(projectCheckMap4Date,checkTicket,monthFormat,2,mcheckTicket,budgetMap);
                    }else{
                        //System.out.println("==============月份不存在添加");
                        //月份不存在，添加
                        comp6(projectCheckMap4Date,checkTicket,check4projectMonth,monthFormat,2,2,mcheckTicket,budgetMap);
                    }
                    if(projectCheckMap4Date.containsKey(checkTicket.getProjectId()+","+dayFormat)){
                        //System.out.println("==============项目天存在数据累加");
                        //天存在，累加数据
                        comp5(projectCheckMap4Date,checkTicket,dayFormat,2,mcheckTicket,budgetMap);
                    }else{
                        //System.out.println("==============天不存在添加");
                        //天不存在，添加
                        comp6(projectCheckMap4Date,checkTicket,check4projectDay,dayFormat,2,1,mcheckTicket,budgetMap);
                    }
                }else{
                    //System.out.println("==============2检票业态初始化数据");
                    //业态不存在，初始化数据
                    comp6(projectCheckMap4Date,checkTicket,check4projectYear,yearFormat,2,4,mcheckTicket,budgetMap);
                    comp6(projectCheckMap4Date,checkTicket,check4projectQuarter,quarterFormat,2,3,mcheckTicket,budgetMap);
                    comp6(projectCheckMap4Date,checkTicket,check4projectMonth,monthFormat,2,2,mcheckTicket,budgetMap);
                    comp6(projectCheckMap4Date,checkTicket,check4projectDay,dayFormat,2,1,mcheckTicket,budgetMap);
                    //装载
                    projectCheckMap4Date.put(checkTicket.getProjectId()+","+yearFormat,check4projectYear);
                    projectCheckMap4Date.put(checkTicket.getProjectId()+","+quarterFormat,check4projectQuarter);
                    projectCheckMap4Date.put(checkTicket.getProjectId()+","+monthFormat,check4projectMonth);
                    projectCheckMap4Date.put(checkTicket.getProjectId()+","+dayFormat,check4projectDay);
                }
                projectCheckMap.put(checkTicket.getProjectId(),null);
            }
        }
        //project
        filterMap4check(projectCheckMap,projectCheckMap4Date);
        //layout
        filterMap4check(layoutCheckMap,layoutCheckMap4Date);
        //building
        filterMap4check(buildingCheckMap,buildingCheckMap4Date);

        //注入到mysql
        for (Map.Entry<String,Map<String,TicketReport>> layout:layoutCheckMap.entrySet()) {
            for (Map.Entry<String,TicketReport> layout4Date:layout.getValue().entrySet()) {
                TicketReport ticket = layout4Date.getValue();
                ticketReportMapper.insert(ticket);
            }
        }
        for (Map.Entry<String,Map<String,TicketReport>> project:projectCheckMap.entrySet()) {
            for (Map.Entry<String,TicketReport> project4Date:project.getValue().entrySet()) {
                TicketReport ticket = project4Date.getValue();
                ticketReportMapper.insert(ticket);
            }
        }
        for (Map.Entry<String,Map<String,TicketReport>> build:buildingCheckMap.entrySet()) {
            for (Map.Entry<String,TicketReport> build4Date:build.getValue().entrySet()) {
                TicketReport ticket = build4Date.getValue();
                ticketReportMapper.insert(ticket);
            }
        }

        return 0;
    }

    @Override
    public int saveBatchInfo() {
        //从mongo拉取批次信息
        List<MShopSaleDataSeq> seqList = mongoTemplate.findAll(MShopSaleDataSeq.class);
        for (MShopSaleDataSeq mShopSaleDataSeq:seqList) {
            //判断批次号是否存在
            String seqId = mShopSaleDataSeq.getSeqId();
            List<Batch> batchBySeqId = batchMapper.findBatchBySeqId(seqId);
            //若批次表存在，跳过
            if(batchBySeqId!=null&&batchBySeqId.size()!=0){
                continue;
            }
            //注入批次表
            Batch batch = new Batch();
            batch.setBatchNumber(mShopSaleDataSeq.getSeqId());
            batch.setCreateDate(ConvertUtils.convertDate(mShopSaleDataSeq.getSys_time()));
            batch.setLayoutId(Integer.valueOf(mShopSaleDataSeq.getLayoutId()));
            batch.setLayoutName(mShopSaleDataSeq.getLayoutName());
            batch.setTotalCount(Integer.valueOf(mShopSaleDataSeq.getTotalCount()));
            batch.setPageTotal(Integer.valueOf(mShopSaleDataSeq.getPageTotal()));
            //默认为未统计
            batch.setStatisticsState("N");
            batch.setGetCount(0);
            //标记为未获取
            batch.setGetState("N");
            //注入到Mysql
            batchMapper.insert(batch);

        }
        return 0;
    }

    @Override
    public void AnalyzeData4CheckTicket() {

    }

    @Override
    public void AnalyzeData4ShopSale() {

    }

    private void comp(Map<String,IncomeReport> map, TicketSale ticketSale, String format,int pLevel,MticketSaleDetail mticketSaleDetail) throws Exception{
        IncomeReport incomeReport = null;
        if(pLevel==0)//业态
            incomeReport = map.get(ticketSale.getLayoutId()+","+format);
        if(pLevel==1)//商铺
            incomeReport = map.get(mticketSaleDetail.getBuildingCode()+","+format);
        if(pLevel==2)//项目
            incomeReport = map.get(ticketSale.getProjectId()+","+format);
        //年存在，累加数据
        if(incomeReport!=null){
            BigDecimal typeAmount = incomeReport.getTypeAmount();
            typeAmount = typeAmount.add(ticketSale.getTicketAmount());
            Integer bookingCount = incomeReport.getBookingCount();
            Integer ticketCount = ticketSale.getTicketCount();
            bookingCount+=ticketCount;
            incomeReport.setTypeAmount(typeAmount);
            incomeReport.setBookingCount(bookingCount);
        }
        //覆盖key-value
        if(pLevel==0){
            //业态
            map.put(ticketSale.getLayoutId()+","+format,incomeReport);
        }
        if(pLevel==1){
            //商铺
            map.put(mticketSaleDetail.getBuildingCode()+","+format,incomeReport);
        }
        if(pLevel==2){
            //项目
            map.put(ticketSale.getProjectId()+","+format,incomeReport);
        }
    }

    private void comp2(Map<String,IncomeReport> map, TicketSale ticketSale, IncomeReport report, String format, int pLevel, int type, MticketSaleDetail mticketSaleDetail) throws Exception{
        report.setProjectLevel(pLevel);
        if(pLevel==2){
            //项目
            //System.out.println("comp2-->pLevel:2");
            report.setTypeId(ticketSale.getProjectId());
            report.setTypeName(ticketSale.getProjectName());
        }else if(pLevel==1){
            //商铺
            //System.out.println("comp2-->pLevel:1");
            report.setTypeId(mticketSaleDetail.getBuildingCode());
            report.setTypeName(mticketSaleDetail.getBuildingName());
        }else if(pLevel==0){
            //业态
            //System.out.println("comp2-->pLevel:1");
            report.setTypeId(ticketSale.getLayoutId().toString());
            report.setTypeName(ticketSale.getLayoutName());
        }
        report.setTypeAmount(ticketSale.getTicketAmount());
        report.setDateType(format);
        report.setType(type);
        //income4year.setBudgetId(budgetId); 这个没用直接去budget表取
        //销售比率  该项目销售额/所有项目销售额
        //income4year.setSalePercentum();//需要统计完成计算
        //初始化售票总数
        report.setBookingCount(ticketSale.getTicketCount());
        report.setCreateDate(new Date());
        map.put(report.getTypeId()+","+format,report);
    }


    private void comp3(Map<String, IncomeReport> map, ShopSale sale, String format,int pLevel,MShopSaleDetail mShopSaleDetail) throws Exception {
        IncomeReport incomeReport = null;
        if(pLevel==0)//业态
             incomeReport = map.get(sale.getLayoutId()+","+format);
        if(pLevel==1)//商铺
            incomeReport = map.get(mShopSaleDetail.getBuildingCode()+","+format);
        if(pLevel==2)//项目
            incomeReport = map.get(sale.getProjectId()+","+format);
        //年存在，累加数据
        if(incomeReport!=null){
            BigDecimal typeAmount = incomeReport.getTypeAmount();
            typeAmount = typeAmount.add(sale.getSalePrice());
            incomeReport.setTypeAmount(typeAmount);
        }
        //覆盖key-value
        if(pLevel==0){
            //业态
            map.put(sale.getLayoutId()+","+format,incomeReport);
        }
        if(pLevel==1){
            //商铺
            map.put(mShopSaleDetail.getBuildingCode()+","+format,incomeReport);
        }
        if(pLevel==2){
            //项目
            map.put(sale.getProjectId()+","+format,incomeReport);
        }
    }

    private void comp4(Map<String, IncomeReport> map, ShopSale sale, IncomeReport report, String format, int pLevel, int type, MShopSaleDetail mShopSaleDetail) {
        report.setProjectLevel(pLevel);
        if(pLevel==2){
            //项目
            //System.out.println("comp4-->pLevel:2");
            report.setTypeId(sale.getProjectId());
            report.setTypeName(sale.getProjectName());
        }else if(pLevel==1){
            //商铺
            //System.out.println("comp4-->pLevel:1");
            report.setTypeId(mShopSaleDetail.getBuildingCode());
            report.setTypeName(mShopSaleDetail.getBuildingName());
        }else if(pLevel==0){
            //业态
//            System.out.println("comp4-->pLevel:0");
            String layoutId = sale.getLayoutId().toString();
            report.setTypeId(layoutId);
            report.setTypeName(sale.getLayoutName());
        }
        report.setTypeAmount(sale.getSalePrice());
        report.setDateType(format);
        report.setType(type);
        //income4year.setBudgetId(budgetId); 这个没用直接去budget表取
        //销售比率  该项目销售额/所有项目销售额
        //income4year.setSalePercentum();//需要统计完成计算
        //初始化售票总数
        report.setCreateDate(new Date());
        map.put(report.getTypeId()+","+format,report);
    }

    private void comp5(Map<String,TicketReport> map,CheckTicket checkTicket,String format,int pLevel,McheckTicket mcheckTicket,
                      Map<String,Integer> budgetMap){
        TicketReport ticketReport = new TicketReport();
        int checkCount;
        int totalNum = 0;
        if(pLevel==0)//业态
            ticketReport = map.get(mcheckTicket.getLayoutId()+","+format);
        if(pLevel==1)//商铺
            ticketReport = map.get(mcheckTicket.getBuildingCode()+","+format);
        if(pLevel==2)//项目
            ticketReport = map.get(checkTicket.getProjectId()+","+format);
        String layoutId = mcheckTicket.getLayoutId();
        String projectId = checkTicket.getProjectId();
        //累加数据 检票数量
            checkCount = ticketReport.getTicketCount();
            checkCount+=checkTicket.getCheckCount();
            ticketReport.setTicketCount(checkCount);
        //覆盖key-value
        if(pLevel==0){
            //业态
            for (Map.Entry<String,Integer> entry:budgetMap.entrySet()) {
                String layoutInMap = entry.getKey().split(",")[1];
                String typeDateInMap = entry.getKey().split(",")[2];
                if(layoutId.equals(layoutInMap)&&format.equals(typeDateInMap)){
                    totalNum += entry.getValue();
                }
            }
            if(totalNum!=0)
                ticketReport.setFinishPercentum(ticketReport.getTicketCount()*100/totalNum);
            else
                ticketReport.setFinishPercentum(0);
            map.put(mcheckTicket.getLayoutId()+","+format,ticketReport);
        }
        if(pLevel==1){
            //商铺
            for (Map.Entry<String,Integer> entry:budgetMap.entrySet()) {
                String typeDateInMap = entry.getKey().split(",")[2];
                if(format.equals(typeDateInMap)){
                    totalNum += entry.getValue();
                }
            }
            if(totalNum!=0)
                ticketReport.setFinishPercentum(ticketReport.getTicketCount()*100/totalNum);
            else
                ticketReport.setFinishPercentum(0);
            map.put(mcheckTicket.getBuildingCode()+","+format,ticketReport);
        }
        if(pLevel==2){
            //项目
            for (Map.Entry<String,Integer> entry:budgetMap.entrySet()) {
                String typeDateInMap = entry.getKey().split(",")[2];
                String projectInMap = entry.getKey().split(",")[0];
                if(projectId.equals(projectInMap)&&format.equals(typeDateInMap)){
                    totalNum += entry.getValue()+0+0;
                }
            }
            if(totalNum!=0)
                ticketReport.setFinishPercentum(ticketReport.getTicketCount()*100/totalNum);
            else
                ticketReport.setFinishPercentum(0);
            map.put(checkTicket.getProjectId()+","+format,ticketReport);
        }
    }

    private void comp6(Map<String, TicketReport> map, CheckTicket checkTicket, TicketReport report, String format, int pLevel, int type, McheckTicket mcheckTicket,
    Map<String,Integer> budgetMap){
        int totalNum =0;
        report.setProjectLevel(pLevel);
        report.setTicketCount(checkTicket.getCheckCount());
        String projectId = checkTicket.getProjectId();
        String layoutId = mcheckTicket.getLayoutId();
        if(pLevel==2){
            //项目
            //System.out.println("comp6-->pLevel:2");
            report.setTypeId(checkTicket.getProjectId());
            report.setTypeName(checkTicket.getProjectName());
            for (Map.Entry<String,Integer> entry:budgetMap.entrySet()) {
                String typeDateInMap = entry.getKey().split(",")[2];
                String projectInMap = entry.getKey().split(",")[0];
                if(projectId.equals(projectInMap)&&format.equals(typeDateInMap)){
                    totalNum += entry.getValue();
                }
            }
        }else if(pLevel==1){
            //商铺
            //System.out.println("comp6-->pLevel:1");
            report.setTypeId(mcheckTicket.getBuildingCode());
            report.setTypeName(mcheckTicket.getBuildingName());
            //商铺
            for (Map.Entry<String,Integer> entry:budgetMap.entrySet()) {
                String typeDateInMap = entry.getKey().split(",")[2];
                if(format.equals(typeDateInMap)){
                    totalNum += entry.getValue();
                }
            }
        }else if(pLevel==0){
            //业态
            //System.out.println("comp6-->pLevel:0");
            report.setTypeId(mcheckTicket.getLayoutId());
            report.setTypeName(layoutMapper.selectByPrimaryKey(Integer.valueOf(mcheckTicket.getLayoutId())).getLayoutName());
            for (Map.Entry<String,Integer> entry:budgetMap.entrySet()) {
                String layoutInMap = entry.getKey().split(",")[1];
                String typeDateInMap = entry.getKey().split(",")[2];
                if(layoutId.equals(layoutInMap)&&format.equals(typeDateInMap)){
                    totalNum += entry.getValue()+0;
                }
            }
        }
        report.setDateType(format);
        report.setType(type);
        //report.setBudgetId(budgetId); 这个没用直接去budget表取
        //report.setRetentionNumber(); 滞留数没定义
        //完成率 检票数/目标
        if(totalNum!=0)
            report.setFinishPercentum(report.getTicketCount()*100/totalNum);
        else
            report.setFinishPercentum(0);
        report.setCreateDate(new Date());
        map.put(report.getTypeId()+","+format,report);
    }
    private void filterMap(Map<String,Map<String,IncomeReport>> bmap,Map<String,IncomeReport> smap){
        Map<String,IncomeReport> resMap = null;
        for (String key:bmap.keySet()) {
            resMap = new HashMap<>();
            for (Map.Entry<String,IncomeReport> entry:smap.entrySet()) {
                if(key!=null&&key.equals(entry.getValue().getTypeId())){
                    resMap.put(entry.getKey(),entry.getValue());
                }
            }
            bmap.put(key,resMap);
        }
    }
    private void filterMap4check(Map<String,Map<String,TicketReport>> bmap,Map<String,TicketReport> smap){
        Map<String,TicketReport> resMap = null;
        for (String key:bmap.keySet()) {
            resMap = new HashMap<>();
            for (Map.Entry<String,TicketReport> entry:smap.entrySet()) {
                if(key!=null&&key.equals(entry.getValue().getTypeId())){
                    resMap.put(entry.getKey(),entry.getValue());
                }
            }
            bmap.put(key,resMap);
        }
    }
    private void salePercentCalc(Map<String,IncomeReport> allMap){
        for (String str:allMap.keySet()) {
            //总金额
            BigDecimal total = new BigDecimal(0.0);
            //固定类型金额
            BigDecimal money = new BigDecimal(0.0);
            for (Map.Entry<String,IncomeReport> judgeEntry:allMap.entrySet()) {
                if(str.split(",")[1].equals(judgeEntry.getValue().getDateType())){
                    //指定时间的总金额
                    total = total.add(judgeEntry.getValue().getTypeAmount());
                }
                if(str.equals(judgeEntry.getKey())){
                    money = judgeEntry.getValue().getTypeAmount();
                }
            }
            BigDecimal hun = new BigDecimal(100);
            allMap.get(str).setSalePercentum(money.multiply(hun).divide(total,2,BigDecimal.ROUND_HALF_UP).toString());
        }
    }

    @Override
    public void clearData() {
        incomeReportMapper.clearAll();
        shopSaleMapper.clearAll();
        ticketSaleMapper.clearAll();
        checkTicketMapper.clearAll();
        ticketReportMapper.clearAll();
    }


}

