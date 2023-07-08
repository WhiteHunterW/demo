package com.example.springframe.core.io;

import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
public class ClassPathResource implements Resource {

    private String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not null");
        this.path = path;
        this.classLoader = (null != classLoader) ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream is = classLoader.getResourceAsStream(path);
        if(is == null) {
            throw new FileNotFoundException(this.path + "cannot be opened because it does not exist");
        }
        return is;
    }
}
