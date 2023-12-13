package com.example.biz.design;


import com.example.biz.data.User;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/7
 */
public class UserBuilder {

    private String username;
    private Integer gender;


    public static UserBuilder newUserBuilder(){
        return new UserBuilder();
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder gender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public User build(){
        return new User(this.username, this.gender);
    }
}
