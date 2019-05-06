package com.busyqa.crm.repo;

import com.busyqa.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findAll();;
    User save(User user);

    List<User> findByPositions_TeamName(String teamName);
    List<User> findByPositions_RoleName(String roleName);


    @Modifying
    @Transactional
    @Query("delete from User u where u.username = :username")
    void deleteUsersByUsername(@Param("username") String username);

    @Query("select u from User u")
    List<User> findAllWithPositions();





    @Query("select u from User u where u.username = :username")
    User findUserByUsername(@Param("username") String username);









}
