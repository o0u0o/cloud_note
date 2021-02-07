//处理登出
function logout() {
	//alert("退出登录");
	//清除cookie
	delCookie("userId");
	//跳转到登录页面
	window.location.href="log_in.html";
}
