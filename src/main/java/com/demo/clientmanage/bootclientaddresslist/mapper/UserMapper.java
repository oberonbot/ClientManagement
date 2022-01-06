package com.demo.clientmanage.bootclientaddresslist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.clientmanage.bootclientaddresslist.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
