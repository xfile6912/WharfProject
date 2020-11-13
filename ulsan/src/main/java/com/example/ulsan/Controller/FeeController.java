package com.example.ulsan.Controller;

import com.example.ulsan.Model.CrudInterface;
import com.example.ulsan.Model.Entity.Company;
import com.example.ulsan.Model.Entity.Fee;
import com.example.ulsan.Model.Network.Header;
import com.example.ulsan.Model.Network.body.FeeBody;
import com.example.ulsan.Model.Network.body.InfoBody;
import com.example.ulsan.Service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
public class FeeController implements CrudInterface<FeeBody> {
    @Autowired
    private FeeService feeService;

    @Override
    @PostMapping("/")
    public Header<FeeBody> create(@RequestBody FeeBody request) {
        return feeService.create(request);//company cannot create fee information
    }

    @Override
    @GetMapping("/{id}")
    public Header<FeeBody> read(@PathVariable Long id) {
        return feeService.read(id);//company can read the fee information
    }

    @GetMapping("/")
    public Header<List<FeeBody> > readAll() {
        return feeService.readAll();
    }

    @Override
    @PutMapping("/")
    public Header<FeeBody> update(@RequestBody FeeBody request) {
        return feeService.update(request);//company cannot update fee
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return feeService.delete(id);//company cannot delete fee
    }
}
