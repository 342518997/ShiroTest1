package com.pc.realm;


import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.pc.model.Tuser;
import com.pc.service.TuserService;



/**
 * @author ���  shiro �����֤��Ȩ
 *
 */
public class MyRealm extends AuthorizingRealm{
	@Resource
	private TuserService service;
	//��Ȩ
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// TODO Auto-generated method stub
		//��ȡ��ǰ�û�
		String username=(String)principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		//���ý�ɫ
		authorizationInfo.setRoles(service.RoleName(username));
		//����Ȩ��
		authorizationInfo.setStringPermissions(service.PermissionName(username));
		return authorizationInfo;
	}
	//�����֤
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username=(String)token.getPrincipal();
		Tuser user=service.Login(username);
		if(user==null){
			 throw new UnknownAccountException("�˺��������!");			
		}
		//����ƥ��
		AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()),getName());
		return authenticationInfo;
		
	}

	


}
