package com.niit.bloghub.generator;

public class IdGenerator {

	public static String generateId(String id)
	{
		Integer i=Integer.parseInt(id);
		Integer u=1000+i;
		String k=u.toString();
		return k;
	}
}
