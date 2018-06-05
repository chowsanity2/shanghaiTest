package com.chow.controller;

import com.alibaba.fastjson.JSONObject;
import com.chow.model.bo.CheckTicketBo;
import com.chow.model.bo.PerCusTransactionBo;
import com.chow.service.BusinessHandleService;
import com.chow.service.DataHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 数据处理
 */
@Controller
public class DataHandlerController {
    @Autowired
    private DataHandleService dataHandleService;
    @Autowired
    private BusinessHandleService businessHandleService;
    @RequestMapping(value = "/inject")
    public void inject(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int flag = dataHandleService.inject();
        String callback = request.getParameter("callback");
        response.getWriter().print(callback+"("+flag+")");
    }
    @RequestMapping(value = "/reset")
    public void reset(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int flag = dataHandleService.reset();
        String callback = request.getParameter("callback");
        response.getWriter().print(callback+"("+flag+")");
    }

    @RequestMapping(value = "/pct",method = RequestMethod.GET,produces = "text/plain;charset=utf-8")
    public void pct(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String callback = request.getParameter("callback");
        String dateTime = request.getParameter("dateTime");
        String dateType = request.getParameter("dateType");
        dateTime = "2018-05";//test
        dateType = "2";
        PerCusTransactionBo pctBo = businessHandleService.findPCTData(dateTime,dateType);
        String outData = JSONObject.toJSONString(pctBo);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().print(callback + "(" + outData + ")");
    }

    @RequestMapping(value = "/checkTicket",method = RequestMethod.GET,produces = "text/plain;charset=utf-8")
    public void checkTicket(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String callback = request.getParameter("callback");
        String startDate = request.getParameter("startDate");
        String type = request.getParameter("type");
        String dateType = request.getParameter("dateType");
        String endDate = request.getParameter("endDate");
        startDate = "2018-04";//test
        endDate = "2018-05";
        dateType = "2";
        CheckTicketBo checkTicketBo = businessHandleService.findCheckTicketData(startDate,endDate,type,dateType);
        String outData = JSONObject.toJSONString(checkTicketBo);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().print(callback + "(" + outData + ")");
    }
}
