package com.example.ulsan.controller.page;

import com.example.ulsan.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping(path = {""})
    public ModelAndView index() {
        return new ModelAndView("/pages/main")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main")
                ;
    }

    @RequestMapping("/usage")
    public ModelAndView usage() {
        return new ModelAndView("/pages/usage")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "usage")
                ;
    }
    @RequestMapping("/order")
    public ModelAndView order() {
        return new ModelAndView("/pages/order")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "order")
                ;
    }
}
