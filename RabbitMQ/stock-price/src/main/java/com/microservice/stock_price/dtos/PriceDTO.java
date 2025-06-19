package com.microservice.stock_price.dtos;

import java.io.Serializable;

public class PriceDTO implements Serializable {
    public String productCode;
    public double price;
}
