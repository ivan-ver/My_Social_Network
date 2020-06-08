package com.MSN.Repo;

import com.MSN.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Integer> {
    Optional<User> findById(Integer id);
}
