package com.example.controller;

import com.example.model.Contract;
import com.example.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping ("/api/contracts")
public class RestContract {
    @Autowired
    private IContractService iContractService;

    @GetMapping ("/")
    public ResponseEntity<List<Contract>> showList (){
        List<Contract> contracts = iContractService.findAll();
        if (contracts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(contracts,HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity <String> create (@RequestBody Contract contract){
        iContractService.save(contract);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success Created");
    }
}
