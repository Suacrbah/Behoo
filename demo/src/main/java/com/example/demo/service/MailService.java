package com.example.demo.service;



import java.util.List;


public interface MailService {

    void sendMail(String subject, String body, String from, String[] to) throws Exception;

    void sendMail(String subject, String body, String from, String[] cc, String[] to) throws Exception;

    void sendMail(String subject, String body, boolean html, String from, String[] cc, String[] to) throws Exception;

    void sendMail(String subject, String body, boolean html, String from, String[] cc, String[] to, List<String> attachPathList) throws Exception;
}


