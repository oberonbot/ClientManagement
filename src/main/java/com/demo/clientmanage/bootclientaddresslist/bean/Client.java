package com.demo.clientmanage.bootclientaddresslist.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("client")
public class Client {
    public String id;
    public String name;
    public String phone;
}
