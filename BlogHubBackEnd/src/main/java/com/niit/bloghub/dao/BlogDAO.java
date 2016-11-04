package com.niit.bloghub.dao;

import org.springframework.stereotype.Repository;

import com.niit.bloghub.models.Blog;

@Repository(value="blogDAO")
public interface BlogDAO {

	boolean addBlog(Blog b);
}
