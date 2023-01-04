package com.atguigu.webapp.demo.controller;

import com.atguigu.webapp.demo.bean.OrderInfo;
import com.atguigu.webapp.demo.service.OrderService;
import com.atguigu.webapp.demo.service.impl.OrderServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;



@RestController   //标识controller入口类   //1 接收请求 包括参数   2 返回结果
public class DemoController {


    @Autowired    //装配业务类  //其实这个话可以加一个@qualifier("Orderservice1\2\3\4")表示使用不同的实现类方法（如果有多个的话）
    OrderService orderService;


    @RequestMapping("/hello")  //标识访问路径
    public  String getHelloWorld(@RequestParam("name") String name ,@RequestParam("age") Integer age){  //装载参数
            return "hello world:"+name+","+age+" 岁";    //返回值
    }

    @RequestMapping("/order/{id}")  //标识访问路径
    public  String getOrder(@PathVariable("id") String id){  //装载参数
        return "order:"+id;    //返回值
    }
    //PathVariable  可变，任务值都封装在里面。连接就不是通过=赋值的方式，而是localhost:8080/order/18  就是这个18了

    //或者写成@RequestMapping(value = "/order"，method=RequertMethod.POST)
    @PostMapping(value = "/order") // post请求  一般用于用户提交写操作
    public String saveOrder(@RequestBody OrderInfo orderInfo){ //封装到结构化对象中  map 或者 bean
       // orderService.saveOrder(orderInfo);  //可以保存数据库
    //注意对应post请求，不同通过连接去赋值返回结果，要通过用户界面话的操作才行。这里我们通过小工具实现类似连接赋值的方式实现
        orderService.save(orderInfo);
        return  "success";
    }

    @GetMapping("/orders")
    public List<OrderInfo> orderList(@RequestParam("gt") BigDecimal gtAmount){
        List<OrderInfo> orderInfoList = orderService.list(new QueryWrapper<OrderInfo>().gt("amount", gtAmount));
        return orderInfoList;
    }


}
