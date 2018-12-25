package com.example.common.entity;

import lombok.Data;

/**
 * @author perth
 * @ClassName MemberLimitStrategy
 * @Description TODO
 * @Date 2018/12/19 20:47
 * @Version 1.0
 **/
@Data
public class MemberLimitStrategy  {
    private Long id;
    private int minMember;
    private int maxMember;
    private Long courseId;


}
