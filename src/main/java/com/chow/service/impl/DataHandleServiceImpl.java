package com.chow.service.impl;

import com.chow.dao.mongo.DataHandleDao;
import com.chow.service.DataHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DataHandleServiceImpl implements DataHandleService {
    @Autowired
    private DataHandleDao dataHandleDao;
    /**
     * 从mongoDB提取数据注入到Mysql
     * @return 0:失败 1:成功
     */
    @Override
    public int inject() {
        try {
            dataHandleDao.saveCheckTicket();
            dataHandleDao.saveAllSale();
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 清空Mysql数据
     * @return 0:失败 1:成功
     */
    @Override
    public int reset() {
        dataHandleDao.clearData();
        return 1;
    }
}
