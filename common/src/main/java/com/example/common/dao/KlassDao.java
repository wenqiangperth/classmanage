package com.example.common.dao;

import com.example.common.entity.Klass;
import com.example.common.mapper.KlassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @ClassName KlassDao
 * @Description
 * @Author perth
 * @Date 2018/12/22 0022 下午 2:48
 * @Version 1.0
 **/
@Repository
public class KlassDao {

    @Autowired
    private KlassMapper klassMapper;

    public Klass getClassByClassId(Long classId)
    {
        return klassMapper.getKlassByKlassId(classId);
    }
}
