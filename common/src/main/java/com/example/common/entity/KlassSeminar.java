package com.example.common.entity;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Date;

/**
 * @author perth
 * @ClassName KlassSeminar
 * @Description TODO
 * @Date 2018/12/22 14:58
 * @Version 1.0
 **/
@Data
public class KlassSeminar {
    private Long id;
    private Long klassId;
    private Long seminarId;
    private Date reportDDL;
    private int status;
}
