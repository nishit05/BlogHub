package com.niit.bloghub.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.bloghub.dao.BlogDAO;
import com.niit.bloghub.models.Blog;

@RestController
public class BlogController {

	@Autowired
	private BlogDAO blogDAO;

	@Autowired
	private Blog blog;

	public Date giveDate(Date d) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		String ds = df.format(d);
		try {
			Date bd = df.parse(ds);
			return bd;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "addblog", method = RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
		blog.setB_date(giveDate(new Date()));
		if (blogDAO.addBlog(blog)) {
			blog.setCode(200);
			blog.setErrormsg("Blog added successfully");
		} else {
			blog.setCode(500);
			blog.setErrormsg("Blog cannot be added...please contact admin for detatils");
		}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping(value = "getblog/{id}", method = RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable(value = "id") String id) {
		blog = blogDAO.getBlog(id);
		if (blog == null) {
			blog = new Blog();
			blog.setCode(404);
			blog.setErrormsg("Blog not found for " + id);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);

	}

	@RequestMapping(value = "bloglist", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getAllBlogs() {
		List<Blog> bl = blogDAO.getAllBlog();
		System.out.println("calling get all");
		if (bl.isEmpty()) {
			blog.setCode(404);
			blog.setErrormsg("No Blog not found");
			bl.add(blog);
		}
		return new ResponseEntity<List<Blog>>(bl, HttpStatus.OK);
	}
	
	@RequestMapping(value="blogaccept/{id}",method=RequestMethod.GET)
	public ResponseEntity<Blog> accept(@PathVariable(value="id")String id)
	{
		blog=blogDAO.getBlog(id);
		if(blog==null)
		{
			blog=new Blog();
			blog.setCode(404);
			blog.setErrormsg("Blog not found for "+id);
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		else
		{
			blog.setStatus("approved");
			blogDAO.update(blog);
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
	}
}
