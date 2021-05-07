//package com.example.hogwartsmybatis.test;
//
//import com.alibaba.fastjson.JSONObject;
//import com.example.hogwartsmybatis.dao.HogwartsTestUserMapper;
//import com.example.hogwartsmybatis.entity.HogwartsTestUser;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.InputStream;
//
//public class TestDemo {
//
//    public static void main(String[] args) {
//        InputStream inputStream = TestDemo.class.getClassLoader().getResourceAsStream("generator/config.xml");
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        /**
//         * 获取实现接口的代理对象
//         */
//        HogwartsTestUserMapper hogwartsTestUserMapper = sqlSession.getMapper(HogwartsTestUserMapper.class);
//
//        /**
//         * 增、删、改都需要提交事务，执行完成后需要关闭资源防止资源浪费
//         */
////        Account account = new Account(3L, "王武", "678908", 34);
////        accountRepository.save(account);
////        sqlSession.commit();
//
//        HogwartsTestUser hogwartsTestUser = hogwartsTestUserMapper.selectByPrimaryKey(1L);
//        System.out.println(JSONObject.toJSONString(hogwartsTestUser));
//
//
//
//
//    }
//
//
//}
