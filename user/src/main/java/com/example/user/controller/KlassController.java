package com.example.user.controller;

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

    @Autowired
    KlassService klassService;

    @PutMapping(value="/{classId}")
    public Long updateClassStudentByExcel(@PathVariable(value="classId")Long classId, MultipartFile file)
    {
        return klassService.updateClassStudentByExcel(classId,file);
    }

    @DeleteMapping(value="/classId")
    public Long deleteClassByClassId(@PathVariable(value="classId")Long classId)
    {
        return klassService.deleteClassByClassId(classId);
    }


}
