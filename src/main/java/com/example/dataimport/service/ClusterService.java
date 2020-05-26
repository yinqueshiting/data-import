package com.example.dataimport.service;

import java.util.Map;

public interface ClusterService {
    Object saveUserListFromExcel(Map<String,String> paramMap);
    void clusterUpdateUserInfo();
}
