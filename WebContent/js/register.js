function register(){
	
	    var password=$("#password").val();
		
		var password2=$("#password2").val();
		
		if(password!=password2){
			
			alert("两次输入的密码不一样！");
			
			return false;
		}
		$.get("register",//后台url
				{"userName":$("#userName").val(),"password":$("#password").val(),"roleId":$("#roleId").val(),"salt":$("#salt").val()},//传送到后台的参数
				function(data){//成功后的回调函数
					
					if(data){
						
						alert("注册成功!");
						
						$(location).attr('href', "login.jsp");
						
					}else{
						
						alert("注册失败!");
						
					}
			
				},
				
				"json");
		
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
