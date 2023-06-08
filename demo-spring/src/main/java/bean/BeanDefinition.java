package bean;

/**
 * Function:
 * bean对象元数据
 * @author wenzeng
 * @date 2023/5/27
 */
public class BeanDefinition {

    /**
     * 对象
     */
    private Class<?> bean;

    public BeanDefinition(Class bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
