package com.project.demo.controller;

import com.project.demo.entity.SeatInformation;
import com.project.demo.service.SeatInformationService;
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
 *座位信息：(SeatInformation)表控制层
 *
 */
@RestController
@RequestMapping("/seat_information")
public class SeatInformationController extends BaseController<SeatInformation,SeatInformationService> {

    /**
     *座位信息对象
     */
    @Autowired
    public SeatInformationController(SeatInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapseat_number = new HashMap<>();
        mapseat_number.put("seat_number",String.valueOf(paramMap.get("seat_number")));
        List listseat_number = service.select(mapseat_number, new HashMap<>()).getResultList();
        if (listseat_number.size()>0){
            return error(30000, "字段座位号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
