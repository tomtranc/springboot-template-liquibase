package com.ttran.example.rest;

import com.ttran.example.model.dto.Customer;
import com.ttran.example.rest.base.BaseRestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttran.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
public class CustomerController extends BaseRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String index(HttpServletRequest request) throws UnknownHostException {
        return "Application is up at: http://" + InetAddress.getLocalHost().getHostAddress() + ":" + serverPort;
    }

    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> list(HttpServletRequest request) {
        return customerService.list();
    }

    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer postEndpoint(@Valid @RequestBody Customer dto, Errors errors) {
        if (errors.hasErrors()) {
            final String errorMessage = getErrorMessage(errors.getAllErrors());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }

        return customerService.create(dto);
    }

    @PutMapping(value = "/customer/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Customer putEndpoint(@PathVariable String id, @Valid @RequestBody Customer dto, Errors errors) {
        if (errors.hasErrors()) {
            final String errorMessage = getErrorMessage(errors.getAllErrors());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
        return customerService.update(dto);
    }

    @DeleteMapping(value = "/customer/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEndpoint(@PathVariable String id) {
        customerService.delete(id);
    }
}