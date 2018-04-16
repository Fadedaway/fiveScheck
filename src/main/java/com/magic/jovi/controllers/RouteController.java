package com.magic.jovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by fanjiawei on 2018/4/16
 * 页面跳转配置
 */
@Controller
@RequestMapping
public class RouteController {

    @RequestMapping()
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/collect")
    public String jumpToCollector(@RequestParam String dateValue, @RequestParam Long groupId, @RequestParam String groupName, Model model) {
        model.addAttribute("dateValue", dateValue);
        model.addAttribute("groupId", groupId);
        model.addAttribute("groupName", groupName);

        return "collect";
    }
}
