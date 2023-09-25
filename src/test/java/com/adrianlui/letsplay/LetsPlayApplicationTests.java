package com.adrianlui.letsplay;

import com.adrianlui.letsplay.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class LetsPlayApplicationTests {

	@Autowired
	ApplicationContext applicationContext;
	@Test
	void contextLoads() {
		UserController userController = applicationContext.getBean(UserController.class);
		System.out.println(userController.getAllUsers().toString());
	}

}
