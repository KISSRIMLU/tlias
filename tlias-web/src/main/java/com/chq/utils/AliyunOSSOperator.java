package com.chq.utils;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;

import com.aliyun.sdk.service.oss2.models.*;
import com.aliyun.sdk.service.oss2.transport.BinaryData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * @author Ricardo
 */
@Component
public class AliyunOSSOperator {
    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    public String upload(byte[] bytes, String fileName) {
        String endpoint = aliyunOSSProperties.getEndpoint();
        String region = aliyunOSSProperties.getRegion();
        String bucket = aliyunOSSProperties.getBucket();

        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();

        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(region);

        try (OSSClient client = clientBuilder.build()) {
            PutObjectResult result = client.putObject(PutObjectRequest.newBuilder()
                    .bucket(bucket)
                    .key(dir + "/" + fileName)
                    .body(BinaryData.fromBytes(bytes))
                    .build());

//            System.out.printf("status code:%d, request id:%s, eTag:%s\n",
//                    result.statusCode(), result.requestId(), result.eTag());

        } catch (Exception e) {
//            If the exception is caused by ServiceException, detailed information can be obtained in this way.
//             ServiceException se = ServiceException.asCause(e);
//             if (se != null) {
//                System.out.printf("ServiceException: requestId:%s, errorCode:%s\n", se.requestId(), se.errorCode());
//            }
//            System.out.printf("error:\n%s", e);
        }

        return endpoint+"/"+dir+"/"+fileName;
    }
}

