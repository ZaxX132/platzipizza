package com.platzi.pizza.service;

import com.platzi.pizza.persitence.entity.PizzaEntity;
import com.platzi.pizza.persitence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository=pizzaRepository;
    }
    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }
    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }
    public List<PizzaEntity> getAll(){
        return this.pizzaRepository.findAll();
    }
    public PizzaEntity getById(int id){
        return this.pizzaRepository.findById(id).orElse(null);
    }
    public PizzaEntity save(PizzaEntity pizzaEntity){
        return this.pizzaRepository.save(pizzaEntity);
    }
    public boolean exists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
    public void delete(int id){
        this.pizzaRepository.deleteById(id);
    }
}
