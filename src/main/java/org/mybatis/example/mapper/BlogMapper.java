package org.mybatis.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.example.bean.Blog;

public interface BlogMapper {

    Blog selectBlog(int id);

    @Select("SELECT * FROM blog WHERE id = #{id}")
    Blog selectBlog3(int id);
}
