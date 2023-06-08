package config;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
public interface BeanFactory {

    /**
     * 获取bean实例对象
     * @param beanName bean名称
     * @return
     */
    Object getBean(String beanName);
}
