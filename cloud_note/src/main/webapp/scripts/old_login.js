/* scripts/login.js 编码为 utf-8  */

$(function(){
	//登录功能
	//console.log('Hello World!');
	$('#login').click(loginAction);
	$('#count').blur(checkName);
	$('#password').blur(checkPassword);
	
	//注册功能
	$('#regist_button').click(registAction);
	$('#regist_username').blur(checkRegistName);
	$('#regist_password').blur(checkRegistPassword);
	$('#final_password').blur(checkConfirm);
});

function checkConfirm(){
	var pwd2 = $('#final_password').val();
	var pwd = $('#regist_password').val();
	//pwd 如果是空值表示 false, 非空则是true
	if(pwd && pwd==pwd2){
		$('#final_password').next().hide();
		return true;
	}
	$('#final_password').next().show()
		.find('span').html('确认密码不一致');
	return false;
}

function checkRegistPassword(){
	var pwd = $('#regist_password').val().trim();
	var rule = /^\w{4,10}$/;
	if(rule.test(pwd)){
		$('#regist_password').next().hide();
		return true;
	}
	$('#regist_password').next().show()
		.find('span').html('4~10个字符');
	return false;
}

function checkRegistName(){
	var name = $('#regist_username').val().trim();
	var rule = /^\w{4,10}$/;
	if(rule.test(name)){
		$('#regist_username').next().hide();
		return true;
	}
	$('#regist_username').next().show()
	  .find('span').html('4~10字符');
	return false;
}

/**
 * 
 */
function registAction(){
	console.log('registAction');
	//检验界面参数
	var n = checkRegistName() +
		checkRegistPassword() +
		checkConfirm();
	if(n!=3){
		return ;
	}
	//获取界面中表单数据
	var name = $('#regist_username').val().trim();
	var nick = $('#nickname').val();
	var password = $('#regist_password').val();
	var confirm = $('#final_password').val();
	//发起AJAX请求
	var url = 'user/regist.do';
	var data = {name:name, 
			nick:nick, 
			password:password, 
			confirm:confirm};
	//console.log(data);
	// $.post 是 $.ajax的简化版
	$.post(url, data, function(result){
		console.log(result);
		if(result.state==0){
			//退回登录界面
			$('#back').click();
			var name = result.data.name;
			$('#count').val(name);
			$('#password').focus();
			//清空表单
			$('#regist_username').val('');
			$('#nickname').val('');
			$('#regist_password').val('');
			$('#final_password').val('');
			
		}else if(result.state==4){
			$('#regist_username').next().show()
			  .find('span').html(result.message);
		}else if(result.state==3){
			$('#regist_password').next().show()
			  .find('span').html(result.message);
		}else{
			alert(result.message);
		}
	});
	//得到响应以后, 更新界面
}

function checkName(){
	var name = $('#count').val();
	var rule = /^\w{4,10}$/;
	if(! rule.test(name)){
		$('#count').next().html('4~10个字符');
		return false;
	}
	$('#count').next().empty();
	return true;
}

function checkPassword(){
	var password = $('#password').val();
	var rule = /^\w{4,10}$/;
	if(! rule.test(password)){
		$('#password').next().html('4~10个字符');
		return false;
	}
	$('#password').next().empty();
	return true;
}


function loginAction(){
	//console.log("loginAction");
	//获取用户输入的用户名和密码
	var name = $('#count').val();
	var password = $('#password').val();
	
	//检查 用户名和密码输入框
	var n=checkName()+checkPassword();
	if(n!=2){
		return;
	}
	
	//data 对象中的属性名要与服务器控制器的参数
	// 名一致! login(name, password)
	var data = {"name":name, 
				"password":password};
	$.ajax({
		url:'user/login.do',
		data:data,
		type:'post',
		dataType:'json',
		success: function(result){
			console.log(result);
			if(result.state==0){
				//登录成功!
				var user = result.data;
				console.log(user);
				//登录成功以后将userId保存到cookie中
				addCookie("userId", user.id);
				//跳转到 edit.html
				location.href='edit.html';
			}else{
				var msg = result.message;
				if(result.state==2){
					$('#count').next().html(msg);
				}else if(result.state==3){
					$('#password').next().html(msg);
				}else{
					alert(msg);
				}
			}
		},
		error: function(e){
			alert("通信失败!");
		}
	});
}








