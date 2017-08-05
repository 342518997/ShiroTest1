function register(){
	
	    var password=$("#password").val();
		
		var password2=$("#password2").val();
		
		var userName=$("#userName").val();
		
		if(userName==null||userName==''){
			
			alert("请输入账号！");
			
			return false;
		}	 	
		
		if(password==null||password==''){
			
			alert("请输入密码！");
			
			return false;
		}
		
		if(password!=password2){
			
			alert("两次输入的密码不一样！");
			
			return false;
		}
		$.ajax({
			url:"register",
			type:"post",
			data:{"userName":$("#userName").val(),"password":$("#password").val(),"roleId":$("#roleId").val(),"salt":$("#salt").val()},//传送到后台的参数
			success:function(data){
				data=$.parseJSON(data);
				if(data.boole){
					
					alert(data.userName);
					
					$(location).attr('href', "logi.html");
					
				}else{
					
					alert(data.userName);
					
				}
			},
		     error:function(e){
	                alert("错误！！");	                    
	            }		
		});
		
	}

function reloadValidateCode(){
	
	$("#validateCodeImg").attr("src","validateCode?data=" + new Date() + Math.floor(Math.random()*24));

}
function login(){	
	$.ajax({
		url:"login",
		type:"post",
		data:{userName:$("#userName").val(),password:$("#password").val(),verification:$("#verification").val()},
		success:function(data){
			data=$.parseJSON(data);
			if(!data.booe){
				if(data.row!=null){
					alert(data.errorMsg+""+data.row);
				}else{
					alert(data.errorMsg);
				}						
				reloadValidateCode();						
			}else{
				
				window.location.href="success.html";
				
			}
		},
	     error:function(e){
                alert("错误！！");	                    
            }		
	});
}
