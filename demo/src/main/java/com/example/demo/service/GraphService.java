package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface GraphService {

    public String executeUpload(String filename, String uploadDir, MultipartFile file);

    public String executeDelete(String filename, String uploadDir);

}
