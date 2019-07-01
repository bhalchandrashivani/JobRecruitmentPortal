package com.neu.edu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.pojo.Role;

public class RolesDao extends Dao {
	
	/*
	 * method to fetch role name if it exists
	 * @param role_name
	 * @return role assigned
	 */
	public  Role getRole(String role_name) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Role where roll_name=:role_name");
			q.setString("roll_name", role_name);
			Role role = (Role)q.uniqueResult();
			close();
			return role;
			
		}		
		catch(HibernateException e){
			rollback();
			throw new Exception("No Role Found");
		}		
	}
}
