package com.extend.api.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Token implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1097956750901297370L;

	@Id
	@Column(length = 3)
	private String tokenId;
	
	private java.util.Date createDate;
	
}


