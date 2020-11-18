package com.mizuho.dao.mapper;

import com.mizuho.model.Price;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PriceMapper implements RowMapper<Price> {

    @Override
    public Price mapRow(ResultSet resultSet, int i) throws SQLException{
        Price price = new Price();
        price.setVendorId(resultSet.getString("VENDOR_ID"));
        price.setInstrumentId(resultSet.getString("INSTRUMENT_ID"));
        price.setPrice(resultSet.getBigDecimal("PRICE"));
        price.setCurrency(resultSet.getString("CURRENCY"));
        return price;
    }
}
