package com.demo.clientmanage.bootclientaddresslist.admin;

import com.demo.clientmanage.bootclientaddresslist.bean.Client;
import com.demo.clientmanage.bootclientaddresslist.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class BootClientaddresslistApplicationTests {

    @Autowired
    ClientMapper clientMapper;



    @Test
    void testClientMapper(){
        Client selected = clientMapper.selectById(1L);
//        Client selected1 = clientMapper.selectById();
        System.out.println(selected);
    }



}
