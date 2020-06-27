package com.extend.api.vo;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Money_Order")
public class Order {

	@Id
	private String orderNo;
	
	@OneToOne(targetEntity=Room.class)
	private Room room;

	@OneToOne(targetEntity=User.class)
	private User owner;
	
	@OneToMany(targetEntity=SharedUser.class)
	private List<SharedUser> sharedUsers = new ArrayList<>();
	
	public void addSharedUsers(SharedUser user) {
		this.sharedUsers.add(user);
	}
	
	private int money;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Integer> sharedMoney = new ArrayList<>();
	
	public void addSharedMoney(int m) {
		this.sharedMoney.add(m);
	}
	
	private int personCount;
	
	private java.util.Date date;
	
	@OneToOne(targetEntity=Token.class)
	private Token token;
}
