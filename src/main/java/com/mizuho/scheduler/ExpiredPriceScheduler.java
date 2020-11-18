package com.mizuho.scheduler;

import com.mizuho.dao.PriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

public class ExpiredPriceScheduler {

    private PriceDao priceDao;

    @Autowired
    public ExpiredPriceScheduler(PriceDao priceDao){
        this.priceDao = priceDao;
    }
    @Scheduled(cron = "0 1 1 * * ?")
    public void removeExpiredPrices(){
        priceDao.delete(LocalDate.now().minusDays(30));
    }
}
