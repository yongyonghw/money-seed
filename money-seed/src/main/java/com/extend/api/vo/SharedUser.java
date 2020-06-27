package com.extend.api.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class SharedUser {
	
	@Id
	private String key;
	
	private String id;
	
	private int money;
	
	private String tokenId;
}
