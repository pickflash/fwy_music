package com.Hao.controller;

import com.Hao.bean.MusicBean;
import com.Hao.entity.TbAlbum;
import com.Hao.entity.TbAlbumArtist;
import com.Hao.entity.TbArtist;
import com.Hao.entity.TbMusic;
import com.Hao.service.AlbumArtistService;
import com.Hao.service.AlbumService;
import com.Hao.service.ArtistService;
import com.Hao.service.MusicService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("wx/music")
public class MusicController {
    @Autowired
    private MusicService musicService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private AlbumArtistService albumArtistService;

    @GetMapping("/list")
    public HashMap<String, Object> getPage(Integer page, Integer limit){
        HashMap<String,Object> map=new HashMap<>();
        Page<TbMusic> pageInfo=new Page(page,limit);
        Page<MusicBean> beanPage=new Page<>(page,limit);
        musicService.page(pageInfo);

        BeanUtils.copyProperties(page,beanPage,"records");
        List<TbMusic> records=pageInfo.getRecords();
        List<MusicBean> list=records.stream().map((item)->{
            MusicBean musicBean=new MusicBean();
            BeanUtils.copyProperties(item,musicBean);
            //获取专辑
            LambdaQueryWrapper<TbAlbum> AlbumQueryWrapper=new LambdaQueryWrapper<>();
            AlbumQueryWrapper.eq(TbAlbum::getAlId,item.getAlId());
            TbAlbum tbAlbum=albumService.getOne(AlbumQueryWrapper);
            //获取专辑-歌手
            LambdaQueryWrapper<TbAlbumArtist> AlbumArtistQueryWrapper=new LambdaQueryWrapper<>();
            AlbumArtistQueryWrapper.eq(TbAlbumArtist::getAlId,item.getAlId());
            TbAlbumArtist tbAlbumArtist=albumArtistService.getOne(AlbumArtistQueryWrapper);
            //获取歌手
            LambdaQueryWrapper<TbArtist> ArtistQueryWrapper=new LambdaQueryWrapper<>();
            ArtistQueryWrapper.eq(TbArtist::getAId,tbAlbumArtist.getAId());
            List<TbArtist> artistList=artistService.list(ArtistQueryWrapper);

            musicBean.setTbAlbum(tbAlbum);
            musicBean.setArtistList(artistList);
            return musicBean;
        }).collect(Collectors.toList());
        beanPage.setRecords(list);
        map.put("data",beanPage.getRecords());
        return map;
    }

    @GetMapping("/detail")
    public HashMap<String,Object> getMusic(Integer id){
        HashMap<String,Object> map=new HashMap<>();
        LambdaQueryWrapper<TbMusic> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(TbMusic::getMId,id);
        TbMusic tbMusic=musicService.getOne(queryWrapper);
        map.put("data",tbMusic);
        return map;
    }
    @GetMapping("/selectByAlId")
    public HashMap<String,Object> selectByAlId(Integer page,Integer limit,Integer id){
        Page<TbMusic> pageInfo=new Page(page,limit);
        Page<MusicBean> beanPage=new Page<>(page,limit);
        LambdaQueryWrapper<TbMusic> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.eq(TbMusic::getAlId,id);
        musicService.page(pageInfo,queryWrapper);
        BeanUtils.copyProperties(page,beanPage,"records");

        List<TbMusic> records=pageInfo.getRecords();

        List<MusicBean> list=records.stream().map((item)->{
            MusicBean musicBean=new MusicBean();
            BeanUtils.copyProperties(item,musicBean);
            //获取专辑
            LambdaQueryWrapper<TbAlbum> AlbumQueryWrapper=new LambdaQueryWrapper<>();
            AlbumQueryWrapper.eq(TbAlbum::getAlId,item.getAlId());
            TbAlbum tbAlbum=albumService.getOne(AlbumQueryWrapper);
            //获取专辑-歌手
            LambdaQueryWrapper<TbAlbumArtist> AlbumArtistQueryWrapper=new LambdaQueryWrapper<>();
            AlbumArtistQueryWrapper.eq(TbAlbumArtist::getAlId,item.getAlId());
            TbAlbumArtist tbAlbumArtist=albumArtistService.getOne(AlbumArtistQueryWrapper);
            //获取歌手
            LambdaQueryWrapper<TbArtist> ArtistQueryWrapper=new LambdaQueryWrapper<>();
            ArtistQueryWrapper.eq(TbArtist::getAId,tbAlbumArtist.getAId());
            List<TbArtist> artistList=artistService.list(ArtistQueryWrapper);

            musicBean.setTbAlbum(tbAlbum);
            musicBean.setArtistList(artistList);
            return musicBean;
        }).collect(Collectors.toList());
        HashMap<String,Object> map=new HashMap<>();
        beanPage.setRecords(list);
        map.put("data",beanPage.getRecords());
        return map;
    }
}
