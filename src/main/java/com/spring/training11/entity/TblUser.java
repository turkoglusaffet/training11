package com.spring.training11.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the tbl_user database table.
 * 
 */
@Entity
@Table(name="tbl_user")
@NamedQuery(name="TblUser.findAll", query="SELECT t FROM TblUser t")
public class TblUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=50)
	private String email;

	@Column(name="first_name", nullable=false, length=20)
	private String firstName;

	@Column(name="last_name", nullable=false, length=20)
	private String lastName;

	@Column(length=45)
	private String password;

	@Column(length=45)
	private String username;

	@JsonIgnore
	//bi-directional many-to-one association to TblContact
	@OneToMany(mappedBy="user")
	private List<TblContact> contacts;

	@JsonIgnore
	//bi-directional many-to-many association to TblRole
	@ManyToMany(mappedBy="users")
	private List<TblRole> roles;

	public TblUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<TblContact> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<TblContact> contacts) {
		this.contacts = contacts;
	}

	public TblContact addContact(TblContact contact) {
		getContacts().add(contact);
		contact.setUser(this);

		return contact;
	}

	public TblContact removeContact(TblContact contact) {
		getContacts().remove(contact);
		contact.setUser(null);

		return contact;
	}

	public List<TblRole> getRoles() {
		return this.roles;
	}

	public void setRoles(List<TblRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "TblUser [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", username=" + username + "]";
	}
	
	

}