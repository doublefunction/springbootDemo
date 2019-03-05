package com.springbootdemo.dao;

import com.springbootdemo.dataObject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDOMapper {
    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selecrByPrimaryKey(Integer id);
}