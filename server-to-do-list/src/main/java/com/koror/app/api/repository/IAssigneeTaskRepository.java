package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.AssigneeTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IAssigneeTaskRepository {

    @Insert("insert into assigneetask (`ID`, `USERID`, `TASKID`) values(#{id}, #{userId}, #{taskId})")
    void add(AssigneeTask assigneeTask);

    @Delete("delete from assigneetask where `ID` = #{id}")
    void delete(String id);

    @Select("select * from assigneetask where `ID` = #{id}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "userId", column = "USERID"),
            @Result(property = "taskId", column = "TASKID"),
    })
    AssigneeTask getById(String id);

    @Select("select * from assigneetask")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "userId", column = "USERID"),
            @Result(property = "taskId", column = "TASKID"),
    })
    List<AssigneeTask> getList();

    @Update("update assigneetask set `USERID` = #{userId} 'TASKID' id = #{taskId} `ID` = #{id}")
    void update(AssigneeTask assigneeTask);

    @Delete("delete from assigneetask where `USERID` = #{userId} and `TASKID` = #{taskId}")
    void deleteAssigneeByParam(String userId, String taskId);

    @Select("select * from assigneetask where `USERID` = #{userId}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "userId", column = "USERID"),
            @Result(property = "taskId", column = "TASKID"),
    })
    AssigneeTask getAssigneeByUserId(String userId);

}
