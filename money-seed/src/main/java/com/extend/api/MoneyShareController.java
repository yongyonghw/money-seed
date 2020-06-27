package com.extend.api;

import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.MoneyConst;
import com.extend.api.service.MoneyShareService;
import com.extend.api.vo.Order;
import com.extend.api.vo.Token;
import com.extend.data.OrderRepository;

@RestController
@RequestMapping("/money")
public class MoneyShareController {
	
	private final MoneyShareService moneyShareService;
	
	@Autowired
	public MoneyShareController(MoneyShareService moneyShareService) {
		this.moneyShareService = moneyShareService;
	}
	
	@RequestMapping("/share/{money}/{personCount}")
	public Token postSharedVo(@RequestHeader(MoneyConst.HEADER_ROOM) String room,
			@RequestHeader(MoneyConst.HEADER_USER) String user
			, @PathVariable int money, @PathVariable int personCount) {
		
		Token token = moneyShareService.makeToken();
		
		moneyShareService.makeOrder(token, money, room, user, personCount);
		
		
		return token;
	}
	@RequestMapping("/share/pay/{tokenId}")
	public Map<String, Object> postGetMoney(@RequestHeader(MoneyConst.HEADER_ROOM) String room,
			@RequestHeader(MoneyConst.HEADER_USER) String user
			, @PathVariable String tokenId) {
		
		return moneyShareService.sharePay(tokenId, user);
	}
	
	@RequestMapping("/share/result/{tokenId}")
	public Map<String, Object> postSharedResult(@RequestHeader(MoneyConst.HEADER_ROOM) String room,
			@RequestHeader(MoneyConst.HEADER_USER) String user
			, @PathVariable String tokenId) {
		
		return moneyShareService.getShareResult(tokenId, user);
	}
	
	
}
