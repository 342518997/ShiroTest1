package com.pc.dao;

import java.util.Set;

import org.springframework.stereotype.Repository;
import com.pc.model.Tuser;
/**
 * @author ��� �û���¼ ��Ȩ �ӿ�
 */
@Repository("UsersMapper")
public interface TuserMapper {
	// �����˺Ų�ѯ��¼
	Tuser Login(String username);

	// �����˺Ų�ѯ��ɫ��Ϣ
	Set<String> RoleName(String username);

	// �����˺Ų�ѯȨ����Ϣ
	Set<String> PermissionName(String username);
	
	//ע���˺�
	int addRegister(Tuser tuser);
}
