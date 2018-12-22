package com.example.common.entity;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

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
    private DateTimeLiteralExpression.DateTime reportDDL;
    private int status;
}
