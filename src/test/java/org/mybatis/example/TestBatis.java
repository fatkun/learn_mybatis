package org.mybatis.example;

import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.mybatis.example.bean.Blog;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.mybatis.example.bean.BlogStatusType;
import org.mybatis.example.mapper.BlogMapper;

/**
 * Created by fatkun on 2017/8/13.
 */
public class TestBatis extends TestCase {
    SqlSessionFactory sqlSessionFactory;
    Logger logger = Logger.getLogger(TestBatis.class);

    @Override
    public void setUp() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testEnum() {
        System.out.println(Enum.valueOf(BlogStatusType.class, "NORMAL"));
    }

    @Test
    public void testSelect() {
        SqlSession session = sqlSessionFactory.openSession();
        try {

            Blog blog = (Blog) session.selectOne("org.mybatis.example.mapper.BlogMapper.selectBlog", 1);
            System.out.println(blog);

//            HashMap resultMap = (HashMap) session.selectOne("org.mybatis.example.mapper.BlogMapper.selectBlog2", 1);
//            System.out.println(resultMap);
        } finally {
            session.close();
        }
    }

    @Test
    public void testSelect2() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            Blog blog = blogMapper.selectBlog3(1);
            System.out.println(blog);

        } finally {
            session.close();
        }
    }

    public void testSearch() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("title", "a");
            params.put("start", 0);
            params.put("limit", 2);
            params.put("content", "");
//            params.put("status", BlogStatusType.CANCEL);
            //params.put("mstatus", Arrays.asList(BlogStatusType.CANCEL, BlogStatusType.NORMAL));
            params.put("mstatus", new ArrayList<String>());

            List<Blog> blogs = session.selectList("org.mybatis.example.mapper.BlogMapper.search", params);
            System.out.println(blogs);
        } finally {
            session.close();
        }
    }

    @Test
    public void testInsert() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Blog blog = new Blog();
            blog.setTitle("ccc");
            blog.setContent("ccc内容");
            blog.setStatus(BlogStatusType.CANCEL);
            session.insert("org.mybatis.example.mapper.BlogMapper.insertBlog", blog);
            //项目中经常需要 获取新增的用户的主键
            System.out.println(blog.getId());
            session.commit();
        } catch (Exception e) {
            logger.error("e", e);
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Test
    public void testUpdate() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        try {

            Blog blog = (Blog) session.selectOne("org.mybatis.example.mapper.BlogMapper.selectBlog", 1);

            blog.setTitle("cccx修改后");
            blog.setContent("ccc内容");

            session.update("org.mybatis.example.mapper.BlogMapper.updateBlog", blog);

            session.commit();
        } catch (Exception e) {
            logger.error("e", e);
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Test
    public void testUpdate2() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            HashMap<String, Object> map = new HashMap();
            map.put("id", 1);
            map.put("title", "aaaa1");

            session.update("org.mybatis.example.mapper.BlogMapper.updateBlog2", map);

            session.commit();
        } catch (Exception e) {
            logger.error("e", e);
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Test
    public void testDelete() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.delete("org.mybatis.example.mapper.BlogMapper.deleteBlog", 6);
            session.commit();
        } catch (Exception e) {
            logger.error("e", e);
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
