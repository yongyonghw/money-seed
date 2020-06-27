package com.extend.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.extend.api.vo.Order;
import com.extend.api.vo.SharedUser;
import com.extend.api.vo.User;

public interface SharedUserRepository extends CrudRepository<SharedUser, String>{
	List<SharedUser> findAllByTokenId(String tokenId);
}
