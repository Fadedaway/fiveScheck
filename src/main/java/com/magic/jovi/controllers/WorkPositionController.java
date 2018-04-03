package com.magic.jovi.controllers;

import com.magic.jovi.services.WorkPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fanjiawei on 2018/4/3
 * 工作站位管理（机器管理）
 */
@RequestMapping(value = "/workPosition")
@RestController
public class WorkPositionController {

    private final WorkPositionService workPositionService;

    @Autowired
    public WorkPositionController(WorkPositionService workPositionService) {
        this.workPositionService = workPositionService;
    }
}
