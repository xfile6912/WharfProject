package com.example.ulsan.Controller;

import com.example.ulsan.Model.CrudInterface;
import com.example.ulsan.Model.Network.Header;
import com.example.ulsan.Model.Network.body.UsageBody;
import com.example.ulsan.Service.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage")
public class UsageController implements CrudInterface<UsageBody> {

    @Autowired
    private UsageService usageService;

    @Override
    @PostMapping("/")
    public Header<UsageBody> create(@RequestBody UsageBody request) {
        return usageService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<UsageBody> read(@PathVariable Long id) {
        return usageService.read(id);
    }

    @GetMapping("/")
    public Header<List<UsageBody>> readAll() {
        return usageService.readAll();
    }

    @Override
    @PutMapping("/")
    public Header<UsageBody> update(@RequestBody UsageBody request) {
        return usageService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return usageService.delete(id);
    }
}