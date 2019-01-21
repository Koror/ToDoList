package com.koror.app.api.repository;

import com.koror.app.entity.Group;
import org.apache.ibatis.annotations.*;

import java.io.IOException;
import java.util.List;

public interface IGroupRepository {

    @Insert("insert into `group` (`ID`, `NAME`, `CREATOR`) values(#{id}, #{name}, #{creator})")
    void add(Group group);

    @Delete("delete from `group` where `ID` = #{id}")
    void delete(String id);

    @Select("select * from `group` where `ID` = #{id}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "creator", column = "CREATOR")
    })
    Group getById(String id);

    @Select("select * from `group`")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "creator", column = "CREATOR")
    })
    List<Group> getList();

    @Update("update `group` set `NAME` = #{name}, `CREATOR` = #{creator} where `ID` = #{id}")
    void update(final Group group);

}
