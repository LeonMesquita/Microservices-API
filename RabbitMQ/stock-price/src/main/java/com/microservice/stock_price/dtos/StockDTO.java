package com.microservice.stock_price.dtos;

import java.io.Serializable;

public class StockDTO implements Serializable {
    private String productCode;
    private String quantity;

    public StockDTO() {}

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}