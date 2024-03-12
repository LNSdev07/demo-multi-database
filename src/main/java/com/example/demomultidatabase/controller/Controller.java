package com.example.demomultidatabase.controller;

import com.example.demomultidatabase.model.order.Order;
import com.example.demomultidatabase.model.user.User;
import com.example.demomultidatabase.repo.order.OrderRepository;
import com.example.demomultidatabase.repo.user.UserRepository;
import lombok.Data;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OrderRepository orderRepo;


    @PostMapping("/create-user")
    public ResponseEntity createUsers(@RequestBody User user){
        user = userRepo.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create-order")
    public ResponseEntity createOrders(@RequestBody Order order){
        order = orderRepo.save(order);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/create-generate")
    @Transactional
    public ResponseEntity createCommon(@RequestBody UserOrderRequest request
                                       ){
        // nếu cả 2 transaction quản lý chung bởi 1 @Transaction thì khi có lỗi sẽ rollback
        User user = userRepo.save(request.getUser());
        Order order = orderRepo.save(request.getOrder());
        CommonResponse response = new CommonResponse();
        response.setOrder(order);
        response.setUser(user);
        return ResponseEntity.ok(response);
    }

    @Data
    static class CommonResponse{
        private User user;
        private Order order;
        
    }

    @Data
    static class UserOrderRequest{
        private User user;
        private Order order;
    }
}
