package com.chow.service;

import com.chow.model.bo.CheckTicketBo;
import com.chow.model.bo.PerCusTransactionBo;

import java.util.List;

public interface BusinessHandleService {
    PerCusTransactionBo findPCTData(String dateTime, String dateType);

    CheckTicketBo findCheckTicketData(String startDate, String endDate, String type, String dateType);
}
