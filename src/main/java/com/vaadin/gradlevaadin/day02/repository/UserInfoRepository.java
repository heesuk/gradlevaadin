package com.vaadin.gradlevaadin.day02.repository;

import com.vaadin.gradlevaadin.day02.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserInfoRepository extends JpaRepository<UserInfo, String>{
    UserInfo findByUserIdAndPassword(String userId, String password);
    UserInfo findByUserId(String userId);
}
