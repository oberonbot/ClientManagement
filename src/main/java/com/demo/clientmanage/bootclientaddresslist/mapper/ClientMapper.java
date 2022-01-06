package com.demo.clientmanage.bootclientaddresslist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.clientmanage.bootclientaddresslist.bean.Client;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * 方法1：在mapper文件上加@Repository注解，这是从spring2.0新增的一个注解，用于简化 Spring 的开发，实现数据访问
 * 方法2：在mapper文件上加@Component注解，把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class="">
 */
@Repository
@Mapper
// 一定要记得标注Mapper注解,或者在BootClientaddresslistApplication中添加@MapperScan("com.atguigu.admin.mapper")
public interface ClientMapper extends BaseMapper<Client> {


    @Update("update client set id=#{id_after}, name=#{name_after}, phone=#{phone_after} where id=#{id_before} and name=#{name_before} and phone=#{phone_before}")
    Integer updateClient(String id_after, String name_after, String phone_after, String id_before, String name_before, String phone_before);
//
//    @Insert("insert into client values (#{id}, #{name}, #{phone})")
//    Client addClient(String id, String name, String phone);
//
//    @Delete("delete from client where id = #{id} and name = #{name} and phone = #{phone}")
//    Client deleteClient(String id, String name, String phone);
//
//    @Update("update client set id = #{id_after}, name = #{name_after}, phone = #{phone_after} " +
//            "where id = #{id_before} and name = #{name_before} and phone = #{phone_before}")
//    Client updateClient(String id_after, String name_after, String phone_after, String id_before, String name_before, String phone_before);
//
//    @Select("select * from client where id = #{id} or name = #{name} or phone = #{phone}")
//    Client searchClient(String id, String name, String phone);


}
