package com.main.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String>{

}
