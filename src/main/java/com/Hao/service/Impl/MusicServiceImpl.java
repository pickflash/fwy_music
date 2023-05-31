package com.Hao.service.Impl;

import com.Hao.entity.TbMusic;
import com.Hao.mapper.TbMusicMapper;
import com.Hao.service.MusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MusicServiceImpl extends ServiceImpl<TbMusicMapper, TbMusic> implements MusicService {
}
