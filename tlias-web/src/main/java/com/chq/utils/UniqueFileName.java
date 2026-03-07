package com.chq.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author Ricardo
 * @since 2026/3/7 12:33
 */
@Component
public class UniqueFileName {
    public String getUniqueFileName(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
        return uniqueFileName;
    }
}
