package com.neu.edu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.EmployerDao;
import com.neu.edu.exceptions.PostsException;
import com.neu.edu.pojo.Application;
import com.neu.edu.pojo.ApplicationUser;
import com.neu.edu.pojo.JobRelatedData;
import com.neu.edu.utility.CheckSession;

@Controller
@RequestMapping(value = "/*")
public class EmployerController  {
	

	/*
	 * Method to post a job  portal
	 * @param HttpServletRequest
	 * @param UserDao
	 * @param ModelMap
	 * @return ModelAndView for view name postajob
	 */
		@RequestMapping(value = "employer/postajob.htm", method = RequestMethod.GET)
		public ModelAndView gettingJobPostForm(HttpServletRequest req , ModelMap mapp) throws Exception {
			
			if(!CheckSession.checkForUserSession(req)) {
				mapp.addAttribute("errorMessage", "Session expired login again");
				return new ModelAndView("home");
			}else {
				ModelAndView mav = new ModelAndView();
				ApplicationUser User = (ApplicationUser) req.getSession().getAttribute("name");
				req.getSession().setAttribute("name", User);
				mav.setViewName("postajob");
				return mav;
			}
		}

		/*
		 * method called after clicking for posting a job
		 * @param HttpServletRequest
		 * @param EmployerDao
		 * @param ModelMap
		 * @return ModelAndView employerhomepage for success or postajob page in case of failure
		 */
		@RequestMapping(value = "employer/jobpostsuccess.htm", method = RequestMethod.POST)
		public ModelAndView postingAJob(HttpServletRequest req, EmployerDao eDao, ModelMap map) {
			
			if(!CheckSession.checkForUserSession(req)) {
				map.addAttribute("errorMessage", "Session expired login again");
				return new ModelAndView("home");
			}else {
				ApplicationUser User = (ApplicationUser) req.getSession().getAttribute("name");
	
				String jobId = req.getParameter("job_id");
				String title = req.getParameter("jobtitle");
				String major = req.getParameter("major");
				String jobDescUrl = req.getParameter("job_url");
				String majCategory = req.getParameter("majCategory");			
				String jobDesc = req.getParameter("job_description");
				String company = req.getParameter("job_company_name");
				String jobType = req.getParameter("job_type");
				String country = req.getParameter("country");
				String state = req.getParameter("state");
				Date jobpostedOn = new Date();
	
				
				JobRelatedData jobData = new JobRelatedData();
				jobData.setJobID(jobId);
				jobData.setJobTitle(title);
				jobData.setJobUrl(jobDescUrl);
				jobData.setDescription(jobDesc);
				jobData.setJobpostedOn(jobpostedOn);			
				jobData.setState(state);
				jobData.setIndustry(majCategory);
				jobData.setMajor(major);
				jobData.setUser(User);
				jobData.setCompanyName(company);
				jobData.setJobType(jobType);
				jobData.setCountry(country);
	
				try {
					jobData = eDao.postingAJob(jobData);
					if (jobData != null) {
						map.addAttribute("successMessage", "Job posted successfully!");
						return new ModelAndView("employerhomepage", "jobPost", jobData);
					} else {
						map.addAttribute("errorMessage", "Error during saving your job posting. Try again!");
						return new ModelAndView("postajob");
					}
	
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error while saving job details");
				}
				return null;
			}

		}

		
		/*
		 * Method to list all jobs posted by a particular employer
		 * @param HttpServletRequest
		 * @param EmployerDao
		 * @param ModelMap
		 * @return ModelAndView employers-all-posted-jobs for success or home page in case of failure
		 */
		@RequestMapping(value = "/employer/allmyjobposts.htm", method = RequestMethod.GET)
		public ModelAndView listingMyPostedJobs(HttpServletRequest req, EmployerDao eDao , ModelMap mapp) {
			if(!CheckSession.checkForUserSession(req)) {
				mapp.addAttribute("errorMessage", "Session expired login again");
				return new ModelAndView("home");
			}else {
				try {
					
					ApplicationUser user = (ApplicationUser) req.getSession().getAttribute("name");
					System.out.println("FIRST NAME: " + user);
					List<JobRelatedData> jobPost = eDao.listingJobPosts(user);
					return new ModelAndView("employers-all-posted-jobs", "jobPost", jobPost);
				} catch (PostsException e) {
					System.out.println(e.getMessage());
					return new ModelAndView("errors", "errorMessage", "Error occured while displaying your posted jobs");
				}
			}
		}

		
		
		/*
		 * Method to update job details
		 * @param HttpServletRequest
		 * @param EmployerDao
		 * @param ModelMap
		 * @return ModelAndView update-job-details for success or home page in case of failure
		 */
		@RequestMapping(value = "/employer/updateJobData.htm", method = RequestMethod.GET)
		public ModelAndView updatingJobPost(HttpServletRequest req, EmployerDao eDao , ModelMap mapp) {
			if(!CheckSession.checkForUserSession(req)) {
				mapp.addAttribute("errorMessage", "Session expired login again");
				return new ModelAndView("home");
			}else {
				ApplicationUser User = (ApplicationUser) req.getSession().getAttribute("name");
				HttpSession session =  req.getSession();
				String id = req.getParameter("jobID");			
				session.setAttribute("id", id);
				session.setAttribute("name", User);
				long jobId = Long.parseLong(id);
				
				try {
					JobRelatedData jobData = eDao.updatingJobPost(jobId);
					req.setAttribute("jobData", jobData);
					System.out.println("Updaing post for  "+ jobData.getCompanyName());
					return new ModelAndView("update-job-details", "jobData", jobData);
				}catch(PostsException e) {
					return new ModelAndView("errors", "errorMessage", "Error occured during updating the job post");
				}
			}
			
		}
		
