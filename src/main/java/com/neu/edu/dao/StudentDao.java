package com.neu.edu.dao;

import com.neu.edu.pojo.ApplicationUser;
import com.neu.edu.exceptions.PostsException;
import com.neu.edu.exceptions.StudentException;
import com.neu.edu.pojo.Application;
import com.neu.edu.pojo.JobRelatedData;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

public class StudentDao extends Dao{
	
		/*
		 * Method to list all jobs from jobdata 
		 * @return list of job data
		 */
		public List<JobRelatedData> ListingAllJobs() throws StudentException {
			try {
				begin();
				Query query = getSession().createQuery("from JobRelatedData");
				List<JobRelatedData> allJobs = query.list();
				commit();
				close();
				return allJobs;
			} catch (HibernateException e) {
				rollback();
				throw new StudentException("Could not find employer", e);
			}
		}

		
		/*
		 *  Method for uploading files for job application
		 */
		public void savingFiles(Application uploadFile) throws StudentException {
				try {
					begin();
					getSession().save(uploadFile);
					close();
				}catch(HibernateException e) {
					rollback();
					e.printStackTrace();
					throw new StudentException("Error during uploading files :DAO problem",e);
				}
			}
		
		
		/*
		 * Method for fetching application id from User id
		 * @return List of application
		 */
		public Application gettingJobId(ApplicationUser user) throws PostsException{
			try {
				begin();
				Query query = getSession().createQuery("from Application where user = '" + user + "' ");
				Application application = (Application) query.uniqueResult();
				commit();
				close();
				return application;
			}catch(HibernateException e) {
				rollback();
				throw new PostsException("Could not update the job :DAO problem", e);
			}
		}
		
		/*
		 * Method to check if the user exists
		 * @param ApplicationUser
		 * @param JobRelatedData
		 * @return boolean result
		 */
		public boolean doesUserExists(ApplicationUser user, JobRelatedData jobdata)throws StudentException{
			try {
				boolean result = true;
				begin();
				Criteria criteria = getSession().createCriteria(Application.class);
				criteria.add(Restrictions.eq("jobData",jobdata));
				criteria.add(Restrictions.eq("user", user));
				List <Application> out = criteria.list();
				commit();
				close();
				System.out.println("Result" + out.size());
				if(out.size() >= 2) {
						result = true;
					}
					else {
						result = false;
					}
				return result;
				
			}catch(HibernateException e) {
				rollback();
				e.printStackTrace();
				throw new StudentException(" Error during fetching user : DAO problem",e);
			}
		}
		
		
		/*
		 * Method for listing all applications of the candidate
		 * @param ApplicationUser
		 * @return list of Applications
		 */
		public List<Application> listingAppliedJobs(ApplicationUser users)throws StudentException{
			
			try {
				begin();
				Query query = getSession().createQuery("from Application where user = '" + users.getUid() + "' ");
				List<Application> jobApplications = query.list();
				commit();
				close();
				return jobApplications;
			}catch(HibernateException e) {
				rollback();
				e.printStackTrace();
				throw new StudentException("Error occured in fetching data : DAO problem",e);
			}
		}
		
		/*
		 * Method for listing all the job details based on the job Id
		 * @param JObId
		 * @return List of job data
		 */
		public List<JobRelatedData> allJobDetails(long jobId) throws StudentException{
			try {						
				begin();
				Query query = getSession().createQuery("from JobRelatedData where id =:jobId ");
				query.setLong("jobId", jobId);
				List<JobRelatedData> jobDetails = query.list();
				commit();
				close();
				return jobDetails;
			}catch(HibernateException e) {
				rollback();
				e.printStackTrace();
				throw new StudentException("Error occured in fetching data : DAO problem",e);
			}
		}
		
		/*
		 * Method for deleting Job Application
		 * @param job Id
		 * @return boolean flag true if deleted successfully or false
		 */
		public boolean delete(long jobId) throws StudentException{
			try {
				begin();
				Query query = getSession().createQuery("delete from Application where jobData =:jobId ");
				query.setLong("jobId", jobId);
				query.executeUpdate();
				commit();
				close();
				return true;
			}catch(HibernateException e) {
				rollback();
				throw new StudentException("Error in deleting the job post : Dao problem", e);
			}
		}
}
