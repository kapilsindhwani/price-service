package com.mizuho.controller;

import com.mizuho.dao.PriceDao;
import com.mizuho.gateway.PriceGateway;
import com.mizuho.model.Price;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price")
@Tag(name = "Price", description = "Price Service")
public class PriceController {

    private PriceGateway priceGateway;

    private PriceDao priceDao;

    @Autowired
    public PriceController(PriceGateway priceGateway, PriceDao priceDao){
        this.priceGateway = priceGateway;
        this.priceDao = priceDao;

    }

    @PostMapping(value = "/publish")
    @ResponseStatus(HttpStatus.OK)
    public void storePrice(@RequestBody Price price) {
        priceGateway.storePrice(MessageBuilder.withPayload(price).build());
    }

    @GetMapping(value = "/instrument/{instrumentId}", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price by Instrument")
    })
    public List<Price> searchByInstrument(@PathVariable("instrumentId") String instrumentId) {
        return priceDao.searchByInstrument(instrumentId);
    }

    @GetMapping(value = "/vendor/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price by Vendor")
    })
    public List<Price> searchByVendor(@PathVariable("vendorId") String vendorId) {
        return priceDao.searchByVendor(vendorId);
    }

}
