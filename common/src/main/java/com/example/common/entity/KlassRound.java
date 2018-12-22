package com.example.common.entity;

import lombok.Data;

/**
 * @author perth
 * @ClassName KlassRound
 * @Description TODO
 * @Date 2018/12/22 9:13
 * @Version 1.0
 **/
@Data
public class KlassRound {
    private Long klassId;
    private Long roundId;
    private int enrollNumber;
    private int klassSerial;
}
