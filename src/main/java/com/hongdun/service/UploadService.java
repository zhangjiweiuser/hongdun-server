package com.hongdun.service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.hongdun.entity.FileSource;
import com.hongdun.entity.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhang
 * @date 2019-03-07 下午 16:22
 */
@Service
public class UploadService {

    /**
     * 月份格式化，每个月的存放到一个文件夹下
     */
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 文件上传路径
     */
    @Value("${file.upload.path}")
    private String fileUploadPath;

    /**
     * 文件访问路径
     */
    @Value("${file.upload.url}")
    private String fileUploadUrl;

    public Response upload(MultipartFile file) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 重新生成文件名
        String nowStr = LocalDateTime.now().format(dateTimeFormatter);
        String destFileName = nowStr + UUID.randomUUID().toString().substring(0, 8).toUpperCase() + suffixName;
        String imageDir = LocalDate.now().format(dateFormatter);

        try {
            if (!new File(fileUploadPath + imageDir).exists()) {
                boolean createDir = new File(fileUploadPath + imageDir).mkdirs();
                if (!createDir) {
                    return Response.error(-1, "上传失败");
                }
            }
            file.transferTo(new File(fileUploadPath + imageDir + "/" + destFileName));
            return Response.success(0, new FileSource(fileName, fileUploadUrl + imageDir + "/" + destFileName));
        } catch (Exception e) {
            return Response.error(-1, "上传失败");
        }
    }
}
