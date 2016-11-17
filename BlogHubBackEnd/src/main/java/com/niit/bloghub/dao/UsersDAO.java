package com.niit.bloghub.dao;

import java.util.List;

import com.niit.bloghub.models.Users;

public interface UsersDAO {

	boolean addUsers(Users u);
	List<Users> getallUsers();
	Users getUser(String username,String password);
	Users getUserById(int id);
	boolean update(Users u);
}
