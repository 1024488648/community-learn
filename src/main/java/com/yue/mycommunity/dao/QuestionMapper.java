package com.yue.mycommunity.dao;

import com.yue.mycommunity.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) value (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size")Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param("userId") Integer userId,@Param(value = "offset") Integer offset, @Param(value = "size")Integer size);


    @Select("select count(1) from question where  creator = #{userId}")
    Integer countByUserId(@Param("userId") Integer userId);
//    long countByExample(QuestionExample example);
//
//    int deleteByExample(QuestionExample example);
//
//    int deleteByPrimaryKey(Long id);
//
//    int insert(Question record);
//
//    int insertSelective(Question record);
//
//    List<Question> selectByExampleWithBLOBsWithRowbounds(QuestionExample example, RowBounds rowBounds);
//
//
//    List<Question> selectByExampleWithBLOBs(QuestionExample example);
//
//    List<Question> selectByExampleWithRowbounds(QuestionExample example, RowBounds rowBounds);
//
//    List<Question> selectByExample(QuestionExample example);
//
//    Question selectByPrimaryKey(Long id);
//
//    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);
//
//    int updateByExampleWithBLOBs(@Param("record") Question record, @Param("example") QuestionExample example);
//
//    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);
//
//    int updateByPrimaryKeySelective(Question record);
//
//    int updateByPrimaryKeyWithBLOBs(Question record);
//
//    int updateByPrimaryKey(Question record);
}