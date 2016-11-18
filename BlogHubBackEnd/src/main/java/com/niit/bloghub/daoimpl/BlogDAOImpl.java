package com.niit.bloghub.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bloghub.dao.BlogDAO;
import com.niit.bloghub.models.Blog;

@Repository(value = "blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	private static final Logger lg = LoggerFactory.getLogger(BlogDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addBlog(Blog b) {
		// TODO Auto-generated method stub
		lg.debug("Calling add Blog method");
		try {
			sessionFactory.getCurrentSession().save(b);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sessionFactory.close();
			return false;
		}
	}

	@Override
	public List<Blog> getAllBlog() {
		// TODO Auto-generated method stub
		String hql = "From Blog";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		lg.debug("Query is " + hql);
		return q.list();
	}

	@Override
	public boolean update(Blog b) {
		// TODO Auto-generated method stub
		lg.debug("Update method calling");
		try {
			sessionFactory.getCurrentSession().update(b);
			lg.debug("Blog updated");
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lg.debug("Update unsuccessful");
			return false;
		}
	}

	@Override
	public Blog getBlog(int id) {
		// TODO Auto-generated method stub
		lg.debug("get blog call");
		try {
			Blog b = (Blog) sessionFactory.getCurrentSession().get(Blog.class, new Integer(id));
			lg.debug("Blog is recieved");
			return b;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lg.debug("Could not get Blog");
			return null;
		}
	}

	@Override
	public boolean deleteBlog(int id) {
		// TODO Auto-generated method stub
		try {
			Blog b = (Blog) sessionFactory.getCurrentSession().get(Blog.class, new Integer(id));
			sessionFactory.getCurrentSession().delete(b);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
