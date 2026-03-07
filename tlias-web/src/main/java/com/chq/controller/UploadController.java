package com.chq.controller;

import com.chq.pojo.Result;
import com.chq.utils.AliyunOSSOperator;
import com.chq.utils.UniqueFileName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ricardo
 */
@Slf4j
@RestController
public class UploadController {
    
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @Autowired
    private UniqueFileName uniqueFileName;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件名：{}", file.getOriginalFilename());
        if (!file.isEmpty()) {

            // 生成唯一文件名
            String uniquefileName= uniqueFileName.getUniqueFileName(file);

            // 上传文件
            String url = aliyunOSSOperator.upload(file.getBytes(), uniquefileName);
            log.info("上传url：{}", url);
            return Result.success(url);
        }
        return Result.error("上传失败");
    }

}