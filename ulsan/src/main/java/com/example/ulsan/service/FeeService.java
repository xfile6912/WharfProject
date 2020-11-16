package com.example.ulsan.service;

import com.example.ulsan.model.CrudInterface;
import com.example.ulsan.model.entity.Fee;
import com.example.ulsan.model.network.Header;
import com.example.ulsan.model.network.body.FeeBody;
import com.example.ulsan.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeeService implements CrudInterface<FeeBody> {
    @Autowired
    FeeRepository feeRepository;

    @Override
    public Header<FeeBody> create(FeeBody request) {
        Fee fee=Fee.builder()
                .name(request.getName())
                .kind(request.getKind())
                .fee(request.getFee())
                .build();
        Fee returned = feeRepository.save(fee);
        return response(returned);
    }
    @Override
    public Header<FeeBody> read(Long id) {
        Optional<Fee> fee = feeRepository.findById(id);
        return fee.map(temp-> response(temp))
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<FeeBody> update(FeeBody request) {
        Fee fee=feeRepository.getOne(request.getId());
        fee.setName(request.getName())
                .setKind(request.getKind())
                .setFee(request.getFee());
        Fee returned = feeRepository.save(fee);
        return response(returned);
    }

    @Override
    public Header delete(Long id) {
        Optional<Fee> fee = feeRepository.findById(id);
        return fee.map(temp->{feeRepository.delete(temp);
            return Header.OK();}).orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Header<List<FeeBody>> readAll() {
        List<Fee> feeList=feeRepository.findAll();
        List<FeeBody> feeBodyList = new ArrayList<>();
        for(Fee fee:feeList)
        {
            FeeBody temp=FeeBody.builder()
                    .id(fee.getId())
                    .name(fee.getName())
                    .kind(fee.getKind())
                    .fee(fee.getFee())
                    .build();
            feeBodyList.add(temp);
        }
        return Header.OK(feeBodyList);
    }
    private Header<FeeBody> response(Fee fee) {
        FeeBody feeBody=FeeBody.builder()
                .id(fee.getId())
                .name(fee.getName())
                .kind(fee.getKind())
                .fee(fee.getFee())
                .build();
        return Header.OK(feeBody);
    }

}