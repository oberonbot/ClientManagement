package com.demo.clientmanage.bootclientaddresslist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.clientmanage.bootclientaddresslist.bean.Client;

public interface ClientService extends IService<Client> {
    Integer updateClient(String id_after, String name_after, String phone_after, String id_before, String name_before, String phone_before);
}
