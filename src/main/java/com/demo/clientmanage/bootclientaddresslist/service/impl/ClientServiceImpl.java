package com.demo.clientmanage.bootclientaddresslist.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.clientmanage.bootclientaddresslist.bean.Client;
import com.demo.clientmanage.bootclientaddresslist.mapper.ClientMapper;
import com.demo.clientmanage.bootclientaddresslist.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

    @Autowired
    ClientMapper clientMapper;

    public Integer updateClient(String id_after, String name_after, String phone_after, String id_before, String name_before, String phone_before){
        return clientMapper.updateClient(id_after,name_after,phone_after,id_before,name_before,phone_before);
    }
}
