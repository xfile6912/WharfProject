package com.example.ulsan.Controller;

import com.example.ulsan.Model.CrudInterface;
import com.example.ulsan.Model.Entity.Company;
import com.example.ulsan.Model.Entity.Wharf;
import com.example.ulsan.Model.Network.Header;
import com.example.ulsan.Model.Network.body.CompanyBody;
import com.example.ulsan.Model.Network.body.WharfBody;
import com.example.ulsan.Service.WharfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wharf")
public class WharfController implements CrudInterface<WharfBody> {

    @Autowired
    private WharfService wharfService;

    @Override
    @PostMapping("/")
    public Header<WharfBody> create(@RequestBody WharfBody request) {
        return wharfService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<WharfBody> read(@PathVariable Long id) {
        return wharfService.read(id);
    }

    @GetMapping("/")
    public Header<List<WharfBody>> readAll() {
        return wharfService.readAll();
    }

    @Override
    @PutMapping("/")
    public Header<WharfBody> update(@RequestBody WharfBody request) {
        return wharfService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return wharfService.delete(id);
    }
}
