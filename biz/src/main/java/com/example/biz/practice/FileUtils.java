package com.example.biz.practice;

import com.example.biz.data.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/3/8
 */
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
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/user.json"));
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
        } finally {

        }

    }

    public static void fileRead() throws IOException {
        File file = new File("/Users/wenzeng/Desktop/wz/workspace/xhiteam/demo/src/main/resources");
        fileCur(file);
    }

    public static void fileCur(File file){
        if(file == null){
            return;
        }
        System.out.println(file);
        File[] files = file.listFiles((f, name) -> f.isDirectory() || name.endsWith(".txt"));
        if(files == null || files.length == 0){
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
