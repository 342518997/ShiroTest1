package com.pc.util;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author asus ����ƥ��
 *
 */
public class CybHashedCredentialsMatcher extends HashedCredentialsMatcher {

	// ��������Ķ���
	
	private Cache<String, AtomicInteger> passwordRetryCache;
	
	//��ȡ ehcache.xml������� ��ʼ��session
	
	public CybHashedCredentialsMatcher(CacheManager cacheManager) {

		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
		
	}

	// ƥ������
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

		// �õ��˺�

		String userName = (String) token.getPrincipal();

		// �жϵ�¼����

		AtomicInteger retryCount = passwordRetryCache.get(userName);
		
		// ��¼����һ�� retryCount + 1

		if (retryCount == null) {

			retryCount = new AtomicInteger(0);

			passwordRetryCache.put(userName, retryCount);

		}
		
		//��ȡ request����
		
		HttpServletRequest request = ((ServletRequestAttributes)
				RequestContextHolder.getRequestAttributes()).getRequest();
		
		//��ȡ Session����
		
		HttpSession session = request.getSession();
		
		//�����¼����
		
		session.setAttribute("userName", 5-retryCount.get());
		
		// ����5�� ���˺�

		if (retryCount.incrementAndGet() > 5) {

			// �׳��˺������쳣

			throw new ExcessiveAttemptsException();

		}
		
		// ��¼�ɹ� �Ƴ�����

		boolean matches = super.doCredentialsMatch(token, info);

		if (matches) {

			passwordRetryCache.remove(userName);

		}
		
		return matches;
	}
}
