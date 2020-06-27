package com.extend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.extend.api.vo.User;
import com.extend.data.UserRepository;

@SpringBootApplication
public class MoneySeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneySeedApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(UserRepository repo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				User u = new User();
				u.setId("yonghw");
				repo.save(u);
				
				u.setId("yonghw2");
				repo.save(u);
			}
		};
	}
}
