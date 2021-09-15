package com.yue.mycommunity.dao;

import com.yue.mycommunity.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into gituser (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);
}
