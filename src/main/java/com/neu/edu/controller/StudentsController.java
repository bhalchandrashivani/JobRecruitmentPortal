package com.neu.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.EmployerDao;
import com.neu.edu.dao.StudentDao;
import com.neu.edu.exceptions.PostsException;
import com.neu.edu.exceptions.StudentException;
import com.neu.edu.pojo.Application;
import com.neu.edu.pojo.ApplicationUser;
import com.neu.edu.pojo.JobRelatedData;
import com.neu.edu.utility.CheckSession;

@Controller
@RequestMapping(value = "/")
public class StudentsController {

	/*
	 * method to fetch all open and posted jobs
	 * @param HttpServletRequest
	 * @param StudentDao
	 * @param ModelMap
	 * @return ModelAndView home if invalid session or all-jobs page
	 */
	@RequestMapping(value = "student/alljobs.htm", method = RequestMethod.GET)
	public ModelAndView viewingAllJobs(HttpServletRequest request, StudentDao stuDao , ModelMap map) throws Exception {
		if(!CheckSession.checkForUserSession(request)) {
			map.addAttribute("errorMessage", "Session expired kindly login again");
			return new ModelAndView("home");
		}else {
		ApplicationUser User = (ApplicationUser) request.getSession().getAttribute("name");
		try {
			request.getSession().setAttribute("name",User);
			List<JobRelatedData> alljobs = stuDao.ListingAllJobs();
			request.getSession().setAttribute("jobID", alljobs);
			return new ModelAndView("all-jobs", "allJobs", alljobs);
		} catch (StudentException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("errors", "errorMessage", "Error during retrieving all jobs : Controller Problem");
		}
		}
	}

	/*
	 * Method to view all applications made for different positions
	 * @param HttpServletRequest
	 * @param StudentDao
	 * @param ModelMap
	 * @return  ModelAndView home if invalid session or view-applied-jobs page
	 */
	@RequestMapping(value = "student/viewMyJobs.htm", method = RequestMethod.GET)
	public ModelAndView listingAllApplications(HttpServletRequest req, StudentDao stuDao , ModelMap mapp) throws Exception {
		if(!CheckSession.checkForUserSession(req)) {
			mapp.addAttribute("errorMessage", "Session expired kindly login again");
			return new ModelAndView("home");
		}else {
			try {
				ApplicationUser appUsers = (ApplicationUser) req.getSession().getAttribute("name");			
				System.out.println("USERID: " + appUsers.getUid());
				List<Application> applicationsDetails = stuDao.listingAppliedJobs(appUsers);			
				HashMap<Long, List<JobRelatedData>> map = new HashMap<Long, List<JobRelatedData>>();			
				System.out.println("ApplicationsDetails size" + applicationsDetails.size());
				ModelAndView mav = new ModelAndView("view-applied-jobs");
				
				for (Application ja : applicationsDetails) {
					long jobId = ja.getJobData().getId();				
					map.put(jobId, stuDao.allJobDetails(jobId));				
				}
				mav.addObject("map", map);
				return mav;
	
			} catch (Exception e) {
				System.out.println("Error " + e.getMessage());
				return new ModelAndView("errors", "errorMessage", "Error during view apllied jobs: Controller problem");
			}
		}
	}
	
}
