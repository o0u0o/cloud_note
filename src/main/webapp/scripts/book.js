//定义loadUserBooks函数 根据用户ID显示笔记本列表
function loadUserBooks() {
	//1、获取userId
	var userId = getCookie("userId");
	//alert("userId:"+userId);
	//console.log("userId:"+userId);
	
	//2、判断是否获取到有效userId
	if(userId == null){
		//alert("获取userId失败！");
		//2.1 回到登录页面
		window.location.href="log_in.html";
	}else{
		//3、发送ajax请求
		$.ajax({
			url:path+"/book/loadBooks.do",
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				//pan判断1查询是否成功
				if(result.status == 0){
					//获取笔记本集合
					var books = result.data;
					//清除去原列表信息
					$("#note_ul").empty();
					//遍历
					for(var i = 0;i<books.length;i++){
						// 获取笔记本ID
						var bookId = books[i].cn_notebook_id;	//获取笔记本ID（bookid）
						// 获取笔记本名称
						var bookName = books[i].cn_notebook_name;
						// 创建一个笔记本列表项的li元素
						createBookLi(bookId,bookName);
					}						
				}
				console.log("success!");
			},
			error:function(){
				alter("笔记本加载失败！");
			}			
		});
	}
};

//创建一个笔记本li元素
function createBookLi(bookId,bookName) {
	//拼接字符串
	var sli="";
	sli+='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+='</i>';
	sli+=bookName;
	sli+='</a>';
    sli+='</li>';
    //将sli字符串转换为jQuery对象li元素
    var $li=$(sli);
    //将bookId的值绑与jQuery对象绑定
    $li.data("bookId",bookId);
    //获取book_ul,将li元素添加到笔记本ul列表区
    $("#book_ul").append($li);
};