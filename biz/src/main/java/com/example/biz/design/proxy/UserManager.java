package com.example.biz.design.proxy;

import com.alibaba.fastjson.JSON;
import com.example.biz.practice.FileUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户查询功能入口
 * @author wenzeng
 * @date 2025/1/20
 */
public class UserManager {

    private final String SIMPLE_FILE = "/Users/wenzeng/Desktop/wz/模版/人员.txt";

    private final String DETAIL_FILE = "";

    /**
     * 获取部门下的人员
     * @param depId
     * @return
     */
    public Collection<UserModelApi> getUserByDepId(String depId) {
        /*
         * 1.数据库查询 select `name`,user_id from user where dep_id = #{depId} and dep_id like concat('%',#{depId},'%')
         * 为了能方便的根据上级部门查到所有的下级部门，部门ID设计的方式是：上级是01，下级按0101，0102，0103...继续编码
         * 因此部门匹配的时候用=和like
         * 还有一种方式是，冗余一个字段，parentID存下所有的上层ID
         *
         * 2. 没连数据库，读文件：读取固定文件？导入excel？
         */
        List<UserModel> users = FileUtils.readerBuffer(SIMPLE_FILE, UserModel.class);
        List<UserModelApi> proxyList = new ArrayList<>(users.size());
        users.forEach(userModel -> {
            UerProxy proxy = new UerProxy(userModel);
            // 下面两行可以不用设置吧？
            proxy.setUserId(userModel.getUserId());
            proxy.setName(userModel.getName());
            proxyList.add(proxy);
        });
        return proxyList;
    }

    public static void main(String[] args) {
        /*UserManager userManager = new UserManager();
        Collection<UserModelApi> userModelApis = userManager.getUserByDepId("10");
        System.out.println(JSON.toJSONString(userModelApis));*/

        DynamicProxy proxy = new DynamicProxy();
        Order order = new Order("test22",3, "测试账号1");
        OrderApi orderApi = proxy.getProxyInterface(order);
        // 使用被代理接口中的方法
        System.out.println(orderApi.getOrderUser());
        System.out.println(orderApi.getProductName());
        System.out.println(orderApi.getOrderNum());
        // 修改
        orderApi.setProductName("test44","测试账号2");
        orderApi.setOrderNum(4, "测试账号2");
        orderApi.setOrderUser("测试账号2", "测试账号2");

    }



}
