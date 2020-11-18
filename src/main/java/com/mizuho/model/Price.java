package com.mizuho.model;

import java.math.BigDecimal;

public class Price {

    private String instrumentId;
    private BigDecimal price;
    private String currency;
    private String vendorId;

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
