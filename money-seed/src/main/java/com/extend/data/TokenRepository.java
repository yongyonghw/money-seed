package com.extend.data;

import org.springframework.data.repository.CrudRepository;

import com.extend.api.vo.Token;

public interface TokenRepository extends CrudRepository<Token, String>{

}
