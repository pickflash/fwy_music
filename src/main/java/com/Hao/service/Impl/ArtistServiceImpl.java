package com.Hao.service.Impl;

import com.Hao.entity.TbArtist;
import com.Hao.mapper.TbArtistMapper;
import com.Hao.service.ArtistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl extends ServiceImpl<TbArtistMapper, TbArtist> implements ArtistService {
}
