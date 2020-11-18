package com.mizuho.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Price {
    private String instrumentId;
    private BigDecimal price;
    private String currency;
    private String vendorId;
}
