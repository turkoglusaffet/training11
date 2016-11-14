package com.spring.training11.jpa;

import java.util.List;

import com.spring.training11.entity.TblUser;


public interface UserRepository {

	List<TblUser> findAll();

	TblUser findById(int id);

	TblUser insert(TblUser user);

	TblUser update(TblUser user);

	void delete(int id);

	TblUser findByUsername(String username);

}
