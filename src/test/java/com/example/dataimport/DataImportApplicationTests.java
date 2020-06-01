package com.example.dataimport;

import com.example.dataimport.controller.MeetController;
import com.example.dataimport.service.ClusterService;
import com.example.dataimport.service.MeetService;
import com.example.dataimport.util.UploadUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class DataImportApplicationTests {

    @Autowired
    private ClusterService clusterService;
    @Autowired
    private MeetService meetService;

    @Autowired
    private MeetController meetController;
    @Test
    public void contextLoads() throws FileNotFoundException, URISyntaxException {
      /*  File originalFile = new File("C:\\Users\\Administrator\\Desktop\\583a8c66ef2d4c32d74afc3272b8736.jpg");
        try {
            InputStream inputStream = new FileInputStream(originalFile);
            //UploadUtil.uploadFile("120.76.152.182",22,"root","Cy123456666","/usr/tomcat/apache-tomcat-7.0.100/webapps/HappyHui/upload/xiaoe",inputStream,"test.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        URI imageUrl = new URI("http://www.xingfuh.com/HappyHui/upload/xiaoe/e16c9bd2-ee7e-4622-8b4b-bf29d9bc4101.jpg");
        FileOutputStream outputStream = new FileOutputStream(new File(imageUrl));
        //outputStream.write();

    }

}
