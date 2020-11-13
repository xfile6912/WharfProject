package com.example.ulsan.Controller;

import com.example.ulsan.Model.CrudInterface;
import com.example.ulsan.Model.Network.Header;
import com.example.ulsan.Model.Network.body.OrderBody;
import com.example.ulsan.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController implements CrudInterface<OrderBody> {

    @Autowired
    private OrderService orderService;

    @Override
    @PostMapping("/")
    public Header<OrderBody> create(@RequestBody OrderBody request) {
        return orderService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    public Header<OrderBody> read(@PathVariable Long id) {
        return orderService.read(id);
    }

    @GetMapping("/")
    public Header<List<OrderBody>> readAll() {
        return orderService.readAll();
    }

    @Override
    @PutMapping("/")
    public Header<OrderBody> update(@RequestBody OrderBody request) {
        return orderService.update(request);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable Long id) {
        return orderService.delete(id);
    }
}