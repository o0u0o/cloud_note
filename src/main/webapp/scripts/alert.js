//点击确认添加笔记
function sureAddNote() {
	//alert("123");
	//2.获取请求参数
	//2.1	获取笔记本名称(title)
	var title = $("#input_note").val().trim();
	//2.2	获取用户ID
	var userId = getCookie("userId");
	//2.3	获取笔记本ID
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	console.log(title+" "+userId+" "+bookId);
	
	//数据格式检测
	var ok = true;
	if(title==""){	//判断标题是否为空
		ok = false;
		$("#title_span").html("标题不能为空!");
	}
	if(userId==null){	//检查是否失效
		ok = false;
		window.location.href="log_in.html";
	}
	if(ok){
		//3.发送ajax请求
		$.ajax({
			url:path+"/note/add.do",
			type:"post",
			data:{"title":title,"userId":userId,"bookId":bookId},
			dataType:"json",
			success:function(result){//处理回调函数
				//console.log("回调函数");
				if(result.status == 0){
					//获取相关属性值
					var noteId = result.data.cn_note_id;
					var noteTitle = result.data.cn_note_title;
					
					//调用创建NoteLi
					createNoteLi(noteId,noteTitle)
					alert(result.msg);
					console.log("noteId:"+noteId+" noteTitle"+noteTitle);				
//					//拼接字符串
//					var sli="";
//					sli+='<li class="online">';
//					sli+='<a>';
//					sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">';
//					sli+='</i>'; 
//					sli+=title;
//					sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
//					sli+='<i class="fa fa-chevron-down"></i></button>';
//					sli+='</a>'
//					sli+='<div class="note_menu" tabindex="-1">'
//					sli+='<dl>'
//					sli+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
//					sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
//					sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
//					sli+='</dl>';
//					sli+='</div>';
//					sli+='</li>';
//					//将字符串sli转换为jquery对象
//					var $li = $(sli);
//					//绑定数据(保存noteId)
//					$li.data("noteId",noteId);
//					//console.log("笔记本ID:"+bookId);
//					//追加到笔记本列表
//					$("#note_ul").append($li);
					//关闭弹出的窗口
					closeAlertWindow();
				}
			},
			error:function(){
				alert("创建笔记失败!");
			}
		});
	}
};	

//弹出对话框-1:	删除笔记对话框
function alertDeleteWindow(){
	
}

//弹出对话框-2:	新建笔记对话框
function alertAddNoteWindow() {
	//判断是否有笔记本被选中
	var $li = $("#book_ul a.checked").parent();
	if($li.length == 0){
		alert("请选择笔记本");
	}else{
		//弹出创建笔记对话框
		$("#can").load("alert/alert_note.html");
		//显示背景色
		$(".opacity_bg").show();
	}
};

//确定创建笔记本 1.绑定事件
function sureAddBook(){
	alert("测试创建");	
	
	//2.获取请求参数（用户ID、笔记本名称）
	//2.1-获取用户ID
	var userId = getCookie("userId");
	//2.2-获取笔记本名称（input_notebook）
	var bookName = $("#input_notebook").val().trim();
	//var bookId = $li.data("bookId");
	alert(userId+" "+bookName+" ");
	
	//3.发送ajax请求
	$.ajax({
		url:path+"/book/add.do",
		type:"post",
		data:{"userId":userId,"bookName":bookName},
		dataType:"json",
		success:function(result){
			//console.log(result.status);
			if(result.status == 0){
				//var bookId = status.
				//拼接字符串
				var sli="";
				sli+='<li class="online">';
				sli+='<a>';
				sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
				sli+='</i>';
				sli+=bookName;
				sli+='</a>';
			    sli+='</li>';
			    var $li  = $(sli);
			    
				//追加列表信息
			    $("#book_ul").append($li);
			    
				//关闭窗口
				closeAlertWindow();
				
				//清除原列表信息
				$("#book_ul").empty();
				
				//加载笔记本
				loadUserBooks();
			}
		},
		error:function(){
			alert("创建笔记本失败！");
		}
	});
};

//弹出新建笔记本的对话框
function alertAddBookWindow() {
	//1.把html放在div（#can）下，弹出新建笔记本对话框
	$("#can").load("alert/alert_notebook.html")
	
	//2.把背景色显示出来（半透明灰色部分）
	//通过class获取他(opacity_bg是css的一个属性)并让它显示
	$(".opacity_bg").show();
	
};

//关闭对话框（对所有对话框有效）
function closeAlertWindow(){
	//1.清空已经被加载进去的html的div，就是can
	$("#can").html("");	//清空（调用html，给个空字符串）
	//2.隐藏背景色（通过类选择器，调用hide()方法）
	$(".opacity_bg").hide();
};