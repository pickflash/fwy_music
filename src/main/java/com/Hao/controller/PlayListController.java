package com.Hao.controller;


import com.Hao.entity.TbMusicPlaylist;
import com.Hao.entity.TbPlaylist;
import com.Hao.entity.TbUser;
import com.Hao.service.MusicPlaylistService;
import com.Hao.service.PlaylistService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/playlist")
public class PlayListController {
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private MusicPlaylistService musicPlaylistService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public Object upload(MultipartFile file, HttpServletRequest req) {
        //1.1 获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        System.out.println("原始名称：" + originalFilename);
        //1.2 获取文件后缀
        String ext = FilenameUtils.getExtension(originalFilename);
        //1.3 获取上传文件夹的名称
        String realPath = "\\img\\";
        //1.4 获取时间戳，设置文件唯一名称
        String filename = System.currentTimeMillis() + "." + ext;
        System.out.println("唯一名称：" + filename);
        //1.5 创建文件
        File newFile = new File(realPath, filename);
        //1.7 创建HashMap对象
        HashMap<String, Object> map = new HashMap<>();
        //1.6 文件上传
        try {
            file.transferTo(newFile);
            map.put("data", "upload/img/" + filename);
        } catch (IOException e) {
            System.out.println("上传失败的原因：" + e.getMessage());
            map.put("state", "上传失败");
        }
        return map;
    }

    @GetMapping("/list")
    public HashMap<String,Object> list(HttpSession session){
        TbUser tbUser=(TbUser) session.getAttribute("user");
        LambdaQueryWrapper<TbPlaylist> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(TbPlaylist::getUserId,tbUser.getId());
        List<TbPlaylist> playlists=playlistService.list(queryWrapper);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",playlists);
        return map;
    }

    @PostMapping("/fav")
    public Object favorite(@RequestBody TbMusicPlaylist tbMusicPlaylist){
        musicPlaylistService.save(tbMusicPlaylist);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data","收藏成功");
        return map;
    }

    @PostMapping("/add")
    public Object add(@RequestBody TbPlaylist tbPlaylist,HttpSession session) throws ParseException {
        TbUser user=(TbUser)session.getAttribute("user");
        TbPlaylist playlist=tbPlaylist;
        // 设置日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取当前时间
        Date now = new Date();
        // 格式化当前时间
        String strNow =sdf.format(now);
        now=sdf.parse(strNow);
        playlist.setPlCreatetime(now);
        playlist.setPlUpdatetime(now);
        playlist.setUserId(user.getId());
        playlistService.save(playlist);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data","success");
        return map;
    }
}
