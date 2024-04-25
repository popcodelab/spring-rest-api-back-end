package com.pop.codelab.chatopbackend.controllers;

import com.pop.codelab.chatopbackend.controllers.dto.BaseDTO;
import com.pop.codelab.chatopbackend.message.MessageService;
import com.pop.codelab.chatopbackend.service.CrudService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class CrudController<T extends BaseDTO> {

    private  CrudService<T> service;


    public CrudController(CrudService<T> crudService) {
        this.service = crudService;
    }


    @GetMapping("/")
    public ResponseEntity<List<T>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable Long id){
        Optional<T> optionalT = service.findById(id);

        return optionalT.map(T ->
                        new ResponseEntity<>(T, HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * Saves an object of type T and returns a custom ResponseEntity.
     * Used to produce custom ResponseEntity using another type as T
     *
     * @param body the object to be saved
     * @return a custom ResponseEntity containing the saved object and the HTTP status code CREATED (201)
     */

//    @PostMapping("/")
//    public ResponseEntity<?> saveWithCustomResponseEntity(@RequestBody T body){
//            return new ResponseEntity<>(service.save(body), HttpStatus.CREATED);
//    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody T body){
        return new ResponseEntity<>(service.save(body), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<T> optional = service.findById(id);

        return optional.map(T ->
                        new ResponseEntity<>("Object with the id " + id + " was deleted.", HttpStatus.NO_CONTENT))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody T body){
        Optional<T> optional = service.findById(id);
        optional.ifPresent(n -> service.update(id, body));
        return optional.map(n ->
                        new ResponseEntity<>("Object with id " + id + " was updated.", HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND));
    }

}

