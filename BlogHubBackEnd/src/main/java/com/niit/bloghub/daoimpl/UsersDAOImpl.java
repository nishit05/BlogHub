package com.niit.bloghub.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.bloghub.dao.UsersDAO;
import com.niit.bloghub.models.Users;

@Repository(value = "usersDAO")
@Transactional
public class UsersDAOImpl implements UsersDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public UsersDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private boolean isNotValidId(int id)
	{
		int ct=0;
		for (Users u:getallUsers())
		{
			if(u.getU_id()==id)
				ct++;
		}
		if(ct==0)
			return true;
		else
			return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getallUsers() {
		// TODO Auto-generated method stub
		String hql = "From Friends";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public boolean addUsers(Users u) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(u);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Users getUser(String username, String password) {
		// TODO Auto-generated method stub

		try {
			String h = "From Friends where username=" + "'" + username + "'" + "and password=" + "'" + password + "'";
			Query q = sessionFactory.getCurrentSession().createQuery(h);
			Users u = (Users) q.uniqueResult();
			return u;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Users getUserById(int id) {
		// TODO Auto-generated method stub
		if(isNotValidId(id))
		{
			return null;
		}
		else
		{
		try {
			Users u = (Users) sessionFactory.getCurrentSession().get(Users.class, new Integer(id));
			return u;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		}
	}

	@Override
	public boolean update(Users u) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(u);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteUser(Users u) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(u);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	// public boolean validate(String username, String password) {
	// // TODO Auto-generated method stub
	// int ct=0;
	// List<Users>ul=getallUsers();
	// for(Users p:ul)
	// {
	// if(p.getUsername().equals(username) || p.getPassword().equals(password))
	// ct++;
	// }
	// if(ct==0)
	// return false;
	// else
	// return true;
	// }

}
