package com.spring.training11.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.training11.entity.TblUser;


@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	public List<TblUser> findAll() {
		String jql = "select o from TblUser o"; 
		TypedQuery<TblUser> query = em.createQuery(jql, TblUser.class);
		return query.getResultList();
	}
	
	public TblUser findById(int id) {
		return em.find(TblUser.class, id);
	}
	
	public TblUser findByUsername(String username) {
		String jql = "select o from TblUser o JOIN FETCH o.roles WHERE o.username=?1"; 
		TypedQuery<TblUser> query = em.createQuery(jql, TblUser.class);
		query.setParameter(1, username);
		return query.getSingleResult();
	}
	
	//@Secured({"ROLE_ADMIN"})
	@Transactional
	public TblUser insert(TblUser user) {
		em.persist(user);
		return user;
	}
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public TblUser update(TblUser user) {
		return em.merge(user);
	}
	
	//@Secured({"ROLE_ADMIN"})
	@Transactional
	public void delete(int id) {
		TblUser user = findById(id);
		em.remove(user);
	}

}
