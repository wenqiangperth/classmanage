package com.example.common.entity;

import lombok.Data;

/**
 * @author perth
 * @ClassName TeamValidApplication
 * @Description TODO
 * @Date 2018/12/22 22:26
 * @Version 1.0
 **/
@Data
public class TeamValidApplication {
    private Long id;
    private Long teamId;
    private Long courseId;
    private Long teacherId;
    private String reason;
    private Long status;
}
