package com.pc.test;

import java.util.HashMap;
import java.util.Map;

public class StringTool {
	public static void main(String[] args) {
		String str="aaaffffgdgfgd";
		char[] cs=str.toCharArray();
		 Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for(int i=0;i<cs.length;i++){
			if(null!=map.get(cs[i])){
				map.put(cs[i], map.get(cs[i])+1);
			}else{
				map.put(cs[i], 1);
			}
		}
		 int maxValue = map.get(cs[0]);  
	        char ch = ' ';  
	        for (int j = 0; j < cs.length; j++)  
	            if (maxValue < map.get(cs[j])) {  
	                maxValue = map.get(cs[j]);  
	                ch = cs[j];  
	            }  
	  
	        System.out.println("出现次数最多的字符：" + ch + " 出现次数：" + maxValue);  
	}

}
