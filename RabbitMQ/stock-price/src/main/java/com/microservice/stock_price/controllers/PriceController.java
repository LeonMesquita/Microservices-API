package com.microservice.stock_price.controllers;

import com.microservice.stock_price.constants.RabbitMQConstants;
import com.microservice.stock_price.dtos.StockDTO;
import com.microservice.stock_price.services.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    RabbitmqService rabbitmqService;
    @PutMapping
    public ResponseEntity updatePrice(@RequestBody StockDTO body) {
        this.rabbitmqService.sendMessage(RabbitMQConstants.QUEUE_PRICE, body);
        return new ResponseEntity(HttpStatus.OK);
    }
}
