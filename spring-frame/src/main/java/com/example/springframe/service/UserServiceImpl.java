package com.example.springframe.service;

import com.example.springframe.beans.BeanClassLoaderAware;
import com.example.springframe.beans.BeanFactoryAware;
import com.example.springframe.beans.BeanNameAware;
import com.example.springframe.beans.factory.BeanFactory;
import com.example.springframe.context.ApplicationContext;
import com.example.springframe.context.ApplicationContextAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/16
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService, BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware {

    private String userName;

    private String userId;

    private String location;

    private String company;

    private UserManager userManager;

    private ApplicationContext applicationContextAware;

    private BeanFactory beanFactory;

    public UserServiceImpl() {

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public UserServiceImpl(String userName) {
        this.userName = userName;
    }

    public ApplicationContext getApplicationContextAware() {
        return applicationContextAware;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }


/*public UserServiceImpl(@Autowired RetryService retryService) {
        this.retryService = retryService;
    }*/

    @Override
    public void insertUser(User user) {
        String userName = userManager.getUserName("1");
        log.info("test rsult", userName);
        //userMapper.insert(user);
    }

    public String getUserInfo() {
        String userName = userManager.getUserName("2");
        StringBuilder builder = new StringBuilder("用户名: ");
        builder.append(userName)
                .append("用户ID: ")
                .append(userId)
                .append("地点: ")
                .append(location)
                .append("公司: ")
                .append(company);
        return builder.toString();
    }

   /* @Override
    public void destroy() {
        System.out.println("执行 UserServiceImpl#destroy");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("执行 UserServiceImpl#afterPropertiesSet");
    }*/

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader is "+classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("beanName is "+beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContextAware = applicationContext;
    }
}
