package com.yue.mycommunity.dao;

import com.yue.mycommunity.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper {
    @Insert("insert into gituser (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);

    @Select("select * from gituser where token = #{token}")
    User findByToken(@Param("token") String token);
}
