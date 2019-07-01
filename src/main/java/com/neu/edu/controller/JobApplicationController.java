package com.neu.edu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.neu.edu.utility.EmailUtility;

@Controller
@RequestMapping(value = "/")
public class JobApplicationController {
	
	@RequestMapping(value = "student/UploadPage.htm", method = RequestMethod.GET)
	public ModelAndView showingApplicationPage(HttpServletRequest req , EmployerDao empDao, ModelMap mapp)throws PostsException, StudentException {
		if(!CheckSession.checkForUserSession(req)) {
			mapp.addAttribute("errorMessage", "Session expired login again");
			return new ModelAndView("home");
		}else {
		
			ApplicationUser User = (ApplicationUser) req.getSession().getAttribute("name");
			HttpSession session = (HttpSession) req.getSession();
			String jobId = req.getParameter("jobID");
			long id = Long.parseLong(jobId);		
			JobRelatedData jobdata = empDao.updatingJobPost(id);
			session.setAttribute("jobID", jobdata);				
			req.getSession().setAttribute("name", User);
			System.out.println("User name " + User);
			
			return new ModelAndView("jobapplication");
		}
	}
	
	@RequestMapping(value = "student/apply.htm", method = RequestMethod.POST)
	public ModelAndView handlingFileUpload(HttpServletRequest req, StudentDao stuDao, @RequestParam CommonsMultipartFile[] fileUpload , ModelMap mapp) throws Exception {
		if(!CheckSession.checkForUserSession(req)) {
			mapp.addAttribute("errorMessage", "Session expired login again");
			return new ModelAndView("home");
		}else {
		
			boolean token = true;
			
			ApplicationUser User = (ApplicationUser) req.getSession().getAttribute("name");
			JobRelatedData jobData = (JobRelatedData) req.getSession().getAttribute("jobID");		
			System.out.println("JOBDETAILS are: " + jobData + " APPUSERS are: " + User);
			List<JobRelatedData> jd = new ArrayList<JobRelatedData>();	
	
			try {
				token = stuDao.doesUserExists(User, jobData);
				System.out.println("Token " + token);
				if (token == true) {
					return new ModelAndView("errors", "errorMessage", "You have already applied for this postion");
				} else {
						req.getSession().setAttribute("name", User);
						req.getSession().setAttribute("JobID", jobData);
						if (fileUpload != null && fileUpload.length > 0) {
							for (CommonsMultipartFile aFile : fileUpload) {
								System.out.println("Saving file names as: " + aFile.getOriginalFilename());
								Application uploadFile = new Application();
								uploadFile.setFileName(aFile.getOriginalFilename());
								uploadFile.setData(aFile.getBytes());
								uploadFile.setUser(User);
								uploadFile.setJobData(jobData);
								stuDao.savingFiles(uploadFile);
							}
					}
				}
				jd.add(jobData);
				req.getSession().setAttribute("jobList", jd);
				return new ModelAndView("all-jobs");
			} catch (StudentException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("errors", "errorMessage", "Error during file uploading : Controller problem");
	
			}
		}
	}
	
	@RequestMapping(value = "/student/withdrawMyApplication.htm", method = RequestMethod.GET)
	public ModelAndView deletingJobPost(HttpServletRequest req, StudentDao stuDao, ModelMap mapp) throws StudentException {
		if(!CheckSession.checkForUserSession(req)) {
			mapp.addAttribute("errorMessage", "Session expired login again");
			return new ModelAndView("home");
		}else {
		
			HttpSession session = (HttpSession) req.getSession();
			String jobId = req.getParameter("jobID");
			try {
				if (jobId.equals(null)) {
					System.out.println("No Id found ");
				} else {
					session.setAttribute("jobId", jobId);
					System.out.println("JOB ID : " + jobId);
					long id = Long.parseLong(jobId);
					stuDao.delete(id);
				}
				return new ModelAndView("withdraw-application");
			} catch (StudentException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("errors", "errorMessage", "Error during deleting the job application : Controller Problem");
			}
		}

	}
	
	@RequestMapping(value = "/job/emailstudent.htm", method = RequestMethod.GET)
	public ModelAndView sendingemail(HttpServletRequest req, StudentDao stuDao, ModelMap mapp , EmailUtility emailUtil) throws Exception {
		if(!CheckSession.checkForUserSession(req)) {
			mapp.addAttribute("errorMessage", "Session expired login again");
			return new ModelAndView("home");
		}else {
			
			ApplicationUser User = (ApplicationUser) req.getSession().getAttribute("name");
			HttpSession session = (HttpSession) req.getSession();
			String id = req.getParameter("jobID");
			session.setAttribute("name", User);
			//long jobid = Long.parseLong(id);
			
			String jobId = req.getParameter("jobID");
			String emailId = req.getParameter("email");
			String fname = req.getParameter("name");
			ModelAndView mav = new ModelAndView("student-emailed-page");
			System.out.println("email ID" + emailId);
			try {
				if (jobId.equals(null)) {
					System.out.println("No Id found ");
				} else {
					//session.setAttribute("name", fname);
					String emailBody = "Can we schedule a call to discuss details of the positon you applied at our company";
					Boolean flag = emailUtil.sendEmail("google.com", emailId, emailBody, "Hello");
					if(flag==true) {
						mapp.addAttribute("successMessage", "Email sent successfully to " + fname );
						
					}else {
						mapp.addAttribute("errorMessage", "Email was not sent");
					}										
				}
				mav.addObject("map", mapp);
				return mav;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ModelAndView("errors", "errorMessage", "email sending issue : Controller Problem");
			}
		}

	}

}
