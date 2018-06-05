package com.chow.dao;

import com.chow.model.ConsumerFlowReport;

public interface ConsumerFlowReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConsumerFlowReport record);

    int insertSelective(ConsumerFlowReport record);

    ConsumerFlowReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConsumerFlowReport record);

    int updateByPrimaryKey(ConsumerFlowReport record);
}