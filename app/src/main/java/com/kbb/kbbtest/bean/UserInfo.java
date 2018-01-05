package com.kbb.kbbtest.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by longlong on 2017/9/20.
 *
 * @ClassName: UserInfo
 * @Description:
 * @Date 2017/9/20
 */

public class UserInfo extends DataSupport {

    private String username;

    private String password;

    private String type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
