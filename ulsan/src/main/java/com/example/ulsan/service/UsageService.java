package com.example.ulsan.service;

import com.example.ulsan.model.CrudInterface;
import com.example.ulsan.model.entity.Usages;
import com.example.ulsan.model.network.Header;
import com.example.ulsan.model.network.Pagination;
import com.example.ulsan.model.network.body.UsageBody;
import com.example.ulsan.repository.CompanyRepository;
import com.example.ulsan.repository.UsageRepository;
import com.example.ulsan.repository.WharfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsageService implements CrudInterface<UsageBody> {
    @Autowired
    UsageRepository usageRepository;
    @Autowired
    WharfRepository wharfRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Override
    public Header<UsageBody> create(UsageBody request) {
        Usages usages =bodyToUsage(request);
        Usages returned=usageRepository.save(usages);
        return response(returned);

    }

    @Override
    public Header<UsageBody> read(Long id) {
        Optional<Usages> usage = usageRepository.findById(id);
        return usage.map(temp-> response(temp))
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UsageBody> update(UsageBody request) {
        Usages usages =usageRepository.getOne(request.getId());
        usages.setArea(request.getArea())
                .setCompany(companyRepository.getOne(request.getCompanyId()))
                .setStartDate(request.getStartDate())
                .setEndDate(request.getEndDate())
                .setWharf(wharfRepository.getOne(request.getWharfId()));
        Usages returned = usageRepository.save(usages);
        return response(returned);
    }

    @Override
    public Header delete(Long id) {
        Optional<Usages> usage = usageRepository.findById(id);
        return usage.map(temp->{usageRepository.delete(temp);
            return Header.OK();}).orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Usages bodyToUsage(UsageBody request)
    {
        Usages usages = Usages.builder()
                .area(request.getArea())
                .company(companyRepository.getOne(request.getCompanyId()))
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .wharf(wharfRepository.getOne(request.getWharfId()))
                .build();
        return usages;
    }

    public UsageBody usageToBody(Usages usages)
    {
        UsageBody usageBody = UsageBody.builder()
                .area(usages.getArea())
                .companyId(usages.getCompany().getId())
                .company(usages.getCompany().getName())
                .wharf(usages.getWharf().getName())
                .wharfKind(usages.getWharf().getKind())
                .startDate(usages.getStartDate())
                .endDate(usages.getEndDate())
                .wharfId(usages.getWharf().getId())
                .id(usages.getId())
                .build();
        return usageBody;
    }
    public Header<UsageBody> response(Usages usages) {
        UsageBody usageBody = usageToBody(usages);
        return Header.OK(usageBody);
    }

    public Header<List<UsageBody>> readAll(Pageable pageable) {
        Page<Usages> usagesList =usageRepository.findAll(pageable);
        List<UsageBody> usageBodyList = new ArrayList<>();
        for(Usages usages : usagesList)
        {
            UsageBody usageBody = usageToBody(usages);
            usageBodyList.add(usageBody);
        }
        Pagination pagination= Pagination.builder()
                .totalPages(usagesList.getTotalPages())
                .totalElements(usagesList.getTotalElements())
                .currentPage(usagesList.getNumber())
                .currentElements(usagesList.getNumberOfElements())
                .build();

        return Header.OK(usageBodyList, pagination);
    }
}
