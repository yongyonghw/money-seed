package com.extend.api.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.MoneyConst;
import com.extend.api.vo.Order;
import com.extend.api.vo.Room;
import com.extend.api.vo.SharedUser;
import com.extend.api.vo.Token;
import com.extend.api.vo.User;
import com.extend.data.OrderRepository;
import com.extend.data.RoomRepository;
import com.extend.data.SharedUserRepository;
import com.extend.data.TokenRepository;
import com.extend.data.UserRepository;

@Service
public class MoneyShareService {
	private final OrderRepository orderRepository;
	private final TokenRepository tokenRepository;
	private final RoomRepository roomRepository;
	private final UserRepository userRepository;
	private final SharedUserRepository sharedUserRepository;
	
	@Autowired
	public MoneyShareService(OrderRepository orderRepository
			, TokenRepository tokenRepository
			, RoomRepository roomRepository
			, UserRepository userRepository
			, SharedUserRepository sharedUserRepository) {
		
		// TODO Auto-generated constructor stub
		this.orderRepository = orderRepository;
		this.tokenRepository = tokenRepository;
		this.roomRepository = roomRepository;
		this.userRepository = userRepository;
		this.sharedUserRepository = sharedUserRepository;
	}
	
	public Token makeToken() {
		Token token = new Token();
		token.setCreateDate(new Date());
		
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 3; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // A-Z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		token.setTokenId(temp.toString());
		tokenRepository.save(token);
		return token;
	}
	
	public void makeOrder(Token token, int money, String room, String user, int personCount) {
		Order o = new Order();
		o.setDate(token.getCreateDate());
		o.setMoney(money);
		o.setOwner(userRepository.findById(user).get());
		o.setPersonCount(personCount);
		o.setToken(token);
		o.setOrderNo(token.getTokenId());
		Random r = new Random();
		int leaveMoney= money;
		int leaveTotal = 0;
		for(int i = 0; i < personCount; i ++) {
			if(i == personCount - 1) {
				o.addSharedMoney(money - leaveTotal);
			}
			else {
				int rm = r.nextInt(leaveMoney);
				leaveMoney -= rm;
				leaveTotal += rm;
				
				o.addSharedMoney(leaveMoney);
			}
		}
		
		orderRepository.save(o);
	}
	
	public Map<String, Object> sharePay(String tokenId, String user) {
		Map<String, Object> resultMap = new HashMap<>();
		Order o = orderRepository.findById(tokenId).get();
		Token t = tokenRepository.findById(tokenId).get();
		
		long nowTime = new Date().getTime();
		
		long cal = nowTime - t.getCreateDate().getTime();
		
		//10분경과시 실패하도록 
		if(cal > 60 * 1000) {
			resultMap.put("resultCode", MoneyConst.CODE.TEN_MINUTES_EXPIRED.name());
			return resultMap;
		}
		
		List<Integer> li = o.getSharedMoney();
		
		int m = li.remove(li.size() - 1);
		
		SharedUser u = new SharedUser();
		u.setId(user);
		u.setMoney(m);
		u.setTokenId(tokenId);
		String key = tokenId + "_" + user;
		u.setKey(key);
		
		//뿌린자는  받을수 없음 
		if(orderRepository.findById(tokenId).get().getOwner().getId().equals(user)) 
		{
			resultMap.put("resultCode", MoneyConst.CODE.NOT_RECIVER.name());
			return resultMap;
		} 
		//이미 받은사람도 또 받을수 없음  
		else if(sharedUserRepository.existsById(key)) {
			resultMap.put("resultCode", MoneyConst.CODE.ALREADY_RECIVED.name());
			return resultMap;
		}
		else {
			sharedUserRepository.save(u);
			resultMap.put("resultCode", MoneyConst.CODE.SUCCESS.name());
			resultMap.put("received_money", m);
			resultMap.put("received_id", user);
		}
		
		return resultMap;
	}
	
	
	public Map<String, Object> getShareResult(String tokenId, String user) {
		Order o = orderRepository.findById(tokenId).get();
		long nowTime = new Date().getTime();
		
		Map<String, Object> resultMap = new HashMap<>();
		if(o == null) {
			resultMap.put("resultCode", MoneyConst.CODE.TOKEN_INVALID.name());
		}
		
		else {
			if(!o.getOwner().getId().equals(user)) {
				resultMap.put("resultCode", MoneyConst.CODE.NOT_OWNER);
			} else {
				long cal = nowTime - o.getDate().getTime();
				if(cal < 3600 * 24 * 1000 * 7) {
					
					resultMap.put("shareTime", o.getDate());
					resultMap.put("shareMoney", o.getMoney());
					resultMap.put("sharedMoneys", sharedUserRepository.findAllByTokenId(tokenId));
					resultMap.put("resultCode", MoneyConst.CODE.SUCCESS.name());
				}
			}
		}
		
		return resultMap;
		
	}
	

}
