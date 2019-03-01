package com.hongdun.controller;

import java.io.File;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.hongdun.entity.FeedOrigin;
import com.hongdun.entity.Response;
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
public class PicUploadController {

    @PostMapping("/upload")
    public Response upload( FeedOrigin feed) {
        String name = feed.getName();
        System.out.println(name);
        // 获取文件名
        String fileName = feed.getFile().getOriginalFilename();
        // 获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 重新生成文件名
        fileName = UUID.randomUUID() + suffixName;
        // 执行上传的文件夹路径
        String filePath = "E:/2019/";
        try {
            feed.getFile().transferTo(new File(filePath + fileName));
            return Response.success(0, "上传成功");
        } catch (Exception e) {
            return Response.error(-1, "上传失败");
        }
    }
}
