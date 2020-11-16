package com.example.ulsan.controller;

import com.example.ulsan.model.CrudInterface;
import com.example.ulsan.model.network.Header;
import com.example.ulsan.model.network.body.FeeBody;
import com.example.ulsan.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
public class FeeController implements CrudInterface<FeeBody> {
    @Autowired
    private FeeService feeService;

    @Override
    @PostMapping("")
    public Header<FeeBody> create(@RequestBody FeeBody request) {
        return feeService.create(request);//company cannot create fee information
    }

    @Override
    @GetMapping("/{id}")
    public Header<FeeBody> read(@PathVariable Long id) {
        return feeService.read(id);//company can read the fee information
    }

    @GetMapping("")
    public Header<List<FeeBody> > readAll() {
        return feeService.readAll();
    }

    @Override
    @PutMapping("")
    public Header<FeeBody> update(@RequestBody FeeBody request) {
        return feeService.update(request);//company cannot update fee
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return feeService.delete(id);//company cannot delete fee
    }
}
