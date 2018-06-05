package com.chow.dao;

import com.chow.model.Batch;

import java.util.List;

public interface BatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Batch record);

    int insertSelective(Batch record);

    Batch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Batch record);

    int updateByPrimaryKey(Batch record);

    //根据批次号判断该批次是否存在
    List<Batch> findBatchBySeqId(String batchNumber);

    //因为没apiType这个字段，改用layoutId判断,只需要返回的批次号
    List<String> findBatchByLayoutId(Integer layoutId);
    List<String> findBatchNotLayoutId(Integer layoutId);
}