package com.example.ulsan.service;

import com.example.ulsan.model.CrudInterface;
import com.example.ulsan.model.entity.Orders;
import com.example.ulsan.model.entity.Usages;
import com.example.ulsan.model.entity.Wharf;
import com.example.ulsan.model.network.Header;
import com.example.ulsan.model.network.body.OrderBody;
import com.example.ulsan.model.network.body.UsageBody;
import com.example.ulsan.model.network.body.WharfBody;
import com.example.ulsan.repository.WharfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WharfService implements CrudInterface<WharfBody> {
    @Autowired
    WharfRepository wharfRepository;
    @Autowired
    UsageService usageService;
    @Autowired
    private OrderService orderService;
    @Override
    public Header<WharfBody> create(WharfBody request) {
        Wharf wharf=Wharf.builder()
                .name(request.getName())
                .kind(request.getKind())
                .area(request.getArea())
                .exemptionArea(request.getExemptionArea())
                .build();
        Wharf returned=wharfRepository.save(wharf);
        return response(returned);
    }


    @Override
    public Header<WharfBody> read(Long id) {
        Optional<Wharf> wharf = wharfRepository.findById(id);

        System.out.println(wharf);
        return wharf.map(temp-> response(temp))
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<WharfBody> update(WharfBody request) {
        Wharf wharf=wharfRepository.getOne(request.getId());
        wharf.setName(request.getName())
                .setKind(request.getKind())
                .setArea(request.getArea())
                .setExemptionArea(request.getExemptionArea());
        Wharf returned=wharfRepository.save(wharf);
        return response(returned);
    }

    @Override
    public Header delete(Long id) {
        Optional<Wharf> wharf = wharfRepository.findById(id);
        return wharf.map(temp-> {
            wharfRepository.delete(temp);
            return response(temp);
        }).orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    private Header<WharfBody> response(Wharf wharf) {
         WharfBody wharfBody = WharfBody
                .builder()
                .id(wharf.getId())
                .name(wharf.getName())
                 .kind(wharf.getKind())
                .area(wharf.getArea())
                .exemptionArea(wharf.getExemptionArea())
                .build();
        List<Orders> ordersList = wharf.getOrdersList();
        List<Usages> usagesList =wharf.getUsagesList();
        if(ordersList !=null) {
            List<OrderBody> orderBodyList = ordersList.stream()
                    .map(order -> {
                        return orderService.response(order).getData();
                    })
                    .collect(Collectors.toList());
            wharfBody.setOrderBodyList(orderBodyList);
        }
        if(usagesList !=null) {
            List<UsageBody> usageBodyList = usagesList.stream()
                    .map(useInfo -> {
                        return usageService.response(useInfo).getData();
                    })
                    .collect(Collectors.toList());
            wharfBody.setUsageBodyList(usageBodyList);
        }


        return Header.OK(wharfBody);
    }

    public Header<List<WharfBody>> readAll() {
        List<Wharf> wharfList=wharfRepository.findAll();
        List<WharfBody> wharfBodyList=new ArrayList<>();

        for(Wharf wharf:wharfList)
        {
            WharfBody wharfBody = WharfBody
                    .builder()
                    .id(wharf.getId())
                    .name(wharf.getName())
                    .kind(wharf.getKind())
                    .area(wharf.getArea())
                    .exemptionArea(wharf.getExemptionArea())
                    .build();
            List<Orders> ordersList = wharf.getOrdersList();
            List<Usages> usagesList =wharf.getUsagesList();
            List<OrderBody> orderBodyList= ordersList.stream()
                    .map(order->{
                        return orderService.response(order).getData();
                    })
                    .collect(Collectors.toList());
            List<UsageBody> usageBodyList = usagesList.stream()
                    .map(useInfo -> {
                        return usageService.response(useInfo).getData();
                    })
                    .collect(Collectors.toList());
            wharfBody.setOrderBodyList(orderBodyList);
            wharfBody.setUsageBodyList(usageBodyList);

            wharfBodyList.add(wharfBody);
        }
        return Header.OK(wharfBodyList.stream().collect(Collectors.toList()));
    }
}
