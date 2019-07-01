package com.neu.edu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.neu.edu.exceptions.PostsException;
import com.neu.edu.pojo.Application;
import com.neu.edu.pojo.ApplicationUser;
import com.neu.edu.pojo.JobRelatedData;

public class EmployerDao extends Dao {
	
	/*
	 * Method for saving the job details to the database
	 * @param JobRelatedData
	 * @return JobRelatedData
	 */
		public JobRelatedData postingAJob(JobRelatedData job) {
			try {
				begin();	
				getSession().save(job);
				commit();
				close();
				return job;
			}catch(HibernateException e) {
				rollback();
				e.printStackTrace();
				return null;
			}
		}
		
	/*
	 * Method for listing all the jobs posted by the employer in the prtal
	 * @param ApplicationUser
	 * @return list of JobRelatedData
	 */
		public List<JobRelatedData> listingJobPosts(ApplicationUser user) throws PostsException{
			try {
				begin();
				getSession();
				int userid = user.getUid();
				Query query = getSession().createQuery("from JobRelatedData where user =:userid ");
				query.setInteger("userid", userid);
				System.out.println("Printing Query: "+ query);
				List<JobRelatedData> jobPosts = query.list();
				commit();
				close();
				return jobPosts;
			}catch(HibernateException e) {
				rollback();
				throw new PostsException("Could not find any posts!", e);
			}
		}
		
		/*
		 * Method for deleting Job Post
		 * @param jobId
		 * @return boolean if job is deleted successful then true ofr else false
		 */
		public boolean deleteJobPost(long jobId) throws PostsException{
			try {
				begin();
				Query query = getSession().createQuery("delete from JobRelatedData where id =:jobid ");
				query.setLong("jobid", jobId);
				query.executeUpdate();
				commit();
				close();
				return true;
			}catch(HibernateException e) {
				rollback();
				throw new PostsException("Error while deleting the job post : ", e);
			}
		}
		
		/*
		 * Method for fetching the id to update the job post
		 * @param jobId
		 * @return JobRelatedData
		 */
		public JobRelatedData updatingJobPost(long jobid) throws PostsException{
			try {
				begin();
				Query query = getSession().createQuery("from JobRelatedData where id =:jobid ");
				query.setLong("jobid", jobid);
				JobRelatedData jobdetails = (JobRelatedData) query.uniqueResult();
				commit();
				close();
				return jobdetails;
			}catch(HibernateException e) {
				rollback();
				throw new PostsException("Could not update the job DAO problem", e);
			}
		}
		
		/*
		 * Method for updating the job post
		 */
		public JobRelatedData onClickUpdatingJobPost(JobRelatedData jobdata, long newJobid) throws PostsException{
			try {
				begin();
				String jobid = jobdata.getJobID();
				String title = jobdata.getJobTitle();
				String company = jobdata.getCompanyName();
				String jobType = jobdata.getJobType();
				String country = jobdata.getCountry();
				String state = jobdata.getState();
				String industry = jobdata.getIndustry();
				String major = jobdata.getMajor();
				String url = jobdata.getJobUrl();
				String description = jobdata.getDescription();
				
				Query query = getSession().createQuery("update JobRelatedData set jobID ='" + jobid + "' , jobTitle ='" + title + "', companyName = '" + company + "', jobType = '" + jobType + "', country = '" + country + "', state = '" + state + "', industry = '" + industry + "', major = '" + major + "', jobUrl = '" + url + "', description = '" + description + "' where id = '" + newJobid + "' ");
				query.executeUpdate();
				commit();
				close();
				return jobdata;
			}catch(HibernateException e) {
				rollback();
				throw new PostsException("Error during updating the job DAO Problem", e);
			}
		}
		
		/*
		 * Method to list all the candidates JobApplication applied to the post based on id
		 * @Param jobId
		 * @return list of applications
		 * 
		 */
		public List<Application> listingOfCandidatesApplied(long jobId) throws PostsException{
			try {
				begin();
				Query query = getSession().createQuery("from Application where jobData=:jobId ");
				query.setLong("jobId", jobId);
				List<Application> application = query.list();
				System.out.println("some list " + application);
				commit();
				close();
				return application;
			}catch (HibernateException e) {
	            rollback();
	            throw new PostsException("Could not find candidate", e);
	        }
		}
		
		/*
		 * Method to get list of candidate information using userid
		 * @param userId
		 * @return list of Application USer
		 */
		public List<ApplicationUser> gettingUserInfo(int userid) throws PostsException{
			try {
				begin();
				Query query = getSession().createQuery("from ApplicationUser where uid=:userid ");
				query.setInteger("userid", userid);
				List<ApplicationUser> userinfo = query.list();
				commit();
				close();
				return  userinfo;
			}catch (HibernateException e) {
	            rollback();
	            throw new PostsException("Could not find candidate info", e);
	        }
		}

}
