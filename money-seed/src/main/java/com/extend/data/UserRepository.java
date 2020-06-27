package com.extend.data;

import org.springframework.data.repository.CrudRepository;

import com.extend.api.vo.Order;
import com.extend.api.vo.User;

public interface UserRepository extends CrudRepository<User, String>{

}
