package com.example.ulsan.controller;

import com.example.ulsan.model.CrudInterface;
import com.example.ulsan.model.network.Header;
import com.example.ulsan.model.network.body.UsageBody;
import com.example.ulsan.service.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage")
public class UsageController implements CrudInterface<UsageBody> {

    @Autowired
    private UsageService usageService;

    @Override
    @PostMapping("")
    public Header<UsageBody> create(@RequestBody UsageBody request) {
        return usageService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<UsageBody> read(@PathVariable Long id) {
        return usageService.read(id);
    }

    @GetMapping("")
    public Header<List<UsageBody>> readAll(@PageableDefault(sort="id", direction= Sort.Direction.DESC, size=10) Pageable pageable) {
        return usageService.readAll(pageable);
    }

    @Override
    @PutMapping("")
    public Header<UsageBody> update(@RequestBody UsageBody request) {
        return usageService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return usageService.delete(id);
    }
}