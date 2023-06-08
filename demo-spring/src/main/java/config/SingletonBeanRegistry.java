package config;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例bean
     * @param beanName
     * @return
     */
    Object getSingletonBean(String beanName);
}
