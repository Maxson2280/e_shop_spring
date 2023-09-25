package com.vyatsu.task14.repositories;

import com.vyatsu.task14.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long>  {

}
