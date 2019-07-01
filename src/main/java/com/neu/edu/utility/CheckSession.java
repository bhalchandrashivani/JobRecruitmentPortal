package com.neu.edu.utility;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckSession {

		public static  Boolean checkForUserSession(HttpServletRequest request)
		{
			if(request.getSession(false)!=null)
			{
				System.out.println("session does exist");				
				if(request.getSession().getAttribute("name")!=null)
				{
					System.out.println("session does  exists 2");
					return true;
				}
				System.out.println("no user in session");
				return false;									
			}
			
			System.out.println(" no user in session");			
			return false;
		}
		
		public static HttpSession getSessionForUser(HttpServletRequest request, String username)
		{
			if(checkForUserSession(request)==false)
			{
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				System.out.println("session for user : "+username);
				return session;
			}
			else
			{
				return request.getSession();
			}
		}
}