		/*
		 * Method for deleting a job postition
		 *  @param HttpServletRequest
		 * @param EmployerDao
		 * @param ModelMap
		 * @return ModelAndView update-job-details for success or home page in case of failure
		 */
		@RequestMapping(value = "/employer/deleteJobPost.htm", method = RequestMethod.GET)
		public ModelAndView deletingJobPost(HttpServletRequest req, EmployerDao eDao, ModelMap mapp) {
			if(!CheckSession.checkForUserSession(req)) {
				mapp.addAttribute("errorMessage", "Session expired login again");
				return new ModelAndView("home");
			}else {
				HttpSession session = (HttpSession) req.getSession();
				String jobId = req.getParameter("jobID");
				try {
					if (jobId.equals(null)) {
						System.out.println("No id found");
					} else {
						session.setAttribute("jobId", jobId);
						long id = Long.parseLong(jobId);
						eDao.deleteJobPost(id);
					}
					return new ModelAndView("deletejobpostsuccess");
				} catch (PostsException e) {
					System.out.println(e.getMessage());
					return new ModelAndView("errors", "errorMessage", "Error occured during deleting the job post");
				}
			}

		}
		
		
		/*
		 * method to update job description adn details
		 * 
		 * @param HttpServletRequest
		 * @param EmployerDao
		 * @param ModelMap
		 * @param JobRelatedData
		 * @return ModelAndView employerhomepage or home in case of error
		 * 
		 */
		@RequestMapping(value = "/employer/updateJobData.htm", method = RequestMethod.POST)
		public ModelAndView onUpdate(HttpServletRequest request, EmployerDao eDao, ModelMap map, @ModelAttribute("jobData") JobRelatedData jobData) {
			if(!CheckSession.checkForUserSession(request)) {
				map.addAttribute("errorMessage", "Session expired login again");
				return new ModelAndView("home");
			}else {
			
				ApplicationUser appUsers = (ApplicationUser) request.getSession().getAttribute("name");
				HttpSession session =  request.getSession();
				String id = (String)session.getAttribute("id");
							
				String jobId = request.getParameter("job_id");
				String title = request.getParameter("jobtitle");
				String company = request.getParameter("job_company_name");
				String jobType = request.getParameter("job_type");
				String country = request.getParameter("country");
				String state = request.getParameter("state");
				String majCategory = request.getParameter("majCategory");
				String major = request.getParameter("major");
				String jobDescUrl = request.getParameter("job_url");
				String jobDesc = request.getParameter("job_description");
				Date postedOn = new Date();
				
				jobData.setJobID(jobId);
				jobData.setJobTitle(title);
				jobData.setDescription(jobDesc);
				jobData.setJobpostedOn(postedOn);
				jobData.setCompanyName(company);
				jobData.setIndustry(majCategory);
				jobData.setMajor(major);
				jobData.setJobUrl(jobDescUrl);			
				jobData.setJobType(jobType);
				jobData.setCountry(country);
				jobData.setState(state);
				
				
				
				try {
					long newJobId = Long.parseLong(id);
					JobRelatedData details = eDao.onClickUpdatingJobPost(jobData, newJobId);
					request.getSession().setAttribute("updatedjobData", details);
					session.setAttribute("name", appUsers);
					map.addAttribute("successMessage", "Your job has been updated successfully!");
					return new ModelAndView("employerhomepage", "jobData", details);
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error in updating job details");
				}
				return null;
			}
			
		}
		
		/*
		 * method to list all students applied
		 * 
		 * @param HttpServletRequest
		 * @param EmployerDao
		 * @param ModelMap
		 * @return ModelAndView students-applied or home in case of error
		 * 
		 */
		@RequestMapping(value = "/employer/viewCandidatesApplied", method = RequestMethod.GET)
		public ModelAndView listingCandidatesApplied(HttpServletRequest request,EmployerDao eDao, ModelMap mapp) {
			if(!CheckSession.checkForUserSession(request)) {
				mapp.addAttribute("errorMessage", "Session expired login again");
				return new ModelAndView("home");
			}else {
				try{
					ApplicationUser User = (ApplicationUser) request.getSession().getAttribute("name");
					HttpSession session = request.getSession();
					String id = request.getParameter("jobID");
					session.setAttribute("id", id);
					session.setAttribute("name", User);
					long jobid = Long.parseLong(id);
					System.out.println("JOB_ID is : "+jobid);
					ModelAndView mav = new ModelAndView("students-applied");
					List<Application> listApplication = eDao.listingOfCandidatesApplied(jobid);
					HashMap<Long, List<ApplicationUser>> map = new HashMap<Long, List<ApplicationUser>>();
					Set<Integer> set = new HashSet<Integer>();
					for(Application ja : listApplication) {
						int userid = ja.getUser().getUid();
						System.out.println("USER_ID"+userid);
						if(!set.contains(userid))
						{
							set.add(userid);
							map.put(ja.getId(), eDao.gettingUserInfo(userid));						
						}
						mav.addObject("listapp",listApplication);
					}
					mav.addObject("listuser", map);
					return mav;
					}catch (Exception e) {
						e.printStackTrace();
						System.out.println("Error while viewing candidate details");
					}
					return null;
			}
		}
		
		/*public void printMap(HashMap<Long, List<ApplicationUser>> map) {
			for (Entry<Long, List<ApplicationUser>> entry : map.entrySet()) {
				System.out.println("key : " + entry.getKey());
			}
		}*/
	}


