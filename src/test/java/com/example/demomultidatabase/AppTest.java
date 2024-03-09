package com.example.demomultidatabase;


import com.example.demomultidatabase.model.order.Order;
import com.example.demomultidatabase.model.user.User;
import com.example.demomultidatabase.repo.order.OrderRepository;
import com.example.demomultidatabase.repo.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AppTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrderRepository orderRepository;
  
  @Test
  void saveUserDataInDB() {

    User user = new User();
    user.setEmail("johndoe@gmail.com");
    user.setFirstName("John");
    user.setLastName("DOe");

    User savedUser = userRepository.save(user);
    Optional<User> userData = userRepository.findById(savedUser.getId());
    assertTrue(userData.isPresent());

  }

  @Test
  void saveOrderDataInDB() {

    Order order = new Order();
    order.setProductName("Mobile");
    order.setOrderAmount(200);
    order.setUserId(1);

    Order savedOrder = orderRepository.save(order);
    Optional<Order> orderData = orderRepository.findById(savedOrder.getId());
    assertTrue(orderData.isPresent());
  }

}
