package com.Hao.controller;


import com.Hao.entity.TbArtist;
import com.Hao.service.ArtistService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/artistList")
    public HashMap<String,Object> artistList(Integer limit){
        List<TbArtist> list=artistService.list();
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",list);
        return map;
    }

    @GetMapping("/queryByName")
    public HashMap<String,Object> queryByName(String aname){
        LambdaQueryWrapper<TbArtist> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(TbArtist::getAName,aname);
        List<TbArtist> list=artistService.list(queryWrapper);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",list);
        return map;
    }
}
