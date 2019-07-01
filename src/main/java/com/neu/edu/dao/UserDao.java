package com.neu.edu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.neu.edu.pojo.ApplicationUser;

public class UserDao extends Dao {
	
	public UserDao() {
		
	}
	
	/*
	 * Method to feetch valid user
	 * @param username
	 * @param passsword
	 * @return application User
	 */
	public ApplicationUser get(String username, String password) throws Exception{
		try {
			begin();
			
			Query query = getSession().createQuery("from ApplicationUser where username=:username");
			query.setString("username", username);
			ApplicationUser user = (ApplicationUser) query.uniqueResult();
			close();
			
			if(user == null) {
				System.out.println("No User found in DAO Class");
				return null;
			}else {
				
				System.out.println("User "+user.getFname()+" has been logged in");
				Boolean pwdCheck = false;
				if (BCrypt.checkpw(password, user.getPwdHash()))
				{
					System.out.println("user is matched");
					pwdCheck=true;
				}
				System.out.println("PWDCHECK: "+pwdCheck);
				if(pwdCheck== true) {
					return user;
				}
				else
					return null;
				
			}
		
		}catch(HibernateException e){
			rollback();
			throw new Exception("Invalid username or password" + e);
		}
		
	}
	
	/*
	 *  Method for registration of new user
	 *  @param ApplicationUser
	 */
	public ApplicationUser registerUser(ApplicationUser user) {
		try {
			begin();
			getSession().save(user);
			commit();
			close();
			return user;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			return null;
		}
		
	}
	
	/*
	 * method to match Criteria for successful registration
	 * @param email 
	 * @param username
	 * @retrun boolean true or false
	 */
	public boolean registerCriteria(String email, String username) {
		try {
			boolean result = true;
			System.out.println("Inside CRITERIA METHOD");
			begin();
			Criteria criteria = getSession().createCriteria(ApplicationUser.class);
			criteria.add(Restrictions.eq("email", email));
			List<ApplicationUser> crit1 = criteria.list();
			criteria.add(Restrictions.eq("username", username));
			List<ApplicationUser> crit2 = criteria.list();
			System.out.println("EMAIL SIZE "+crit1.size()+" USERNAME SIZE"+crit2.size());
			commit();
			close();
			if((crit1.size() == 1) || (crit2.size() == 1)) {
				result = true;
			}else {
				result = false;
			}
			return result;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		return true;
	}


}
