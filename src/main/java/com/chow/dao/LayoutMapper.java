package com.chow.dao;

import com.chow.model.Layout;

public interface LayoutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Layout record);

    int insertSelective(Layout record);

    Layout selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Layout record);

    int updateByPrimaryKey(Layout record);
}