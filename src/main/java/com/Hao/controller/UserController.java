package com.Hao.controller;

import com.Hao.entity.TbUser;
import com.Hao.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("wx/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody TbUser tbUser, HttpSession session){
        LambdaQueryWrapper<TbUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(TbUser::getUserName,tbUser.getUserName());
        queryWrapper.eq(TbUser::getUserPass,tbUser.getUserPass());
        TbUser user = userService.getOne(queryWrapper);
        HashMap<String,Object> map=new HashMap<>();
        if(user!=null){
            session.setAttribute("user",user);
            map.put("msg","success");
        }else{
            map.put("state","用户名/密码错误");
        }
        return map;
    }

    @PostMapping("/register")
    public Object register(@RequestBody TbUser tbUser){
        LambdaQueryWrapper<TbUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(TbUser::getUserName,tbUser.getUserName());
        queryWrapper.eq(TbUser::getUserNickname,tbUser.getUserNickname());
        TbUser user = userService.getOne(queryWrapper);

        HashMap<String,Object> map=new HashMap<>();
        if(user!=null){
            map.put("msg","用户名/昵称已存在");
        }else{
            userService.save(tbUser);
            map.put("data","success");
        }
        return map;
    }

    @PostMapping("/showMsg")
    public Object showMessage(HttpSession session){
        TbUser user =(TbUser) session.getAttribute("user");
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",user);
        return map;
    }

    @PostMapping("/update")
    public Object update(@RequestBody TbUser tbUser,HttpSession session){
        HashMap<String,Object> map=new HashMap<>();
        TbUser user =(TbUser) session.getAttribute("user");
        Boolean Nameable=true;
        if(!user.getUserName().equals(tbUser.getUserName())){
            LambdaQueryWrapper<TbUser> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(TbUser::getUserName,tbUser.getUserName());
            TbUser byName=userService.getOne(queryWrapper);
            if(byName!=null){
                Nameable=false;
                map.put("data","用户用重复");
                return map;
            }
        }
        Boolean NickNameable=true;
        if(!user.getUserNickname().equals(tbUser.getUserNickname())){
            LambdaQueryWrapper<TbUser> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(TbUser::getUserNickname,tbUser.getUserNickname());
            TbUser byNickName=userService.getOne(queryWrapper);
            if(byNickName!=null){
                NickNameable=false;
                map.put("data","昵称重复");
                return map;
            }
        }
        if(Nameable && NickNameable){
            userService.updateById(tbUser);
            map.put("data","修改成功");
            return map;
        }
        session.removeAttribute("user");////////
        return null;
    }
}