package com.project.demo.controller;

import com.project.demo.entity.DishCategory;
import com.project.demo.service.DishCategoryService;
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
 *菜品类别：(DishCategory)表控制层
 *
 */
@RestController
@RequestMapping("/dish_category")
public class DishCategoryController extends BaseController<DishCategory,DishCategoryService> {

    /**
     *菜品类别对象
     */
    @Autowired
    public DishCategoryController(DishCategoryService service) {
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
