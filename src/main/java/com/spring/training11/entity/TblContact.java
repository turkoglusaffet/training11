package com.spring.training11.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_contact database table.
 * 
 */
@Entity
@Table(name="tbl_contact")
@NamedQuery(name="TblContact.findAll", query="SELECT t FROM TblContact t")
public class TblContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=20)
	private String detail;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private TblUser user;

	public TblContact() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public TblUser getUser() {
		return this.user;
	}

	public void setUser(TblUser user) {
		this.user = user;
	}

}