package com.Hao.controller;

import com.Hao.entity.TbAlbum;
import com.Hao.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/list")
    public HashMap<String,Object> list(){
        List<TbAlbum> albumList = albumService.list();
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",albumList);
        return map;
    }
}
