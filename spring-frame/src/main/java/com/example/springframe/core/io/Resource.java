package com.example.springframe.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
public interface Resource {


    /**
     * 获取输入流
     * @throws IOException
     * @return
     */
    InputStream getInputStream() throws IOException;
}
