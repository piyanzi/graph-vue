package com.example.graphvue.dao;

import com.example.graphvue.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findById(int id);

    List<User> findAll();

    User getByUsernameAndPassword(String username,String password);

    @Transactional
    @Modifying
    @Query("update User e set e.username = ?2, e.password = ?3, e.role = ?4 where e.id = ?1")
    void setUser(int id, String username, String password, int role);
}
