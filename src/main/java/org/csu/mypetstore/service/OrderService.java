package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    @Transactional
    public void insertOrder(Order order) ;
    @Transactional
    public Order getOrder(int orderId) ;

    public List<Order> getOrdersByUsername(String username) ;

    public int getNextId(String name);
}
