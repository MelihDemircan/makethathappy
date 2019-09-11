package com.service.highspeedtrain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.highspeedtrain.data.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}