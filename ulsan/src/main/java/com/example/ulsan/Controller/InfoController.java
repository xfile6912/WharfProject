package com.example.ulsan.Controller;

import com.example.ulsan.Model.CrudInterface;
import com.example.ulsan.Model.Entity.Company;
import com.example.ulsan.Model.Entity.Info;
import com.example.ulsan.Model.Network.Header;
import com.example.ulsan.Model.Network.body.InfoBody;
import com.example.ulsan.Service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController implements CrudInterface<InfoBody> {

    @Autowired
    private InfoService infoService;

    @Override
    @PostMapping("/")
    public Header<InfoBody> create(@RequestBody InfoBody request) {
        return infoService.create(request);
    }


    @GetMapping("/")
    public Header<List<InfoBody> > readAll() {
        return infoService.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public Header<InfoBody> read(@PathVariable Long id) {
        return infoService.read(id);
    }

    @Override
    @PutMapping("/")
    public Header<InfoBody> update(@RequestBody InfoBody request) {
        return infoService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return infoService.delete(id);
    }
}
