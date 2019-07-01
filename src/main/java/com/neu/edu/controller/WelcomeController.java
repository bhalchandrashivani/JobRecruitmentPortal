package com.neu.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.RolesDao;
import com.neu.edu.dao.UserDao;
import com.neu.edu.pojo.ApplicationUser;
import com.neu.edu.pojo.Role;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Controller
@RequestMapping(value = "/")
public class WelcomeController {
	
	/*
	 * Method to register into the portal
	 * @param HttpServletRequest
	 * @param UserDao
	 * @param ModelMap
	 * @return String for view name
	 */
	@RequestMapping(value = "/register.htm", method = RequestMethod.POST)
	public String registerUser(HttpServletRequest req , UserDao udao , ModelMap map) {
		
		System.out.println("Inside registerUser controller");
		boolean token = true;
		
		String firstname = req.getParameter("fname");
		String lastname = req.getParameter("lname");
		String email = req.getParameter("email");
		String username = req.getParameter("uname");
		String password = req.getParameter("pwd");
		String rolename= req.getParameter("role");
		
		ApplicationUser user = new ApplicationUser();
		Role role = new Role();
		token = udao.registerCriteria(email, username);
		if(token == true) {
			map.addAttribute("errorMessage", "Username or EmailId has already been registered");
			return "home";
		}
		
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
				
		role.setRoll_name(rolename);
		user.setFname(firstname);
		user.setLname(lastname);
		user.setEmail(email);
		user.setUsername(username);
		user.setPwd(password);
		user.setPwdHash(hashedPassword);
		user.setRole(role);
		
		try {
			user = udao.registerUser(user);
			map.addAttribute("successMessage", "Registration is Successful!");
			return "home";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;				
	}

	
	/*
	 * Method to logout 
	 * @param HttpServletRequest
	 * @return String name of the view
	 */
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	protected String loggingOut(HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		session.invalidate();
		System.out.println("User successfully logged out");
		return "home";		
	}

	
	/*
	 * Method to login into the portal
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param UserDao
	 * @return ModelAndView employer home page for employee role and Student home page for student role
	 */
	@RequestMapping(value = "/login.htm", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView loggingInRequest(HttpServletRequest req, HttpServletResponse response, UserDao appUsersDao, RolesDao roleDao, ModelMap map){
		
		HttpSession httpSession = req.getSession(true);
		String uname = req.getParameter("uname");
		System.out.println("USERNAME :" + uname);
		String password = req.getParameter("pwd");
		try {
			ApplicationUser users = appUsersDao.get(uname, password);
			if(users != null) {
				httpSession.setAttribute("name", users);
	
					if(users.getRole().getRoll_name().equalsIgnoreCase("employer")) {
						return new ModelAndView( "employerhomepage", "name",users);
					}else if(users.getRole().getRoll_name().equalsIgnoreCase("student")) {
						return new ModelAndView( "studenthomepage", "name", users);
					}else {
						map.addAttribute("errorMessage", "Invalid username/password!");
						return new ModelAndView("home");
					}
			}else {
				map.addAttribute("errorMessage", "Invalid username/password!");
				return new ModelAndView("home");
			}
		}catch(Exception e) {			
			e.printStackTrace();
		}		
		return null;
	}
	
}
