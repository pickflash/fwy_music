package com.Hao.service.Impl;

import com.Hao.entity.TbAlbum;
import com.Hao.mapper.TbAlbumMapper;
import com.Hao.service.AlbumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl extends ServiceImpl<TbAlbumMapper, TbAlbum>implements AlbumService {
}
