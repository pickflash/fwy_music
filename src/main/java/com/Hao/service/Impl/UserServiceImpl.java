package com.Hao.service.Impl;

import com.Hao.entity.TbUser;
import com.Hao.mapper.TbUserMapper;
import com.Hao.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements UserService {
}
