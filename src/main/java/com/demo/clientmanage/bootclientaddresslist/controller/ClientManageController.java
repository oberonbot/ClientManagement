package com.demo.clientmanage.bootclientaddresslist.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.clientmanage.bootclientaddresslist.bean.Client;
import com.demo.clientmanage.bootclientaddresslist.bean.User;
import com.demo.clientmanage.bootclientaddresslist.service.ClientService;
import com.demo.clientmanage.bootclientaddresslist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ClientManageController {

    @Autowired
    JdbcTemplate testjdbc;

    @Autowired
    ClientService clientService;

    @Autowired
    UserService userService;


    @ResponseBody
    @GetMapping(value = {"/DisplayClientInfo"})  //从数据库读数据并返回JSON
    public String DisplayClientInfo(String info_per_page, String start){ //由于有分页功能，因此需要获取当前页码和数据条数
//        int start_temp = (Integer.parseInt(start) - 1) * Integer.parseInt(info_per_page);
//        List<Map<String, Object>> data = testjdbc.queryForList("select * from client " + "limit " + start_temp + "," + info_per_page);
        Page<Client> clientPage = new Page<>(Integer.parseInt(start), Integer.parseInt(info_per_page));
        Page<Client> page = clientService.page(clientPage, null);
        List<Client> data = page.getRecords();
//        int total = testjdbc.queryForObject("select count(*) from client",int.class);
        return getString(data);
    }


    @GetMapping(value = "/AddClientInfo")
    public String AddClientInfo(String clientId, String clientName, String clientPhone){
//        String sql = "insert into client values ('" + clientId + "','" + clientName + "','" + clientPhone + "')";
        Client client = new Client(clientId, clientName, clientPhone);
        clientService.save(client);
//        testjdbc.execute(sql);
        return "redirect:/";
    }

    @GetMapping(value = "/DeleteClientInfo")
    public String DeleteClientInfo(String id, String name, String phone){
//        String sql = "delete from client where id='" + id + "'and name='" + name + "' and phone='" + phone + "'";

        QueryWrapper<Client> removeWrapper = new QueryWrapper();
        removeWrapper
                .eq("id", id)
                .eq("name", name)
                .eq("phone", phone);

        clientService.remove(removeWrapper);
//        testjdbc.execute(sql);
        return "redirect:/";
    }

    @GetMapping(value = "/EditClientInfo")
    public String EditClientInfo(String id_after, String name_after, String phone_after, String id_before, String name_before, String phone_before){
//        String sql = "update client set id=" + id_after + ","
//                      + " name='" + name_after + "',"
//                      + " phone=" + phone_after
//                      + " where id=" + id_before
//                      + " and name='" + name_before
//                      + "' and phone=" + phone_before;
//        testjdbc.execute(sql);
        clientService.updateClient(id_after,name_after,phone_after,id_before,name_before,phone_before);
        return "redirect:/";
    }


    @ResponseBody
    @GetMapping(value = "/SearchClientInfo")
    public String SearchClientInfo(String id_search, String name_search, String phone_search){
//        String sql = "select * from client where id='" + id_search + "' or name='" + name_search + "' or phone='"+ phone_search + "'";
//        List<Map<String, Object>> data = testjdbc.queryForList(sql);
//        int total = testjdbc.queryForObject("select count(*) from client",int.class);
        QueryWrapper<Client> searchWrapper = new QueryWrapper();
        searchWrapper
                .eq("id", id_search)
                .or()
                .eq("name", name_search)
                .or()
                .eq("phone", phone_search);
        List<Client> data = clientService.list(searchWrapper);
        return getString(data);

    }

    private String getString(List<Client> data) {
        int total = clientService.count();
        List<Object> clients = new ArrayList<>();
        clients.add(total);
        clients.add(data);
        System.out.println(clients);
        return JSON.toJSONString(clients);
    }

    @GetMapping(value = {"/","login"})
    public String gotoIndex(){
        return "login";
    }

    @GetMapping(value = "/SignUp") // 管理员用来创建用户
    public String SignUp(String name, String password, String password_confirm) {
        if(password.equals(password_confirm)){
            User user = new User(name, password);
            userService.save(user);
        }
        return "redirect:/";

    }

    @ResponseBody
    @GetMapping(value = "/SignIn")
    public String SignIn(User user, HttpSession session) {
//        int num = testjdbc.queryForObject("select count(*) from user where name='" + user.getName() + "' and password='" + user.getPassword() + "'",int.class);
        QueryWrapper<User> countWrapper = new QueryWrapper();
        countWrapper
                .eq("name", user.getName())
                .eq("password", user.getPassword());
        int num = userService.count(countWrapper);
        String reply;
        if (num == 1){
            session.setAttribute("loginUser", user.getName());
            reply = "1";
        }
        else{
            reply = "0";
        }
        return reply;
    }

    @GetMapping(value = "/main")
    public String toMain(){
        return "main";
    }

    @GetMapping(value = "/client_table")
    public String toClientTable(){
        return "client_table";
    }





}
