package com.pc.test;

import java.util.ArrayList;
import java.util.List;


/**
 * @author asus 仓库类Storage实现缓冲区 
 *
 */
public class Storage {
	// 仓库最大存储量 
	private final int MAX_SIZE=100;
	
	// 仓库存储的载体 
	private List<Object>list=new ArrayList<>();
	
	//生成商品
	public void produce(int num){
		
		synchronized(list){
			// 如果仓库剩余容量不足
			while(list.size() + num > MAX_SIZE){
				   System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:"  
	                        + list.size() + "/t暂时不能执行生产任务!"); 
				   try{
					   // 由于条件不满足，生产阻塞 
					   list.wait();
				   }catch(InterruptedException  e){
					   e.printStackTrace();
					   
				   }
			}
			// 生产条件满足情况下，生产num个产品
			for(int i=0;i<num;i++){
				list.add(new Object());
			}
			
		}
		 System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size()); 
		 list.notifyAll();
		
	}
	// 消费num个产品  
	public void consume(int num){
		
		synchronized(list){
			// 如果仓库存储量不足 
			while(list.size() < num){
				System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:"  
                        + list.size() + "/t暂时不能执行生产任务!");  
				try{
					 // 由于条件不满足，消费阻塞
					list.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			// 消费条件满足情况下，消费num个产品  
			for(int i=0; i<num;i++){
				list.remove(i);
			}
			  System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + list.size());
			  list.notifyAll();
			
		}
		
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public int getMAX_SIZE() {
		return MAX_SIZE;
	}
	
			
	

}
