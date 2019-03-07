package com.hongdun.controller;

import com.hongdun.entity.Response;
import com.hongdun.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhang
 * @date 2019-03-01 下午 17:27
 */
@RestController
@Validated
public class FileUploadController {

    @Autowired
    private UploadService uploadService;



    @PostMapping("/upload")
    public Response upload(MultipartFile file) {
        return uploadService.upload(file);
    }
}
