package com.springbootdemo.service.impl;

import com.springbootdemo.dao.UserDOMapper;
import com.springbootdemo.dao.UserPasswordDOMapper;
import com.springbootdemo.dataObject.UserDO;
import com.springbootdemo.dataObject.UserPasswordDO;
import com.springbootdemo.model.UserModel;
import com.springbootdemo.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;
    @Override
    public UserModel getUser(Integer id) {
        UserDO userDO =userDOMapper.selecrByPrimaryKey(id);
        if(userDO == null)
        {
            return null;
        }
        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());
        return converFromDataObject(userDO,userPasswordDO);
    }
    private UserModel converFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if(userDO ==null)
        {
            return null;
        }
        UserModel userModel =new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if(userPasswordDO!=null)
        {
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }
}
