/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingjdbc.example.spring.boot.mybatis.service;

import io.shardingjdbc.example.spring.boot.mybatis.entity.Order;
import io.shardingjdbc.example.spring.boot.mybatis.entity.OrderItem;
import io.shardingjdbc.example.spring.boot.mybatis.repository.OrderItemRepository;
import io.shardingjdbc.example.spring.boot.mybatis.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {

    private static final String STATUS = "INSERT_TEST";

    @Resource
    private OrderRepository orderRepository;
    
    @Resource
    private OrderItemRepository orderItemRepository;
    
    public void demo() {
        orderRepository.createIfNotExistsTable();
        orderItemRepository.createIfNotExistsTable();
        orderRepository.truncateTable();
        orderItemRepository.truncateTable();


        List<Long> orderIds = new ArrayList<>();
        System.out.println("1.Insert--------------");

        for (int userId = 0; userId < 100; userId++) {
            for (int orderCount = 0; orderCount < 10; orderCount++) {
                orderIds.add(insert(userId));
            }
        }

        System.out.println("2.selectAll--------------");
        System.out.println(orderItemRepository.selectAll().size());
//
//        System.out.println("3.Delete--------------");
//        for (Long each : orderIds) {
//            orderRepository.delete(each);
//            orderItemRepository.delete(each);
//        }
//
//        System.out.println("4.selectAll--------------");
//        System.out.println(orderItemRepository.selectAll());
//        orderItemRepository.dropTable();
//        orderRepository.dropTable();
    }

    private long insert(Integer userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(STATUS);
        orderRepository.insert(order);
        long orderId = order.getOrderId();

        OrderItem item = new OrderItem();
        item.setOrderId(orderId);
        item.setUserId(userId);
        item.setStatus(STATUS);
        orderItemRepository.insert(item);

        // System.out.println("userId: " + userId + " orderId:" + orderId);
        return orderId;
    }

}
