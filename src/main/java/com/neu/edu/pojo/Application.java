package com.neu.edu.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Table;

@Entity
@Table(name = "APPLICATIONS")
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, nullable = false)
	private long id;
	
	@Column(name = "FILENAME", length = 100)
	private String fileName;
	
	@Column(name = "FILEDATA", length = 1000000000)
	private byte[] data;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "uid")
	private ApplicationUser user;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "jobId")
	private JobRelatedData jobData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public ApplicationUser getUser() {
		return user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	public JobRelatedData getJobData() {
		System.out.println("getting jobData" + jobData);
		return jobData;
	}

	public void setJobData(JobRelatedData jobData) {
		this.jobData = jobData;
	}

	
}	