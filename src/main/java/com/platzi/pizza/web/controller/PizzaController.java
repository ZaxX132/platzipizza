package com.platzi.pizza.web.controller;

import com.platzi.pizza.persitence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }
    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAllAvailable(){
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }
    @GetMapping("/byName/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable int id){
        return ResponseEntity.ok(this.pizzaService.getById(id));

    }
    @PostMapping("/new")
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza()==null || !this.pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/update")
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza()!=null && this.pizzaService.exists((pizza.getIdPizza()))  ){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if(this.pizzaService.exists(id)){
            this.pizzaService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
