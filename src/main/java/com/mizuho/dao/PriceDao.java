package com.mizuho.dao;

import com.mizuho.dao.mapper.PriceMapper;
import com.mizuho.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PriceDao {

    static final String INSERT_PRICE = "INSERT INTO PRICE_SCHEMA.PRICE(ID,VENDOR_ID,INSTRUMENT_ID,PRICE, CURRENCY) VALUES (PRICE_SCHEMA.PRICE_ID_SEQ.NEXTVAL ,?,?,?,?)";
    static final String DELETE_EXPIRED_PRICE = "DELETE FROM PRICE_SCHEMA.PRICE WHERE CREATED_ON = ?";
    static final String GET_PRICE_BY_INSTRUMENT = "SELECT * FROM PRICE_SCHEMA.PRICE WHERE INSTRUMENT_ID = ?";
    static final String GET_PRICE_BY_VENDOR = "SELECT * FROM PRICE_SCHEMA.PRICE WHERE VENDOR_ID = ?";

    private final JdbcTemplate jdbcTemplate;
    private final PriceMapper priceMapper;

    @Autowired
    public PriceDao(JdbcTemplate jdbcTemplate, PriceMapper priceMapper){
        this.priceMapper=priceMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Price insert(Price price){
        jdbcTemplate.update(INSERT_PRICE, price.getVendorId(),
                price.getInstrumentId(),
                price.getPrice(),
                price.getCurrency());
        return price;
    }

    public void delete(LocalDate date){
        jdbcTemplate.update(DELETE_EXPIRED_PRICE, date);
    }

    public List<Price> searchByInstrument(String instrumentId){
        return jdbcTemplate.query(GET_PRICE_BY_INSTRUMENT, priceMapper, instrumentId);
    }

    public List<Price> searchByVendor(String vendorId){
        return jdbcTemplate.query(GET_PRICE_BY_VENDOR, priceMapper, vendorId);
    }

}
