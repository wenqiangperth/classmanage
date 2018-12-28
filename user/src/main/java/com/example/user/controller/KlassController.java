package com.example.user.controller;

import com.example.common.entity.Klass;
import com.example.user.service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName ClassController
 * @Description
 * @Author perth
 * @Date 2018/12/24 0024 下午 8:00
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/class")
public class KlassController {
    private final static String FILEPATH="/www/wwwroot/zhaotao/";
    @Autowired
    KlassService klassService;

    /**
     * 班级学生
     * @param classId
     * @param file
     * @return
     */
    @PutMapping(value="/{classId}")
    public Long updateClassStudentByExcel(@PathVariable(value="classId")Long classId, MultipartFile file)
    {
        return klassService.updateClassStudentByExcel(classId,file);
    }

    @GetMapping(value = "/{classId}")
    public Klass getKlassById(@PathVariable(name = "classId")Long klassId){
        return klassService.getKlassById(klassId);
    }

    /**
     * 删除班级
     * @param classId
     * @return
     */
    @DeleteMapping(value="/{classId}")
    public Long deleteClassByClassId(@PathVariable(value="classId")Long classId)
    {
        return klassService.deleteClassByClassId(classId);
    }

    @PostMapping(value = "")
    public Long addClass(@RequestBody Klass klass){
        return klassService.addKlass(klass);
    }
}
