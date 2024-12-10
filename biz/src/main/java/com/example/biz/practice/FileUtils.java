package com.example.biz.practice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.biz.data.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/3/8
 */
@Slf4j
public class FileUtils {

    static {

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setName("xinghce");
        user.setCreated(new Date());
        user.setCount(2);
        user.setGender(1);
        //System.out.println(user);
        writeObject(user);
        readObject();

        switch ("fds"){
            case "2": break;
            case "ds": break;
        }

    }

    private static void writeObject(User user) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get("src/main/resources/user.json")));
        outputStream.writeObject(user);
        outputStream.close();
    }

    private static void readObject() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/user.json");
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        User user = (User) inputStream.readObject();
        System.out.println(user);
        inputStream.close();
    }

    /**
     * 读对象文件
     * @param fileName
     * @return
     * @param <T>
     * @throws IOException
     */
    public static <T> List<T> readObjList(String fileName, Class<T> tClass) {
        FileInputStream in = null;
        try {
             in = new FileInputStream(fileName);
             ObjectInputStream objIn = new ObjectInputStream(in);
             Object obj = objIn.readObject();
             return JSON.parseObject(JSON.toJSONString(obj), new TypeReference<List<T>>(tClass){});
        } catch (IOException | ClassNotFoundException e) {
            log.error("readObj -- 读取文件失败", e);
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("readObjList -- 关闭读文件流失败", e);
            }
        }
        return Collections.emptyList();
    }

    /**
     * 缓冲读对象
     * @param fileName
     * @param tClass
     * @return
     * @param <T>
     */
    public static <T> List<T> readBufferList(String fileName, Class<T> tClass) {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(Paths.get(fileName))));
            Object obj = objectInputStream.readObject();
            return JSON.parseObject(JSON.toJSONString(obj), new TypeReference<List<T>>(tClass){});
        } catch (Exception e) {
            log.error("readBufferList -- 读取文件失败", e);
        } finally {
            try {
                if(null != objectInputStream) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                log.error("readBufferList -- 关闭文件流失败", e);
            }
        }
        return Collections.emptyList();
    }

    /**
     * 缓冲写
     * @param fileName
     */
    public static void writeBufferObj(String fileName, Object obj) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(Paths.get(fileName))));
            outputStream.writeObject(obj);
        } catch (Exception e) {
            log.error("writeBufferObj -- 写对象失败");
        } finally {
            try {
                if(null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("writeBufferObj -- 关闭文件流失败");
            }
        }
    }


    /**
     * 写对象文件
     * @param fileName
     */
    public static void writeObjList(Object obj, String fileName) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            ObjectOutputStream outObj = new ObjectOutputStream(out);
            outObj.writeObject(obj);
        } catch (Exception e) {
            log.error("writeObjList -- 写文件失败", e);
        } finally {
            try {
                if(null != out) {
                    out.close();
                }
            } catch (IOException e) {
                log.error("writeObjList -- 关闭写文件流失败", e);
            }
        }
    }


    /**
     * 字符流写文件
     * @param obj
     * @param fileName
     */
    public static void writerBuffer(Object obj, String fileName) {
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter writer = null;
        try {
            outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(Paths.get(fileName)), StandardCharsets.UTF_8);
            writer = new BufferedWriter(outputStreamWriter);
            writer.write(JSON.toJSONString(obj));
        } catch (Exception e) {
            log.error("writerBuffer -- 字符流写文件失败", e);
        } finally {
            try {
                if(null != writer) {
                    writer.close();
                }
                if(null != outputStreamWriter) {
                    outputStreamWriter.close();
                }
            } catch (IOException e) {
                log.error("writerBuffer -- 关闭文件流失败", e);
            }
        }

    }

    /**
     * 字符流缓冲读
     * @param fileName
     * @param tClass
     * @return
     * @param <T>
     */
    public static <T> List<T> readerBuffer(String fileName, Class<T> tClass) {
        InputStreamReader reader;
        BufferedReader bufferedReader;
        StringJoiner joiner = new StringJoiner("");
        try {
            reader = new InputStreamReader(Files.newInputStream(Paths.get(fileName)), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(reader);
            /*char[] ch = new char[500];
            int flg;
            do {
                flg = bufferedReader.read(ch);
            } while (flg != -1);
            return JSON.parseObject(ch, ch.length, List.class);*/
            String str = bufferedReader.readLine();
            if(StringUtils.isNotEmpty(str)) {
                joiner.add(str);
            }
            return JSON.parseObject(joiner.toString(), new TypeReference<List<T>>(tClass){});
        } catch (Exception e) {
            log.error("readerBuffer -- 读取文件失败", e);
        }
        return Collections.emptyList();
    }


    public static void readFile() throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        int c = 0;
        try {
            inputStream = new FileInputStream("src/main/resources/Test.txt");
            byte[] bytes = new byte[500];
            while ((c = inputStream.read(bytes)) != -1){
                System.out.print(new String(bytes, 0, c));
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
            if(outputStream != null){
                outputStream.close();
            }
        }
    }

    public static void readFileBuffer() throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        int l;

        try {
            br = new BufferedReader(new FileReader("src/main/resources/test2.txt"));
            System.out.println(br.readLine());
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void fileRead() throws IOException {
        File file = new File("/Users/wenzeng/Desktop/wz/workspace/xhiteam/demo/src/main/resources");
        fileCur(file);
    }

    public static void fileCur(File file) {
        if(file == null){
            return;
        }
        System.out.println(file);
        File[] files = file.listFiles((f, name) -> f.isDirectory() || name.endsWith(".txt"));
        if(files == null){
            return;
        }
        for (File f :files) {
            if(f.isDirectory()){
                fileCur(f);
            } else if(f.isFile()){
                System.out.println(f);
            }
        }
    }
}
