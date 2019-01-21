package com.koror.app.api.repository;

import com.koror.app.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserRepository{

    @Insert("insert into user (`ID`, `LOGIN`, `PASSWORD`, `NAME`, `EMAIL`, `ACCESS`) values(#{id},#{login},#{password},#{name},#{email},#{access})")
    void add(User user);

    @Delete("delete from user where `ID` = #{id}")
    void delete(String id);

    @Select("select * from user where `ID` = #{id}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "login", column = "LOGIN"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "access", column = "ACCESS")
    })
    User getById(String id);

    @Select("select * from user where `LOGIN` = #{login}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "login", column = "LOGIN"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "access", column = "ACCESS")
    })
    User getByLogin(String login);

    @Select("select * from user where `LOGIN` = #{login} and `PASSWORD` = #{password}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "login", column = "LOGIN"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "access", column = "ACCESS")
    })
    User login(@Param(value = "login") String login, @Param(value = "password")String password);

    @Select("select * from user")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "login", column = "LOGIN"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "access", column = "ACCESS")
    })
    List<User> getList();

    @Update("update user set `LOGIN` = #{login}, `PASSWORD` = #{password}, `NAME` = #{name}, `EMAIL` = #{email}, `ACCESS` = #{access} where `ID` = #{id}")
    void update(User entity);

}
