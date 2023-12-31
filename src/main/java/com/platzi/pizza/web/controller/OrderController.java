package com.platzi.pizza.web.controller;

import com.platzi.pizza.persitence.entity.OrderEntity;
import com.platzi.pizza.persitence.projection.OrderSummary;
import com.platzi.pizza.service.OrderService;
import com.platzi.pizza.service.dto.RandomOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping()
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }
    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders(){
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }
    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutside(){
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }
    @GetMapping("/byCustomer/{id}")
    public ResponseEntity<List<OrderEntity>> getByCustomer(@PathVariable String id){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }
    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getOrderSummary(@PathVariable int id){
        return ResponseEntity.ok(this.orderService.getSummary(id));
    }
    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto dto){
        return ResponseEntity.ok(this.orderService.saveRandomOrder(dto));
    }
}
