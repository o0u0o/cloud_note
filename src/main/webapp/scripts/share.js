//点击“更多按钮”，加载结果的下一页
function moreSearchShare() {
	//获取参数
	var keyword = $("#search_note").val().trim();
	page = page + 1;
	alert(keyword);
	//发送ajax请求加载列表
	loadPageShare(keyword, page);
};

//按下“Enter”，加载搜索结果的第一页
function sureShearchShare(event){
	var code = event.keyCode;
	if(code==13){	//enter的输入码为13
		//清空列表，避免
		$("#search_ul li").remove();
		//alert("确认点击");
		//2-获取参数
		var keyword = $(this).val().trim();
		//console.log(keyword);
		page = 1;
		loadPageShare(keyword, page);
	}
};

function loadPageShare(keyword, page){
	//发送ajax请求
	$.ajax({
		url:path+"/share/search.do",
		type:"post",
		data:{"keyword":keyword,"page":page},
		dataType:"json",
		success:function(result){

			if(result.status == 0){
				//1-获取数据
				var shares = result.data;
				//遍历数据
					for(var i=0;i<shares.length;i++){
					//(1)获取记录ShareId（绑定到li当中，后续要用）
					var shareId = shares[i].cn_share_id;
					//(2)获取shareTitle（放到li中让其显示）
					var shareTitle = shares[i].cn_share_title;
					//(3)获取一个li对象
					//此处拼接字符串
					var sli="";
					sli+='<li class="online">';
					sli+='<a>';
					sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">';
					sli+='</i>';
					sli+=shareTitle;
					sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
					sli+='<i class="fa fa-chevron-down"></i></button>';
					sli+='</a>'
					sli+='</li>';
					//转换为对象
					var $li = $(sli);
					//(4)绑定shareId 绑定到li当中
					$li.data("shareId",shareId);
					//(5)将li对象添加到ul当中
					$("#search_ul").append($li);
					//切换显示区
					$("#note-list").hide();  //隐藏
					$("#pc_part_6").show();  //显示
				}
			}
		},
		error:function(){
			alert("搜索失败");
		}
	});
};
