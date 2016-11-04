package com.niit.bloghub.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bloghub.dao.BlogDAO;
import com.niit.bloghub.models.Blog;

@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sf;
	
	public BlogDAOImpl(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	@Override
	public boolean addBlog(Blog b) {
		// TODO Auto-generated method stub
		return false;
	}

}
