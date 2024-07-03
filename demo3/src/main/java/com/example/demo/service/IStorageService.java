package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
     Boolean save (MultipartFile file,String fileName,String filePath);
}
