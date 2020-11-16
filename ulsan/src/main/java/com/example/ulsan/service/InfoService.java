package com.example.ulsan.service;

import com.example.ulsan.model.CrudInterface;
import com.example.ulsan.model.entity.Info;
import com.example.ulsan.model.network.Header;
import com.example.ulsan.model.network.body.InfoBody;
import com.example.ulsan.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InfoService implements CrudInterface<InfoBody> {

    @Autowired
    private InfoRepository infoRepository;

    @Override
    public Header<InfoBody> create(InfoBody request) {

        Info info=Info.builder()
                .content(request.getContent())
                .name(request.getName())
                .updatedAt(LocalDate.now())
                .build();
        Info returned = infoRepository.save(info);

        return response(returned);
    }

    @Override
    public Header<InfoBody> read(Long id) {
        Optional<Info> info = infoRepository.findById(id);
        return info.map(temp-> response(temp))
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<InfoBody> update(InfoBody request) {
        Info info=infoRepository.getOne(request.getId());
        info.setName(request.getName()).setContent(request.getContent()).setUpdatedAt(LocalDate.now());
        Info returned = infoRepository.save(info);

        return response(returned);
    }

    @Override
    public Header delete(Long id) {
        Optional<Info> info = infoRepository.findById(id);
        return info.map(temp->{infoRepository.delete(temp);
            return Header.OK();}).orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Header<List<InfoBody>> readAll() {
        List<Info> infoList=infoRepository.findAll();
        List<InfoBody> infoBodyList = new ArrayList<>();
        for(Info info:infoList)
        {
            InfoBody temp= InfoBody.builder()
                    .id(info.getId())
                    .content(info.getContent())
                    .name(info.getName())
                    .updatedAt(info.getUpdatedAt())
                    .build();
            infoBodyList.add(temp);
        }
        return Header.OK(infoBodyList);
    }
    public Header<InfoBody> response(Info info){
        InfoBody infobody= InfoBody.builder()
                .id(info.getId())
                .content(info.getContent())
                .name(info.getName())
                .updatedAt(LocalDate.now())
                .build();
        return Header.OK(infobody);
    }
}