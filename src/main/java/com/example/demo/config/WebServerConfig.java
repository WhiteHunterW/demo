package com.example.demo.config;

import com.example.demo.webserver.impl.JettyWebServer;
import com.example.demo.webserver.impl.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/5/16
 */
@Configuration
public class WebServerConfig {

    @Bean
    public TomcatWebServer tomcatWebServer(){
        return new TomcatWebServer();
    }

    @Bean
    public JettyWebServer jettyWebServer() {
        return new JettyWebServer();
    }

}
