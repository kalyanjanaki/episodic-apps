package com.example.users;

import com.example.users.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by trainer20 on 5/17/17.
 */
public interface UserRepository extends CrudRepository<User,Long> {
}
