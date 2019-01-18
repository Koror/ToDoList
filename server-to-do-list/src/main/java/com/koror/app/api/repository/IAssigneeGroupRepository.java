package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.AssigneeTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IAssigneeGroupRepository {

    @Insert("insert into assigneegroup (`ID`, `USERID`, `GROUPID`) values(#{id}, #{userId}, #{groupId})")
    void add(AssigneeGroup assigneeGroup);

    @Delete("delete from assigneegroup where `ID` = #{id}")
    void delete(String id);

    @Select("select * from assigneegroup where `ID`=#{id}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "userId", column = "USERID"),
            @Result(property = "groupId", column = "GROUPID"),
    })
    AssigneeGroup getById(String id);

    @Select("select * from assigneegroup")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "userId", column = "USERID"),
            @Result(property = "groupId", column = "GROUPID"),
    })
    List<AssigneeGroup> getList();

    @Update("update assigneegroup set `USERID` = #{userId} 'GROUPID' id = #{groupId} `ID` = #{id}")
    void update(AssigneeGroup assigneeGroup);

    @Delete("delete from assigneegroup where `USERID` = #{userId} and `GROUPID` = #{groupId}")
    void deleteAssigneeByParam(String userId, String groupId);

    @Select("select * from assigneegroup where `USERID`=#{userId}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "userId", column = "USERID"),
            @Result(property = "groupId", column = "GROUPID"),
    })
    AssigneeGroup getAssigneeByUserId(String userId);

}
