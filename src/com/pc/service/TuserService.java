package com.pc.service;

import java.util.Set;

import com.pc.model.Tuser;

/**
 * @author asus �û���¼ ��Ȩ service�ӿ�
 *
 */
public interface TuserService {
	// �����˺Ų�ѯ��¼
	Tuser Login(String username);

	// �����˺Ų�ѯ��ɫ��Ϣ
	Set<String> RoleName(String username);

	// �����˺Ų�ѯȨ����Ϣ
	Set<String> PermissionName(String username);
	
	//ע���˺�
	int addRegister(Tuser tuser);

}
