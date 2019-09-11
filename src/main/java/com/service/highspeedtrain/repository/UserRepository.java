package com.service.highspeedtrain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.highspeedtrain.data.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}