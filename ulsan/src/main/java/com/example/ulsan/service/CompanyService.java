package com.example.ulsan.service;

import com.example.ulsan.model.CrudInterface;
import com.example.ulsan.model.entity.Company;
import com.example.ulsan.model.entity.Orders;
import com.example.ulsan.model.entity.Usages;
import com.example.ulsan.model.network.Header;
import com.example.ulsan.model.network.body.CompanyBody;
import com.example.ulsan.model.network.body.OrderBody;
import com.example.ulsan.model.network.body.UsageBody;
import com.example.ulsan.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService implements CrudInterface<CompanyBody> {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    UsageService usageService;
    @Autowired
    private OrderService orderService;

    @Override
    public Header<CompanyBody> create(CompanyBody request) {
        Company company=Company.builder()
                .code(request.getCode())
                .name(request.getName())
                .warnCount(0)
                .build();
        Company returned = companyRepository.save(company);

        return response(returned);
    }

    @Override
    public Header<CompanyBody> read(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        System.out.println(company);
        return company.map(temp-> response(temp))
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Header<List<CompanyBody>> readAll() {

        List<Company> companyList=companyRepository.findAll();
        List<CompanyBody> companyBodyList=new ArrayList<>();

        for(Company company:companyList)
        {
            CompanyBody companyBody=CompanyBody.builder()
                    .id(company.getId())
                    .code(company.getCode())
                    .name(company.getName())
                    .warnCount(company.getWarnCount())
                    .build();
            List<Orders> ordersList = company.getOrdersList();
            List<Usages> usagesList =company.getUsagesList();
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
            companyBody.setOrderBodyList(orderBodyList);
            companyBody.setUsageBodyList(usageBodyList);

            companyBodyList.add(companyBody);
        }
        return Header.OK(companyBodyList.stream().collect(Collectors.toList()));
    }
    @Override
    public Header<CompanyBody> update(CompanyBody request) {
        Company company=companyRepository.getOne(request.getId());
        company.setCode(request.getCode())
                .setName(request.getName())
                .setWarnCount(request.getWarnCount());
        Company returned=companyRepository.save(company);
        return response(returned);
    }

    @Override
    public Header delete(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(temp-> {
            companyRepository.delete(temp);
            return response(temp);
        }).orElseGet(()-> Header.ERROR("데이터 없음"));
    }
    public Header<CompanyBody> response(Company company){
        CompanyBody companyBody = CompanyBody
                .builder()
                .id(company.getId())
                .code(company.getCode())
                .name(company.getName())
                .warnCount(company.getWarnCount())
                .build();
        List<Orders> ordersList = company.getOrdersList();
        List<Usages> usagesList =company.getUsagesList();
        if(ordersList !=null) {
            List<OrderBody> orderBodyList = ordersList.stream()
                    .map(order -> {
                        return orderService.response(order).getData();
                    })
                    .collect(Collectors.toList());
            companyBody.setOrderBodyList(orderBodyList);
        }
        if(usagesList !=null) {
            List<UsageBody> usageBodyList = usagesList.stream()
                    .map(useInfo -> {
                        return usageService.response(useInfo).getData();
                    })
                    .collect(Collectors.toList());
            companyBody.setUsageBodyList(usageBodyList);
        }


        return Header.OK(companyBody);
    }

}
