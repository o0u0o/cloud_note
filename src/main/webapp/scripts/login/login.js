//用户注册功能 userRegise()
function userRegise() {
	//测试单击事件的绑定
	//alert("注册");
	//获取参数
	var name = $("#regist_username").val().trim();
	var nick = $("#nickname").val().trim();
	var password = $("#regist_password").val().trim();
	var final_password = $("#final_password").val().trim();
	//console.log(name+","+nick+","+password+","+final_password);
	//alert("密码:"+password+"密码长度:"+password.length);
	//alert(password);
	//检查数据格式
	//检查用户数据
	var ok = true;	//表示参数状态
	if(name==""){
	  ok = false;
	  //通过父子选择器 选择warning_1下的span
	  $("#warning_1 span").html("用户不能为空");
	  //设置为可显示
	  $("#warning_1").show();
	}

	//检测密码：1、非空 2、不能小于6位
	if(password==""){
	  ok = false;
	  $("#warning_2 span").html("密码不能为空");
	  $("#warning_2").show();
	}else if(password.length > 0 && password.length < 6){
	  ok = false;
	  $("#warning_2 span").html("密码不能小于6位");
	  $("#warning_2").show();
	}

	//检测确认密码：1、非空 2、是否与密码一致
	if(password != final_password){
	  ok = false;
	  $("#warning_3 span").html("密码不一致");
	  $("#warning_3").show();
	}

	if(ok){	//数据校验通过
	  $.ajax({
	    url:path+"/user/add.do",
	    type:"post",
	    data:{"name":name,"nick":nick,"password":password},
	    dataType:"json",
	    //处理回调函数
	    success:function(result){
	   //如果状态值为0 表示注册成功
	   if(result.status == 0){
	    alert(result.msg);	//提示注册成功
	    //window.location.href="log_in.html";
	    //返回到登录页面（通过ID选择器，选择执行click()）
	    $("#back").click();
	  }else if(result.status == 1){	//如果状态值为1 表示注册失败
	    $("#warning_1 span").html(result.msg);
	    $("#warning_1").show()	//别忘了这步！！！
	    //alert(result.msg);	//提示用户名被占用
	  }
	},
	error:function(){
	  alert("注册失败!");
	    }
	  });
	}
}

//用户登录功能 userLogin()
function userLogin() {
	//测试绑事件
	//alert("hi");
	//获取参数 账号 并处理前后空格
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	//alert(name+" "+password);
	//清除页面的提示信息
	$("#count_span").html("");
	$("#password_span").html("");
	//格式检测
	var ok = true;
	if(name==""){
		$("#count_span").html("账号不能为空");
		ok = false;
	}
	if(password==""){
		$("#password_span").html("密码不能为空");
		ok = false;
	}
	//如果状态为ok(检查格式通过) 进行发送请求处理
	if(ok){
		//发送ajax请求
		$.ajax({
			url:path+"/user/login.do",
			type:"post",
			data:{"name":name,"password":password},
			dataType:"json",
			success:function(result){
				//进行回调处理
				//result是服务器controller返回的JSON结果
				if(result.status == 0){	//如果状态值为0：登录成功
					//将ajax传回来的信息保存到cookie（先保存用户ID）
					var userId = result.data.cn_user_id;
					addCookie("userId",userId,2);	//保存在cookie内,cookie保存2小时
					window.location.href="edit.html";
				}else if(result.status == 1){
					//状态值为1: 用户名为空
					$("#count_span").html(result.msg);
				}else if(result.status == 2){
					//状态值为2: 密码错误
					$("#password_span").html(result.msg);
				}

			},
			error:function(){
				alert("登录失败!");
			}

		});
	}
};
