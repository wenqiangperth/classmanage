package com.example.common.dao;

import com.example.common.entity.Klass;
import com.example.common.mapper.KlassMapper;
import com.example.common.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private TeamMapper teamMapper;

    public Klass getClassByClassId(Long classId)
    {
        return klassMapper.getKlassByKlassId(classId);
    }

    /**
     * 删除：id->klass,以及关系
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long deleteKlassById(Long id){
        Long i=klassMapper.deleteKlassById(id);
        if(i>0){
            klassMapper.deleteClassRoundByClassId(id);
            klassMapper.deleteClassSeminarByClassId(id);
            klassMapper.deleteClassStudentByClassId(id);
            teamMapper.deleteTeamByKlassId(id);
        }
        return i;
    }
}
