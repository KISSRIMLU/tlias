package com.chq.service.impl;

import com.chq.mapper.EmpMapper;
import com.chq.pojo.JobOption;
import com.chq.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Ricardo
 * @since 2026/3/10 10:03
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list= empMapper.getEmpJobData();
        List<Object> jobList = list.stream().map(map -> map.get("job")).toList();
        List<Object> dataList = list.stream().map(map -> map.get("count")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return empMapper.countEmpGenderData();
    }
}
