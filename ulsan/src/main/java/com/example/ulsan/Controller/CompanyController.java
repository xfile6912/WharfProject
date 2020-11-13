package com.example.ulsan.Controller;

import com.example.ulsan.Model.CrudInterface;
import com.example.ulsan.Model.Entity.Company;
import com.example.ulsan.Model.Network.Header;
import com.example.ulsan.Model.Network.body.CompanyBody;
import com.example.ulsan.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController implements CrudInterface<CompanyBody> {

    @Autowired
    private CompanyService companyService;

    @Override
    @PostMapping("/")
    public Header<CompanyBody> create(@RequestBody CompanyBody request) {
        return companyService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<CompanyBody> read(@PathVariable Long id) {
        return companyService.read(id);
    }

    @GetMapping("/")
    public Header<List<CompanyBody>> readAll() {
        return companyService.readAll();
    }

    @Override
    @PutMapping("/")
    public Header<CompanyBody> update(@RequestBody CompanyBody request) {
        return companyService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return companyService.delete(id);
    }
}
