package com.project.demo.controller;

import com.project.demo.entity.MenuManagement;
import com.project.demo.service.MenuManagementService;
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
 *菜谱管理：(MenuManagement)表控制层
 *
 */
@RestController
@RequestMapping("/menu_management")
public class MenuManagementController extends BaseController<MenuManagement,MenuManagementService> {

    /**
     *菜谱管理对象
     */
    @Autowired
    public MenuManagementController(MenuManagementService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapdish_no = new HashMap<>();
        mapdish_no.put("dish_no",String.valueOf(paramMap.get("dish_no")));
        List listdish_no = service.select(mapdish_no, new HashMap<>()).getResultList();
        if (listdish_no.size()>0){
            return error(30000, "字段菜品编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
