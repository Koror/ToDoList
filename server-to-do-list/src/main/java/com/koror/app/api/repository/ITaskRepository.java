package com.koror.app.api.repository;

import com.koror.app.entity.Task;
import org.apache.ibatis.annotations.*;

import java.io.IOException;
import java.util.*;

public interface ITaskRepository {

    @Select("insert into task (`ID`, `NAME`, `PRIORITY`, `COMPLETE`, `CREATOR`, `GROUPID`) values(#{id}, #{name}, #{priority}, #{complete}, #{creator}, #{groupId})")
    void add(Task task);

    @Delete("delete from task where `ID` = #{id}")
    void delete(String id);

    @Select("select * from task where `ID`= #{id}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "priority", column = "PRIORITY"),
            @Result(property = "complete", column = "COMPLETE"),
            @Result(property = "creator", column = "CREATOR"),
            @Result(property = "groupId", column = "GROUPID")
    })
    Task getById(String id);

    @Select("select * from task")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "priority", column = "PRIORITY"),
            @Result(property = "complete", column = "COMPLETE"),
            @Result(property = "creator", column = "CREATOR"),
            @Result(property = "groupId", column = "GROUPID")
    })
    List<Task> getList();

    @Update("update task set `NAME` = #{name}, `PRIORITY` = #{priority}, `COMPLETE` = #{complete}, `CREATOR` = #{creator}, `GROUPID` = ? where ID = #{groupId}")
    void update(final Task task);

}
