package com.pc.controllers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pc.model.Tuser;
import com.pc.service.TuserService;
import com.pc.util.EndecryptUtils;
import com.pc.util.ValidateCode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * @author asus ��¼ ע�� ��Ȩ ������
 *
 */
@Controller
@RequestMapping(name = "/")
public class TuserControllers {
	
	@Resource
	private TuserService service;
	
	//��¼
	@RequestMapping("/login")
	public void Login(Tuser user,String verification, HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String errorMsg = null;
		
		boolean account=false;
		
		JSONObject json = new JSONObject();
		
		HttpSession session=request.getSession();
		
		PrintWriter out =response.getWriter();
		
		//�õ���֤��
		
		String code = (String) request.getSession().getAttribute("validateCode");
		
		//�õ��û��������֤��
		
		String submitCode =(String) WebUtils.getCleanParam(request, "verification");
		
		
		//�ж���֤��
		
		if(code == null || !code.equals(submitCode)){
			
			errorMsg = "��֤�����";
						
		}else if(code.equals(submitCode)){
			
			Subject subject = SecurityUtils.getSubject();	 
			
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
					
			try {
				
				subject.login(token);
				
			} catch (UnknownAccountException e) {
				
				errorMsg = "�û������������";
				
				account=true;
				
			} catch (IncorrectCredentialsException e) {
				
				errorMsg = "�û������������";
				
			} catch (ExcessiveAttemptsException e) {
				
				errorMsg = "��¼ʧ�ܶ�Σ��˻�����10����";

			} catch (AuthenticationException e) {
				
				errorMsg = "��������" + e.getMessage();
				
			}
			
		}	
						
		if (errorMsg != null) {
						
			if(code.equals(submitCode) && !account){
									
				int temp = (int)session.getAttribute("userName");
				
				if(temp > 0){
										
					json.element("row", "�����Ե�¼:"+temp+"��!");
					
				}
				
			}
			
			
			
			json.element("booe", false);
			
			json.element("errorMsg", errorMsg);
						
			out.print(json);
		
			
		}else{
			
			json.element("booe", true);
			
			session.setAttribute("user", user);
					
			out.println(json);
			
		}
				
	}
	
	//ע��
	@RequestMapping("/register")
	public void Stdent(Tuser tuser,HttpServletResponse response) throws IOException{
		
		Tuser user=service.Login(tuser.getUserName());
		
		PrintWriter out=response.getWriter();
		
		JSONObject json = new JSONObject();
		
		if(user!=null){
			
			json.element("boole", false);
			
			json.element("userName", "�˺��Ѿ�ע����!");		
					
		}else{
			
			Tuser tuser2=EndecryptUtils.EncryptUser(tuser);
						
			int row=service.addRegister(tuser2);
			
			if(row>0){
				
				json.element("boole", true);
				
				json.element("userName", "ע��ɹ�!");
				
			}
			
		}
		
		out.println(json);
		
		out.close();
						
	}
	
	//������֤��
	
    @RequestMapping(value = "/validateCode")    
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {        
    	response.setHeader("Cache-Control", "no-cache");
        
    	String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
        
    	request.getSession().setAttribute("validateCode", verifyCode);
        
    	response.setContentType("image/jpeg");
        
    	BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        
    	ImageIO.write(bim, "JPEG", response.getOutputStream());
   
    }
    
    //��ȡ��¼����
    
    @RequestMapping(value = "/Byusername")
    public void Byusername(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	
    	HttpSession session = request.getSession();
    	
    	PrintWriter out = response.getWriter();
    	    	   	
    	Tuser user = (Tuser)session.getAttribute("user");
    	
    	JSONObject json = new JSONObject();
    	
    	if(user != null){
    		
    		json.element("user", user);
    		
    		json.accumulate("booe", true);
    		
    	}else{
    		
    		json.accumulate("booe", false);
    		
    	}
    	
    	out.println(json);
    	    	
    }

}
