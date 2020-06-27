package com.extend.data;

import org.springframework.data.repository.CrudRepository;

import com.extend.api.vo.Order;

public interface OrderRepository extends CrudRepository<Order, String>{

}
