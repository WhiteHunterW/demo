package com.example.biz.webserver.impl;

import com.example.biz.webserver.WebServer;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/5/16
 */
public class JettyWebServer implements WebServer {
    @Override
    public void startWebServer() {
        System.out.println("启动jetty");
    }
}
