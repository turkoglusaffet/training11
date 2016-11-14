package com.spring.training11.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_role database table.
 * 
 */
@Entity
@Table(name="tbl_role")
@NamedQuery(name="TblRole.findAll", query="SELECT t FROM TblRole t")
public class TblRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="role_name", nullable=false, length=20)
	private String roleName;

	//bi-directional many-to-many association to TblUser
	@ManyToMany
	@JoinTable(
		name="tbl_role_user"
		, joinColumns={
			@JoinColumn(name="role_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="user_id", nullable=false)
			}
		)
	private List<TblUser> users;

	public TblRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<TblUser> getUsers() {
		return this.users;
	}

	public void setUsers(List<TblUser> users) {
		this.users = users;
	}

}