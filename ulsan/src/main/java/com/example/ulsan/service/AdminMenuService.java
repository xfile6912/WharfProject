package com.example.ulsan.service;

import com.example.ulsan.model.front.AdminMenu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AdminMenuService {

    public List<AdminMenu> getAdminMenu(){
        List<AdminMenu> list=new ArrayList<AdminMenu>();
        list.add(AdminMenu.builder().title("사용 조회").url("/pages/usage").code("usage").build());
        list.add(AdminMenu.builder().title("신청 조회").url("/pages/order").code("order").build());
        return list;

    }

}
