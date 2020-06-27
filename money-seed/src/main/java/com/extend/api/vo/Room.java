package com.extend.api.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Room")
public class Room {
	
	@OneToMany(targetEntity=User.class)
	private List<User> users;
	
	@Id
	private String roomId;
}
