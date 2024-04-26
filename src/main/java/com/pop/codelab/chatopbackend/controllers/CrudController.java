package com.pop.codelab.chatopbackend.controllers;

import com.pop.codelab.chatopbackend.controllers.dto.BaseDto;
import com.pop.codelab.chatopbackend.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

public abstract class CrudController<T extends BaseDto> {

    private  CrudService<T> service;


    public CrudController(CrudService<T> crudService) {
        this.service = crudService;
    }


    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<T> optionalT = null;
        try {
            optionalT = service.findById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return optionalT.map(T ->
                        new ResponseEntity<>(T, HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * Saves the object to the server.
     *
     * @param body the object to be saved
     * @return a ResponseEntity containing the saved object and the HTTP status code CREATED (201)
     */
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody T body){
        return new ResponseEntity<>(service.save(body), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<T> optional = null;
        try {
            optional = service.findById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return optional.map(T ->
                        new ResponseEntity<>("Object with the id " + id + " was deleted.", HttpStatus.NO_CONTENT))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody T body){
        Optional<T> optional = null;
        try {
            optional = service.findById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        optional.ifPresent(n -> service.update(id, body));
        return optional.map(n ->
                        new ResponseEntity<>("Object with id " + id + " was updated.", HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND));
    }


    // TODO connect to the controllers to be more generic
//    public static ResponseEntity<?> generateResponse(String message, HttpStatus status, Object responseObj,
//                                                          int count) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("message", message);
//        map.put("status", status.value());
//        map.put("data", responseObj);
//        map.put("Total Character Count", count);
//
//        return new ResponseEntity<Object>(map, status);
//    }

}

