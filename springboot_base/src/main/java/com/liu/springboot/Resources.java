package com.liu.springboot;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author bliu
 * @date 2021-03-29 10:52
 */
public class Resources {
    public static void main(String[] args) throws IOException {
        Resource fileResource = new UrlResource("file:G:\\clone project\\springboot-learn\\springboot_base\\src\\main\\java\\com\\liu\\springboot\\Resources.java");
        System.out.println(fileResource.getDescription());
        System.out.println(fileResource.getFilename());

        Resource urlResource = new UrlResource("http:www.baidu.com");
        System.out.println(urlResource.getDescription());
        System.out.println(urlResource.getFilename());

        ClassPathResource classPathResource = new ClassPathResource("classpath:Resource.java");
        System.out.println(classPathResource.getFilename());


        ByteArrayResource byteArrayResource = new ByteArrayResource("str".getBytes());

    }
}
