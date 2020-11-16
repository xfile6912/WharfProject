package com.example.ulsan.controller;

import com.example.ulsan.model.CrudInterface;
import com.example.ulsan.model.network.Header;
import com.example.ulsan.model.network.body.InfoBody;
import com.example.ulsan.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController implements CrudInterface<InfoBody> {

    @Autowired
    private InfoService infoService;

    @Override
    @PostMapping("")
    public Header<InfoBody> create(@RequestBody InfoBody request) {
        return infoService.create(request);
    }


    @GetMapping("")
    public Header<List<InfoBody> > readAll() {
        return infoService.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public Header<InfoBody> read(@PathVariable Long id) {
        return infoService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<InfoBody> update(@RequestBody InfoBody request) {
        return infoService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return infoService.delete(id);
    }
}
