package com.neu.edu.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="ROLE")
public class Role {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "roleId", unique=true , nullable=false)
	private int roleId;
	
	@Column (name = "roll_name")
	private String roll_name;
	

	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<ApplicationUser> user = new ArrayList<ApplicationUser>();


	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoll_name() {
		return roll_name;
	}

	public void setRoll_name(String roll_name) {
		this.roll_name = roll_name;
	}
	
	public List<ApplicationUser> getUser() {
		return user;
	}

	public void setUser(List<ApplicationUser> user) {
		this.user = user;
	
	}
	

}
