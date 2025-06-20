package com.project.demo.controller;

import com.project.demo.entity.OpeningInformation;
import com.project.demo.service.OpeningInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *开台信息：(OpeningInformation)表控制层
 *
 */
@RestController
@RequestMapping("/opening_information")
public class OpeningInformationController extends BaseController<OpeningInformation,OpeningInformationService> {

    /**
     *开台信息对象
     */
    @Autowired
    public OpeningInformationController(OpeningInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
