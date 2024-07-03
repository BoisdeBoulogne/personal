package com.example.demo.controllers;

import com.example.demo.service.IStorageService;
import com.example.demo.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/")
public class CommonController {
    @Autowired
    IStorageService storageService;
    @PostMapping(value = "/upload")
    public Result upload(HttpServletRequest request, @RequestParam("file") MultipartFile response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        String fileName = file.getOriginalFilename();
        storageService.save(file, fileName, "C:\\Users\\Administrator\\Desktop\\pics\\");
        return Result.success(fileName);

    }
}
