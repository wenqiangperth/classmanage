package com.example.common.entity;

import lombok.Data;

/**
 * @ClassName RequestInfo
 * @Description
 * @Author perth
 * @Date 2018/12/21 0021 上午 8:51
 * @Version 1.0
 **/
@Data
public class RequestInfo {
    private long leaderId;
    private String leaderName;
    private long courseId;
    private String reason;
}
