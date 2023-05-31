package com.Hao.entity;

import lombok.Data;

import java.util.Date;
@Data
public class TbPlaylist {
    private Integer plId;
    private String plName;
    private String plDesc;
    private Date plCreatetime;
    private Date plUpdatetime;
    private Integer userId;
    private String plPath;
}