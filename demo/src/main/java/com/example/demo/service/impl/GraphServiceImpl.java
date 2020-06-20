package com.example.demo.service.impl;

import com.example.demo.service.GraphService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Service
public class GraphServiceImpl implements GraphService {

    public String executeUpload(String filename, String uploadDir, MultipartFile file) {

        String fileName = filename;
        //服务器端保存的文件对象
        String path = uploadDir + "/" + fileName;
        File fatherPath = new File(uploadDir);
        File serverFile = new File(path);
        //将上传的文件写入到服务器端文件内
        try {
            if (!Arrays.asList(fatherPath.list()).contains(fileName)) {
                file.transferTo(serverFile);
                return "success";
            } else
                return "existed,rename";

        } catch (IOException e) {
            e.printStackTrace();

        }
        return "fail";
    }

    public String executeDelete(String filename, String uploadDir) {

            return "no such file";


    }
}
