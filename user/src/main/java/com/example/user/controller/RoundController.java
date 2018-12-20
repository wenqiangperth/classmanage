package com.example.user.controller;

import com.example.common.entity.Seminar;
import com.example.user.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author perth
 * @ClassName RoundController
 * @Description TODO
 * @Date 2018/12/17 19:06
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/round")
public class RoundController {
    @Autowired
    private RoundService roundService;

    @GetMapping(value = "/{roundId}/seminar")
    public ArrayList<Seminar>getAllSeminarByRoundId(@PathVariable("roundId")Long roundId){
        return roundService.getAllSeminarsByRoundId(roundId);
    }

}
