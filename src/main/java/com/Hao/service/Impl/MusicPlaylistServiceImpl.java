package com.Hao.service.Impl;

import com.Hao.entity.TbMusicPlaylist;
import com.Hao.mapper.TbMusicPlaylistMapper;
import com.Hao.service.MusicPlaylistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MusicPlaylistServiceImpl extends ServiceImpl<TbMusicPlaylistMapper, TbMusicPlaylist> implements MusicPlaylistService {
}
