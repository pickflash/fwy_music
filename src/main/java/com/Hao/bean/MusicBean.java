package com.Hao.bean;

import com.Hao.entity.TbAlbum;
import com.Hao.entity.TbArtist;
import com.Hao.entity.TbMusic;
import lombok.Data;

import java.util.List;

@Data
public class MusicBean extends TbMusic {

    private TbAlbum tbAlbum;

    private List<TbArtist> artistList;
}
