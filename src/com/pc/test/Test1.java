package com.pc.test;

import java.util.Set;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pc.dao.TuserMapper;
import com.pc.model.Tuser;
import com.pc.service.TuserService;

public class Test1 {
	@Test
	public void Test12() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TuserService dao = context.getBean("TuserService", TuserService.class);
		Set<String> list = dao.RoleName("jack");
		for (String name : list) {
			System.out.println(name);
		}
	}

	@Test
	public void Test13() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TuserMapper dao = context.getBean("UsersMapper", TuserMapper.class);
		Tuser tuser = new Tuser();
		for (int i = 0; i < 5; i++) {
			tuser.setUserName("ba" + i);
			tuser.setPassword("5858");
			tuser.setRoleId(2);
			tuser.setSalt("dsfdfsdffdsa");
             dao.addRegister(tuser);
		}

	}

}
