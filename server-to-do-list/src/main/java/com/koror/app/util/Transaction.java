package com.koror.app.util;

import com.koror.app.api.repository.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Transaction {

    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSession sqlSession;

    public static void openSqlSession() throws IOException {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    public static SqlSession getSqlSession() {
        return sqlSession;
    }

    public static void commit(){
        sqlSession.commit();
    }
}
