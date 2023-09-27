package com.example.service;

import com.example.model.OrderBook;

import java.util.List;

public interface IOrderService {
    boolean save (OrderBook orderBook);

    List<OrderBook> findAll ();

    void deleteOrder (int id);

    OrderBook findById (int id);
}
