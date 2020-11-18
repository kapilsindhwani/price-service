package com.mizuho.dao;

import com.mizuho.dao.mapper.PriceMapper;
import com.mizuho.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PriceDaoTest {

    private JdbcTemplate jdbcTemplate;
    private PriceDao priceDao;
    private PriceMapper priceMapper;
    @BeforeEach
    void setUp() {
        jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        priceMapper = Mockito.mock(PriceMapper.class);
        priceDao  = new PriceDao(jdbcTemplate, priceMapper);
    }

    @Test
    void insert() {
        Price price = new Price();
        price.setCurrency("GBP");
        price.setVendorId("123");
        price.setInstrumentId("A123");
        price.setPrice(BigDecimal.TEN);
        priceDao.insert(price);
        Mockito.verify(jdbcTemplate).update(PriceDao.INSERT_PRICE, price.getVendorId(),
                price.getInstrumentId(),
                price.getPrice(),
                price.getCurrency());
    }

    @Test
    void delete() {
        priceDao.delete(LocalDate.now());
        Mockito.verify(jdbcTemplate).update(PriceDao.DELETE_EXPIRED_PRICE, LocalDate.now());
    }

    @Test
    void searchByInstrument() {
        priceDao.searchByInstrument("A123");
        Mockito.verify(jdbcTemplate).query(PriceDao.GET_PRICE_BY_INSTRUMENT, priceMapper, "A123");
    }

    @Test
    void searchByVendor() {
        priceDao.searchByVendor("123");
        Mockito.verify(jdbcTemplate).query(PriceDao.GET_PRICE_BY_VENDOR, priceMapper, "123");
    }
}