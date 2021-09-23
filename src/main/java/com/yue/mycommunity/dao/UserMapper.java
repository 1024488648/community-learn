package com.yue.mycommunity.dao;

import com.yue.mycommunity.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("insert into gituser (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from gituser where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from gituser where id = #{id}")
    User findById(@Param("id")long id);
}
