package com.pc.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.pc.model.Tuser;

/**
 * @author asus shiro���м��ܵĹ������װ
 *
 */
public class EndecryptUtils {
	
	private static String algorithmName = "MD5"; // ���ܷ�ʽ

	private static int hashIterations = 1024; // ���ܴ���

	public static Tuser EncryptUser(Tuser user) {

		// ����ζ���
		SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
		// �����û����Լ�������ɵ�����ƴ�ӳ�������
		String salt = user.getUserName() + secureRandomNumberGenerator.nextBytes().toHex();

		// ��ȡ���ܺ������
		String passWord = new SimpleHash(algorithmName, user.getPassword(),salt,hashIterations).toHex();

		user.setSalt(salt); // ����������

		user.setPassword(passWord); // ���ü��ܹ��������

		return user;
	}
/*	public static void main(String[] args) {
		Tuser uTuser=new Tuser();
		uTuser.setUserName("jack");
		uTuser.setPassword("123");
		Tuser uTuser2=EncryptUser(uTuser);
		System.out.println(uTuser2.getPassword()+" "+uTuser2.getSalt());
	}*/
}
