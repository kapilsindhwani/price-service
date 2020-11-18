package com.mizuho.dao.mapper;

import com.mizuho.model.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.ResultSet;

class PriceMapperTest {

    @Test
    void mapRow() throws Exception{
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(rs.getString("VENDOR_ID")).thenReturn("VENDOR");
        Mockito.when(rs.getString("INSTRUMENT_ID")).thenReturn("A123");
        Mockito.when(rs.getBigDecimal("PRICE")).thenReturn(BigDecimal.TEN);
        Mockito.when(rs.getString("CURRENCY")).thenReturn("GBP");
        PriceMapper priceMapper = new PriceMapper();
        Price price = priceMapper.mapRow(rs, 1);
        Assertions.assertEquals("VENDOR", price.getVendorId());
        Assertions.assertEquals("A123", price.getInstrumentId());
        Assertions.assertEquals(BigDecimal.TEN, price.getPrice());
        Assertions.assertEquals("GBP", price.getCurrency());
    }
}