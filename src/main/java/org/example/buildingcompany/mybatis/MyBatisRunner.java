package org.example.buildingcompany.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.City;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisRunner {
    private final static Logger logger = LogManager.getLogger(MyBatisRunner.class);
    public static void main(String[] args) throws IOException {

        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)) {

            City city = session.selectOne("org.example.buildingcompany.mybatis.mappers.CityMapper.getById", 1);

            logger.info(city);

        }
    }
}
