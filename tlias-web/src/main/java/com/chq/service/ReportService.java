package com.chq.service;

import com.chq.pojo.JobOption;

import java.util.List;
import java.util.Map;


/**
 * @author Ricardo
 * @since 2026/3/10 10:02
 */

public interface ReportService {
    JobOption getEmpJobData();

    List<Map> getEmpGenderData();
}
