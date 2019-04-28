//笔记分享功能-点击分享按钮
function shareNote(){
	//2-获取请求参数
	$li = $(this).parents("li");	//当前对象的所有父元素的li元素
	var noteId = $li.data("noteId");	//获取noteId
	//也可这么写
	//var noteId = $(this).parents("li").data("noteId");
	//alert("分享按钮点击测试:"+noteId);
	
	//3-发送ajax请求
	$.ajax({
		url:path+"/share/add.do",	
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		//回调处理
		success:function(result){
			if(result.status == 0){
				//获取noteTitle数据
				var noteTitle = $li.text();
				//拼接字符串
				var sli = "";
				sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">';
				sli+='</i>'; 
				sli+=noteTitle;
				sli+=' <i class="fa fa-sitemap"></i>'
				sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
				//替换内容(将元素li的<a>标记内容替换)	
				$li.find("a").html(sli);
				//提示用户笔记分享成功
				alert("添加笔记分享成功!");
			}else{
				alert("添加笔记分享失败!");
			}
		},
		error:function(){
			alert("分享笔记失败!");
		}
	});
};

//笔记更新功能-更新笔记信息
function updateNote(){
	console.log("已经触发保存笔记按钮");
	
	//2-获取参数（通过note_ul父元素找到带有checked的a元素）
	var $li = $("#note_ul a.checked").parent();
	//获取笔记Id
	var noteId = $li.data("noteId");
	//获取笔记标题
	var noteTitle = $("#input_note_title").val().trim();
	//获取笔记内容
	//var noteBody = um.getContent(); 
	var noteBody = editor.getContent(); 
	console.log(noteId+" "+noteTitle+" "+noteBody);
	
	//3-发送ajax请求
	$.ajax({
		url:path+"/note/update.do",	//请求路径
		type:"post",		//发送方式为post
		data:{"noteId":noteId,"title":noteTitle,"body":noteBody},	//绑定数据
		dataType:"json",
		success:function(result){
			console.log(result.status);
			if(result.status == 0){
				//拼接字符串
				var str="";
				str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'; 
				str+=noteTitle;
				str+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
				str+='<i class="fa fa-chevron-down"></i></button>';
				
				//将字符串更新到li的元素a中
				$li.find("a").html(str);
				//提示成功
				alert(result.msg);
			}
		},
		error:function(){
			alert("保存信息失败!");
		}
	});
	
};

//删除笔记 
function deleteNote(){
	//alert("删除按钮测试");
	//2-绑定数据
	var noteId = $(this).parents("li").data("noteId");
	//alert("测试数据绑定-noteId:"+noteId);
	
	//3-发送ajax
	$.ajax({
		url:path+"/note/delete.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var $li = $("#note_ul a.checked").parent();
				//loadBookNotes();
				$li.find("a").html("");
				//弹出信息提示
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除笔记失败!");
		}
	});
};

//显示笔记信息
function loadnote(){
	//获取选择效果
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");

	//设置选中效果
	console.log("测试1");
	//2、获取参数
	var noteId = $(this).data("noteId");
	console.log("笔记ID:"+noteId);
			
	//2.发送ajax请求
	$.ajax({
		url:path+"/note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//获取返回的数据
				var note = result.data;
				var id = note.cn_note_id;
				//获取返回的笔记标题
				var title = note.cn_note_title;
				//获取返回的笔记内容
				var body = note.cn_note_body;
				console.log("笔记ID:"+id+"标题:"+title+" 内容:"+body);
						
				//获取该对象，并设置笔记标题（改变placeholder值）
				//$("#input_note_title").attr("placeholder",title);
				$("#input_note_title").val(title);
						
				//设置笔记内容（以前um.setContent(body);）
				editor.setContent(body);
			}else{
				editor.setContent("");
			}
							
		},
		error:function(){
			alert("加载笔记信息失败！");
		}
	});
};

//加载笔记本笔记
function loadBookNotes(){
	//获取选择效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
				
	//1.获取参数 bookId this代表当前点击的那个对象
	var bookId = $(this).data("bookId");
	console.log("bookId:"+bookId);
	//2.判断bookId是否有效
	if(bookId == null){
		alert("bookId不存在!");
	}else{
		$.ajax({
			url:path+"/note/loadnotes.do",
			type:"post",
			data:{"bookId":bookId},
			dataType:"json",
			success:function(result){
				//1、获取笔记集合
				var notes = result.data;
														
				//清除原列表信息
				$("#note_ul").empty();
				$("#note_ul li").remove();
							
				//循环添加li
				for(var i = 0;i<notes.length;i++){
					//获取笔记ID
					var noteId = notes[i].cn_note_id;
					//获取笔记标题
					var nodeTitle = notes[i].cn_note_title;
					
					//创建一个笔记列表的 生成li元素
					createNoteLi(noteId,nodeTitle);
				
					console.log(i+":"+noteId);
					console.log(i+":"+nodeTitle);
								
				}
							
			},
			error:function(){
				alert("加载笔记失败!");
			}
		});
	}
	//alert(bookId);
};

//创建笔记li
function createNoteLi(noteId,noteTitle) {
	//拼接·字符串
	var sli="";
	sli+='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">';
	sli+='</i>'; 
	sli+=noteTitle;
	sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
	sli+='<i class="fa fa-chevron-down"></i></button>';
	sli+='</a>'
	sli+='<div class="note_menu" tabindex="-1">'
	sli+='<dl>'
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli+='</dl>';
	sli+='</div>';
	sli+='</li>';
	
	//转换成JQuery对象
	var $li = $(sli);

	//保存noteId
	$li.data("noteId",noteId);
	//将li追加到ul中
	$("#note_ul").append($li);
};
