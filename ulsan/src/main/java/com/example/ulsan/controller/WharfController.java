package com.example.ulsan.controller;

import com.example.ulsan.model.CrudInterface;
import com.example.ulsan.model.network.Header;
import com.example.ulsan.model.network.body.WharfBody;
import com.example.ulsan.service.WharfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wharf")
public class WharfController implements CrudInterface<WharfBody> {

    @Autowired
    private WharfService wharfService;

    @Override
    @PostMapping("")
    public Header<WharfBody> create(@RequestBody WharfBody request) {
        return wharfService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<WharfBody> read(@PathVariable Long id) {
        return wharfService.read(id);
    }

    @GetMapping("")
    public Header<List<WharfBody>> readAll() {
        return wharfService.readAll();
    }

    @Override
    @PutMapping("")
    public Header<WharfBody> update(@RequestBody WharfBody request) {
        return wharfService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return wharfService.delete(id);
    }
}
