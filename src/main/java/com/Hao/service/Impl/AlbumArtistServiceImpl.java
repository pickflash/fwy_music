package com.Hao.service.Impl;

import com.Hao.entity.TbAlbumArtist;
import com.Hao.mapper.TbAlbumArtistMapper;
import com.Hao.service.AlbumArtistService;
import com.Hao.service.ArtistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AlbumArtistServiceImpl extends ServiceImpl<TbAlbumArtistMapper, TbAlbumArtist> implements AlbumArtistService {
}
