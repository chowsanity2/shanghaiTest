package com.chow.service.impl;

import com.chow.dao.IncomeReportMapper;
import com.chow.dao.TicketReportMapper;
import com.chow.model.TicketReport;
import com.chow.model.bo.CTData;
import com.chow.model.bo.CheckTicketBo;
import com.chow.model.bo.PCTData;
import com.chow.model.bo.PerCusTransactionBo;
import com.chow.model.vo.Con4Budget;
import com.chow.service.BusinessHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class BusinessHandleServiceImpl implements BusinessHandleService {
    @Autowired
    private IncomeReportMapper incomeReportMapper;
    @Autowired
    private TicketReportMapper ticketReportMapper;
    @Override
    public PerCusTransactionBo findPCTData(String dateTime, String dateType) {
        PerCusTransactionBo pctBo = null;
        try{
            List<PCTData> list = new ArrayList<>();
            pctBo = new PerCusTransactionBo();
            PCTData pctData = new PCTData();
            pctBo.setCode("1");
            pctBo.setMsg("成功");
            //根据业态分：
            // 1.商品->income1
            // 2.餐饮->income2
            // 3.售票->income3
            // 4.综合->除了上述三项，其他业态消费other
            String layoutId = null;
            int pLevel = 0;
            Con4Budget con = new Con4Budget();
            con.setTypeDate(dateTime);
            con.setpLevel(0);
            con.setLayoutId("1");
            Integer income1 = incomeReportMapper.findAmountByLayoutId(con);
            con.setLayoutId("2");
            Integer income2 = incomeReportMapper.findAmountByLayoutId(con);
            con.setLayoutId("3");
            Integer income3 = incomeReportMapper.findAmountByLayoutId(con);
            Integer total = incomeReportMapper.findTotalAmount(con);
            if(income1==null)
                income1=0;
            if(income2==null)
                income2=0;
            if(income3==null)
                income3=0;
            int other = 0;
            if(total!=null){
                other = total - (income1+income2+income3);
            }

            //获取人数
            Integer people = ticketReportMapper.getPeople(con);

            if(people!=null&&people!=0){
                income1 = income1/people;
                income2 = income2/people;
                income3 = income3/people;
                other = other/people;
            }
            pctData.setDateTime(dateTime);
            pctData.setGoodsAmount(income1);
            pctData.setFoodAmount(income2);
            pctData.setTicketAmount(income3);
            pctData.setTotalAmount(other);
            list.add(pctData);
            pctBo.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            pctBo = new PerCusTransactionBo();
            pctBo.setCode("0");
            pctBo.setMsg("失败");
        }
        return pctBo;
    }

    @Override
    public CheckTicketBo findCheckTicketData(String startDate, String endDate, String type, String dateType) {
        CheckTicketBo checkTicketBo = null;
        try{
            checkTicketBo = new CheckTicketBo();
            checkTicketBo.setCode("1");
            checkTicketBo.setMsg("成功");
            List<CTData> ctDataList = new ArrayList<>();
            //处理日期格式，去掉"-"方便比较
            startDate = startDate.replace("-","");
            endDate = endDate.replace("-","");
            //预置条件
            Con4Budget con = new Con4Budget();
            //项目
            con.setpLevel(2);
            con.setStartDate(startDate);
            con.setEndDate(endDate);
            con.setType(Integer.valueOf(dateType));
            List<TicketReport> list = ticketReportMapper.findCheckTicketData(con);
            //项目名，检票数量
            Map<String,Integer> map = new HashMap<>();
            if(list!=null&&list.size()!=0){
                for (TicketReport ticketReport:list) {
                    System.out.println("=======选择的时间区间-》项目分类");
                    Integer count;
                    if(map.containsKey(ticketReport.getTypeId())){
                        //累加检票数
                         count = map.get(ticketReport.getTypeId())+ticketReport.getTicketCount();
                    }else{
                         count = ticketReport.getTicketCount();
                    }
                    map.put(ticketReport.getTypeId(),count);
                }
            }
            //开始时间和结束时间相同，求出上个区间的数量,方便计算同比
            startDate = String.valueOf(Integer.parseInt(startDate)-1);
            endDate = String.valueOf(Integer.parseInt(endDate)-1);
            List<TicketReport> lastList = ticketReportMapper.findCheckTicketData(con);
            //项目名，检票数量
            Map<String,Integer> lastMap = new HashMap<>();
            if(lastList!=null&&lastList.size()!=0){
                for (TicketReport ticketReport:lastList) {
                    System.out.println("=======上一段时间区间-》项目分类");
                    Integer count;
                    if(lastMap.containsKey(ticketReport.getTypeId())){
                        //累加检票数
                        count = lastMap.get(ticketReport.getTypeId())+ticketReport.getTicketCount();
                    }else{
                        count = ticketReport.getTicketCount();
                    }
                    lastMap.put(ticketReport.getTypeId(),count);
                }
            }
           //计算总票数
            int total = 0;
            for (Map.Entry<String,Integer> entry:map.entrySet()) {
                total+=entry.getValue();
            }
            checkTicketBo.setTotalTicket(total);
            //计算百分比并封装Bo
            if(map.entrySet().size()!=0){
                for (Map.Entry<String,Integer> entry:map.entrySet()) {
                    CTData ctData = new CTData();
                    ctData.setItemName(entry.getKey());
                    ctData.setTicketNum(entry.getValue());
                    if(total!=0)
                        ctData.setPercent(new BigDecimal((float)entry.getValue()*100/total));
                    else
                        ctData.setPercent(new BigDecimal(0.0));
                    //计算同比
                    if(lastMap.containsKey(entry.getKey())){
                        if(lastMap.get(entry.getKey()) >= entry.getValue())
                            ctData.setIsIncrease(1);//下降
                        else
                            ctData.setIsIncrease(0);//上升
                    }else{
                        ctData.setIsIncrease(0);
                    }
                    ctDataList.add(ctData);
                }
            }
            checkTicketBo.setData(ctDataList);
            return checkTicketBo;
        }catch (Exception e){
            e.printStackTrace();
            checkTicketBo = new CheckTicketBo();
            checkTicketBo.setCode("0");
            checkTicketBo.setMsg("失败");
            return checkTicketBo;
        }

    }
}
