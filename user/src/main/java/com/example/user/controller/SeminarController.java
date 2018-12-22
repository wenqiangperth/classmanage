package com.example.user.controller;

import com.example.common.entity.Klass;
import com.example.common.entity.KlassSeminar;
import com.example.common.entity.Seminar;
import com.example.user.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName SeminarController
 * @Description TODO
 * @Date 2018/12/17 19:07
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/seminar")
public class SeminarController {
    @Autowired
    private SeminarService seminarService;

    /**
     * 创建讨论课
     * @param seminar
     * @return讨论课ID
     */
    @PostMapping(value = "")
    public long createSeminar(@RequestBody Seminar seminar){
        return seminarService.createSeminar(seminar);
    }

    /**
     * 查询：seminarID->klass
     * @param semianrId
     * @return
     */
    @GetMapping(value = "/{seminarId}/class")
    public ArrayList<Klass>getKlassBySeminarId(@PathVariable(name = "seminarId")Long semianrId){
        return seminarService.getKlassBySeminarId(semianrId);
    }

    /**
     * 查询:id->seminar
     * @param seminarId
     * @return
     */
    @GetMapping(value = "/{seminarId}")
    public Seminar getSeminarById(@PathVariable(name = "seminarId")Long seminarId){
        return seminarService.getSeminarById(seminarId);
    }

    /**
     * 更新：修改讨论课
     * @param seminarId
     * @param seminar
     * @return
     */
    @PutMapping(value = "/{seminarId}")
    public Long updateSeminarById(@PathVariable(name = "seminarId")Long seminarId,@RequestBody Seminar seminar){
        seminar.setId(seminarId);
        return seminarService.updateSeminar(seminar);
    }

    /**
     * 更新：修改班级讨论课
     * @param seminarId
     * @param klassId
     * @param klassSeminar
     * @return
     */
    @PutMapping(value = "{seminarId}/class/{classId}")
    public Long updateKlassSeminar(@PathVariable(name = "seminarId") Long seminarId, @PathVariable(name = "classId")Long klassId, @RequestBody KlassSeminar klassSeminar){
        klassSeminar.setKlassId(klassId);
        klassSeminar.setSeminarId(seminarId);
        return seminarService.updateKlassSeminar(klassSeminar);
    }

    /**
     * 删除：seminar
     * @param seminarId
     * @return
     */
    @DeleteMapping(value = "/{seminarId}")
    public Long deleteSeminarById(@PathVariable(name = "seminarId")Long seminarId){
        return seminarService.deleteSeminarById(seminarId);
    }

    /**
     * 查询：班级讨论课
     * @param klassId
     * @param seminarId
     * @return
     */
    @GetMapping(value = "/{seminarId}/class/{classId}")
    public KlassSeminar getKlassSeminarByKlassIdAndSeminarId(@PathVariable(name = "classId")Long klassId,@PathVariable(name = "seminarid")Long seminarId){
        return seminarService.getKlassSeminarByKlassIdAndSeminarId(klassId,seminarId);
    }

    /**
     * 更新：设置讨论课轮次
     * @param seminarId
     * @param roundId
     * @return
     */
    @PutMapping(value = "/{seminarId}/round")
    public Long updateSeminarRoundId(@PathVariable(name = "seminarId")Long seminarId,@RequestBody Long roundId){
        return seminarService.updateSeminarRoundId(roundId,seminarId);
    }

    /**
     * 更新：设置班级讨论课状态
     * @param seminarId
     * @param klassSeminar
     * @return
     */
    @PutMapping(value = "/{seminarId}/status")
    public Long updateSeminarStatus(@PathVariable(name = "seminarId")Long seminarId,@RequestBody KlassSeminar klassSeminar){
        klassSeminar.setSeminarId(seminarId);
        return seminarService.updateSeminarStatus(klassSeminar);
    }

}
