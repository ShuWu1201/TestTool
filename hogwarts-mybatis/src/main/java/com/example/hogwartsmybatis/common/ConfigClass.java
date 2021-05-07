//package com.example.hogwartsmybatis.common;
//
//import com.example.hogwartsmybatis.entity.HogwartsTestUser;
//import com.example.hogwartsmybatis.service.HogwartsTestUserService;
//import com.example.hogwartsmybatis.service.Impl.HogwartsTestUserServiceImpl;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ConfigClass {
//
//    @Bean
//    public SqlSessionFactoryBuilder sqlSessionFactoryBuilder(){
//        return new SqlSessionFactoryBuilder();
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(){
//        return sqlSessionFactoryBuilder().build(ConfigClass.class.getClassLoader().getResourceAsStream("generator/config.xml"));
//    }
//
//    @Bean
//    public SqlSession sqlSession(){
//        return sqlSessionFactory().openSession();
//    }
//
////    @Bean
////    public HogwartsTestUserServiceImpl hogwartsTestUserServiceImpl(){
////        return new HogwartsTestUserServiceImpl();
////    }
//}
