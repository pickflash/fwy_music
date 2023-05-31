package com.Hao.service.Impl;

import com.Hao.entity.TbPlaylist;
import com.Hao.mapper.TbPlaylistMapper;
import com.Hao.service.PlaylistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class PlaylistServiceImpl extends ServiceImpl<TbPlaylistMapper, TbPlaylist> implements PlaylistService {
}
