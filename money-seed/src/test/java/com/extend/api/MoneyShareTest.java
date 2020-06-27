package com.extend.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.core.MoneyConst;
import com.extend.api.vo.Token;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class MoneyShareTest {
	Token payToken;
	
	@Test
	@Order(1)
	//뿌리기 
	public void postSharedTest() {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(MoneyConst.HEADER_USER, "yonghw");
		headers.set(MoneyConst.HEADER_ROOM, "ABC");
		
		
//		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//		map.add("money", "12345");
//		map.add("personCount", "5");
		
//		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		payToken = restTemplate.postForObject("http://localhost:8080/money/share/12345/5", entity, Token.class);
		System.out.println(payToken);
	}
	
	@Test
	@Order(2)
	//받기 (받은 적이 없는 경우) 
	public void requestShareMoney() throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(MoneyConst.HEADER_USER, "yonghw2");
		headers.set(MoneyConst.HEADER_ROOM, "ABC");
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		String r = restTemplate.postForObject("http://localhost:8080/money/share/pay/"
				+ payToken.getTokenId()
				, entity, String.class);
		
		System.out.println(r);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> m = mapper.readValue(r, Map.class);
		
		assertEquals((String)m.get("resultCode"), MoneyConst.CODE.SUCCESS.name());
	}
	
	@Test
	@Order(3)
	//받기 (받은 적이 있는 경우) 
	public void requestShareMoney2() throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(MoneyConst.HEADER_USER, "yonghw2");
		headers.set(MoneyConst.HEADER_ROOM, "ABC");
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		String r = restTemplate.postForObject("http://localhost:8080/money/share/pay/"
				+ payToken.getTokenId()
				, entity, String.class);
		
		System.out.println(r);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> m = mapper.readValue(r, Map.class);
		
		assertEquals((String)m.get("resultCode"), MoneyConst.CODE.ALREADY_RECIVED.name());
	}
	
	@Test
	@Order(4)
	//받기(뿌린자는 받을수 없음 ) 
	public void requestShareMoney3() throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(MoneyConst.HEADER_USER, "yonghw");
		headers.set(MoneyConst.HEADER_ROOM, "ABC");
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		String r = restTemplate.postForObject("http://localhost:8080/money/share/pay/"
				+ payToken.getTokenId()
				, entity, String.class);
		
		System.out.println(r);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> m = mapper.readValue(r, Map.class);
		
		assertEquals((String)m.get("resultCode"), MoneyConst.CODE.NOT_RECIVER.name());
	}
	
	@Test
	@Order(6)
	//조회 결과 (뿌린사람이 조회한 경우 ) 
	public void requestShareResult() throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(MoneyConst.HEADER_USER, "yonghw");
		headers.set(MoneyConst.HEADER_ROOM, "ABC");
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		String r = restTemplate.postForObject("http://localhost:8080/money/share/result/"
				+ payToken.getTokenId()
				, entity, String.class);
		
		System.out.println(r);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> m = mapper.readValue(r, Map.class);

		assertEquals((String)m.get("resultCode"), MoneyConst.CODE.SUCCESS.name());
		
	}
	
	@Test
	@Order(7)
	//조회 결과 (뿌리지 않은 사람이 조회한 경우 ) 
	public void requestShareResult2() throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(MoneyConst.HEADER_USER, "yonghw2");
		headers.set(MoneyConst.HEADER_ROOM, "ABC");
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		String r = restTemplate.postForObject("http://localhost:8080/money/share/result/"
				+ payToken.getTokenId()
				, entity, String.class);
		System.out.println(r);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> m = mapper.readValue(r, Map.class);
		
		assertEquals((String) m.get("resultCode"), MoneyConst.CODE.NOT_OWNER.name());
		
	}
	
	
	public static void main(String[] args) {
		
		
	}
}
