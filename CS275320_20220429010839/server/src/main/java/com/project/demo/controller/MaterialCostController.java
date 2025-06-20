package com.project.demo.controller;

import com.project.demo.entity.MaterialCost;
import com.project.demo.service.MaterialCostService;
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
 *材料成本：(MaterialCost)表控制层
 *
 */
@RestController
@RequestMapping("/material_cost")
public class MaterialCostController extends BaseController<MaterialCost,MaterialCostService> {

    /**
     *材料成本对象
     */
    @Autowired
    public MaterialCostController(MaterialCostService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapmaterial_number = new HashMap<>();
        mapmaterial_number.put("material_number",String.valueOf(paramMap.get("material_number")));
        List listmaterial_number = service.select(mapmaterial_number, new HashMap<>()).getResultList();
        if (listmaterial_number.size()>0){
            return error(30000, "字段材料编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
