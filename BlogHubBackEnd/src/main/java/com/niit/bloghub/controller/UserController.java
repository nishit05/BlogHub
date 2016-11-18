package com.niit.bloghub.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.bloghub.dao.UsersDAO;
import com.niit.bloghub.models.Users;

@RestController
public class UserController {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private Users users;
	
	@Autowired
	private HttpSession session;

	private ResponseEntity<Users>UserState(Users u,String st,String m)
	{
		if(u==null)
		{
			return isNull(u);
		}
		else
		{
			u.setStatus(st);
			u.setReason(m);
			usersDAO.update(u);
		}
		return new ResponseEntity<Users>(u,HttpStatus.OK);
	}
	
	private ResponseEntity<Users> isNull(Users u)
	{
			u=new Users();
			u.setCode(404);
			u.setErrormsg("User not found");
			u.setType("No User");
		return new ResponseEntity<Users>(u,HttpStatus.OK);	
	}
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<Users> addUser(@RequestBody Users users) {
		if (usersDAO.addUsers(users)) {
			users.setCode(200);
			users.setErrormsg("User registered successfully");
		} else {
			users.setCode(500);
			users.setErrormsg("User cannot be added please contact admin");
		}

		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "userslist", method = RequestMethod.GET)
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> ul = usersDAO.getallUsers();
		if (ul.isEmpty()) {
			users.setCode(404);
			users.setErrormsg("No users found");
		}
		return new ResponseEntity<List<Users>>(ul, HttpStatus.OK);
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<Users> validateUser(@RequestBody Users u) {
		String us=u.getUsername();
		String pw=u.getPassword();
		users=usersDAO.getUser(us, pw);
		if (users != null) {
			users.setOnline("Yes");
			usersDAO.update(users);
			users.setCode(200);
			users.setErrormsg("Successful Login");
		} else {
			users=new Users();
			users.setType("Unaunthenticated");
			users.setCode(500);
			users.setErrormsg("Invalid Credentials..Please Try again");
		}
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value="getuser/{id}",method=RequestMethod.GET)
	public ResponseEntity<Users>getUser(@PathVariable("id")int id)
	{
		users=usersDAO.getUserById(id);
		if(users!=null)
		{
			return new ResponseEntity<Users>(users,HttpStatus.OK);
		}
		else
		{
			return isNull(users);
		}
	}
	
	@RequestMapping(value="useraccept/{id}",method=RequestMethod.GET)
	public ResponseEntity<Users>userAccept(@PathVariable("id")int id)
	{
		users=usersDAO.getUserById(id);
		return UserState(users, "Accepted", "No Reason");
	}
	
	@RequestMapping(value="userreject/{id}",method=RequestMethod.GET)
	public ResponseEntity<Users>userDecline(@PathVariable("id")int id)
	{
		users=usersDAO.getUserById(id);
		return UserState(users, "Rejected", "User not found in "+users.getType()+" records");
	}
	
	@RequestMapping(value="logout/{id}",method=RequestMethod.GET)
	public ResponseEntity<Users>logout(@PathVariable("id")int id)
	{
		users=usersDAO.getUserById(id);
		if(users!=null)
		{
		if(users.getOnline().equalsIgnoreCase("yes"))
		{
			users.setOnline("No");
			users.setCode(200);
			users.setErrormsg("Logged out successfully");
			usersDAO.update(users);
			session.invalidate();
		}
		return new ResponseEntity<Users>(users,HttpStatus.OK);
		}
		else
		{
			return isNull(users);
		}
		
	}
	
	@RequestMapping(value="userdelete/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<Users>>userRemove(@PathVariable("id") int id)
	{
		users=usersDAO.getUserById(id);
		if(usersDAO.deleteUser(users))
		{
			ResponseEntity<List<Users>>re=getAllUsers();
			return re;
		}
		else
		return getAllUsers();
	}
}
