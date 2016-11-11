package com.niit.bloghub.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.bloghub.models.Blog;

public interface BlogDAO {

	boolean addBlog(Blog b);
	List<Blog> getAllBlog();
	boolean update(Blog b);
	Blog getBlog(String id);
	boolean deleteBlog(String id);
}
