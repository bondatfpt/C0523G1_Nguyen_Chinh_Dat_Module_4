package com.example.service;

import com.example.model.OrderBook;
import com.example.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository iOrderRepository;
    @Override
    public boolean save(OrderBook orderBook) {
        try {
        iOrderRepository.save(orderBook);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<OrderBook> findAll() {
        return iOrderRepository.findAll();
    }

    @Override
    public void deleteOrder(int id) {
        iOrderRepository.deleteById(id);
    }

    @Override
    public OrderBook findById(int id) {
        return iOrderRepository.findById(id).get();
    }
}
