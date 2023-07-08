package com.example.springframe.context;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/7
 */
public abstract class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
    }

    public String[] getConfigLocations() {
        return configLocations;
    }
}
