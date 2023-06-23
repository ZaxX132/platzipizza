package com.platzi.pizza.web.controller;

import com.platzi.pizza.persitence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import com.platzi.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @GetMapping()
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page,elements));
    }
    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAllAvailable(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int elements
    ,@RequestParam(defaultValue = "price") String sortBy,@RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page,elements,sortBy,sortDirection));
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
    @GetMapping("/ingridient/{ingr}")
    public ResponseEntity<List<PizzaEntity>> getByIngredient(@PathVariable String ingr){
        return ResponseEntity.ok(pizzaService.getWith(ingr));
    }
    @GetMapping("/notIngridient/{ingr}")
    public ResponseEntity<List<PizzaEntity>> getByNotIngredient(@PathVariable String ingr){
        return ResponseEntity.ok(pizzaService.getWithout(ingr));
    }
    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double price){
        return ResponseEntity.ok(pizzaService.getCheapest(price));
    }

    @PutMapping("/updatePrice")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto pizza) {
        if (this.pizzaService.exists(pizza.getPizzaId())) {
            this.pizzaService.updatePrice(pizza);
            return ResponseEntity.ok().build();
        }
       return ResponseEntity.notFound().build();
    }

}
