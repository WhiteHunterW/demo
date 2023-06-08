package com.example.demo.webserver.impl;

import com.example.demo.webserver.WebServer;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/5/16
 */
public class TomcatWebServer implements WebServer {

    @Override
    public void startWebServer() {
        System.out.println("启动tomcat");
    }
}
