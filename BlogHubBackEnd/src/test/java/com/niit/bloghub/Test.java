package com.niit.bloghub;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.bloghub.dao.BlogDAO;
import com.niit.bloghub.models.Blog;

public class Test {

	public static void main(String ...a)
	{
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext();
		app.scan("com.niit.bloghub");
		app.refresh();
		BlogDAO blogdao=(BlogDAO) app.getBean("blogDAO");
		Blog b =new Blog();
		b.setB_id(678);
		b.setTitle("Sports");
		b.setContent("This is a Sports Blog");
		b.setU_id("u78");
		b.setB_date(new Date());
		b.setStatus("pending");
		blogdao.addBlog(b);
	}
}
