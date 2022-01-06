package com.demo.clientmanage.bootclientaddresslist.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.clientmanage.bootclientaddresslist.bean.User;
import com.demo.clientmanage.bootclientaddresslist.mapper.ClientMapper;
import com.demo.clientmanage.bootclientaddresslist.mapper.UserMapper;
import com.demo.clientmanage.bootclientaddresslist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    ClientMapper clientMapper;
}
