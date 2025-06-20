package com.project.demo.controller;

import com.project.demo.entity.OrderingOrder;
import com.project.demo.service.OrderingOrderService;
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
 *点餐订单：(OrderingOrder)表控制层
 *
 */
@RestController
@RequestMapping("/ordering_order")
public class OrderingOrderController extends BaseController<OrderingOrder,OrderingOrderService> {

    /**
     *点餐订单对象
     */
    @Autowired
    public OrderingOrderController(OrderingOrderService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        String sql = "SELECT MAX(ordering_order_id) AS max FROM "+"ordering_order";
        Query select = service.runCountSql(sql);
        Integer max = (Integer) select.getSingleResult();
        sql = "UPDATE `opening_information` INNER JOIN `ordering_order` ON opening_information.order_number=ordering_order.order_number SET opening_information.consumption_amount = opening_information.consumption_amount + ordering_order.dish_price WHERE ordering_order.ordering_order_id="+max;
        select = service.runCountSql(sql);
        select.executeUpdate();
        return success(1);
    }

}
