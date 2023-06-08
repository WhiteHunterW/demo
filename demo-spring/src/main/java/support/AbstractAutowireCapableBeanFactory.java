package support;

import bean.BeanDefinition;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = beanDefinition.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {

        }
        // 放入单例容器？
        addSingletonBean(beanName, bean);
        return bean;
    }
}
