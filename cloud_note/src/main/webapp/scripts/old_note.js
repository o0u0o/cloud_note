// scripts/note.js 编码一定是 utf-8

var SUCCESS = 0;
var ERROR = 1;

$(function(){
	
	//在document对象中存翻页页号状态
	$(document).data('page',0);
	
	//加载第一页数据
	loadPagedNotebooks();
	
	//点击more时候加载下一页数据
	$('#notebook-list').on(
			'click','.more', loadPagedNotebooks);
	
	//网页加载以后, 立即读取笔记本列表
	//loadNotebooks();
	
	//on() 方法绑定事件可以区别事件源
	//click() 方法绑定事件, 无法区别事件源
	//绑定笔记本列表区域的点击事件
	$('#notebook-list').on(
			'click','.notebook', loadNotes);
	
	
	//监听笔记列表中的笔记点击事件,在点击时候加载显示笔记信息
	$('#note-list').on(
			'click','.note', loadNote);
	
	$('#note-list').on('click', '#add_note', showAddNoteDialog);
	
	//监听新建笔记对话框中的创建笔记按钮
	$('#can').on('click','.create-note',addNote);

	//监听对话框中的关闭和取消按钮
	//其中 '.close,.cancel' 是组选择器器, 表示
	//选择 .close 或 .cancel 按钮
	$('#can').on('click','.close,.cancel',closeDialog)
	
	$('#add_notebook').click(demo);
	
	//绑定点击保存笔记事件
	$('#save_note').on('click', updateNote);
	
	
	//绑定笔记子菜单的触发事件
	$('#note-list').on('click', 
			'.btn-note-menu', showNoteMenu);
	
	//监听整体的文档区域, 任何位置点击都要关闭笔记子菜单
	$(document).click(hideNoteMenu);
	
	
	//监听笔记子菜单中移动按钮的点击
	$('#note-list').on('click', '.btn_move', showMoveNoteDialog);

	//监听移动笔记对话框中的确定按钮
	$('#can').on('click', '.move-note', moveNote);

	//监听笔记子菜单中删除按钮的点击
	$('#note-list').on('click', '.btn_delete', showDeleteNoteDialog);
	
	//监听删除笔记对话框中的确定按钮
	$('#can').on('click', '.delete-note', deleteNote);
	
	//监听回收站按钮被点击
	$('#trash_button').click(showTrashBin);
	
	//恢复笔记到笔记本按钮事件监听
	$('#trash-bin').on(
		'click', '.btn_replay', showReplayDialog);

	$('#can').on('click', '.btn-replay', replayNote);

	startHeartbeat();
	
});
 
function loadPagedNotebooks(){
	var page = $(document).data('page');
	var userId = getCookie('userId');
	//从服务器拉去数据
	var url = 'notebook/page.do';
	var data = {userId: userId, page:page};
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			var notebooks = result.data;
			showPagedNotebooks(notebooks, page);
			$(document).data('page', page+1);
		}else{
			alert(result.message);
		}
	});
}

function showPagedNotebooks(notebooks, page){
	var ul = $('#notebook-list ul');
	if(page==0){//第一页时候清空 ul中的li
		ul.empty();
	}else{//不是第一页, 只删除.more元素
		ul.find('.more').remove();
	}
	for(var i=0; i<notebooks.length; i++){
		var notebook=notebooks[i];
		var li = notebookTemplate.replace(
				'[name]', notebook.name);
		li = $(li);
		li.data('notebookId', notebook.id);
		ul.append(li);
	}
	if(notebooks.length!=0){
		ul.append(moreTemplate);
	}
}
var moreTemplate = 
	'<li class="online more">'+
	'<a><i class="fa fa-plus" title="online" '+
	'rel="tooltip-bottom"></i> 加载更多...</a>'+
	'</li>';

function startHeartbeat(){
	var url = "user/heartbeat.do";
	setInterval(function(){
		$.getJSON(url, function(result){
			//console.log(result.data);
		});
	}, 5000);
}

function replayNote(){
	var li = $(document).data('replayItem');
	var id = li.data('noteId');
	var url = 'note/replay.do';
	var nid = $('#replaySelect').val();
	var data = {noteId: id, notebookId:nid};
	$.post(url, data, function(result){
		if(result.state==SUCCESS){
			closeDialog();
			li.slideUp(200, function(){$(this).remove()});
		}else{
			alert(result.message);
		}
	});
}

/** 显示恢复笔记对话框 */
function showReplayDialog(){
	var li = $(this).parent().parent()
	var id = li.data('noteId');
	
	$(document).data('replayItem', li);
	
	if(id){
		$('#can').load('alert/alert_replay.html', loadReplayOptions);
		$('.opacity_bg').show();
		return;
	}
	alert('必须选择笔记!');
}

function loadReplayOptions(){
	var url = 'notebook/list.do';
	var data={userId:getCookie('userId')};
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			var notebooks = result.data;
			//清楚全部的笔记本下拉列表选项
			//添加新的笔记本列表选项
			$('#replaySelect').empty();
			var id=$(document).data('notebookId');
			for(var i=0; i<notebooks.length; i++){
				var notebook = notebooks[i];
				var opt=$('<option></option>')
					.val(notebook.id)
					.html(notebook.name);
				//默认选定当时笔记的笔记本ID
				if(notebook.id==id){
					opt.attr('selected','selected');
				}
				$('#replaySelect').append(opt);
			}
		}else{
			alert(result.message);
		}
	});

}

/** 监听回收站按钮被点击 */
function showTrashBin(){
	$('#trash-bin').show() ;
	$('#note-list').hide() ;
	loadTrashBin();
}

/** 加载回收站中的笔记列表 */
function loadTrashBin(){
	var url = 'note/trash.do';
	var data = {userId: getCookie('userId')};
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			showNotesInTrashBin(result.data);
		}else{
			alert(result.message);
		}
	});
}

function showNotesInTrashBin(notes){
	var ul = $('#trash-bin ul');
	ul.empty();
	for(var i=0; i<notes.length; i++){
		var note = notes[i];
		var li = trashBinItem.replace('[title]', note.title);
		li = $(li);
		li.data('noteId', note.id);
		ul.append(li);
	}
}

var trashBinItem = 
	'<li class="disable">'+
		'<a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+
		' [title]'+
		'<button type="button" class="btn btn-default btn-xs btn_position btn_delete">'+
			'<i class="fa fa-times"></i>'+
		'</button>'+
		'<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay">'+
			'<i class="fa fa-reply"></i>'+
		'</button></a>'+
	'</li>';

/** 删除笔记功能 */
function deleteNote(){
	var url = 'note/delete.do';
	var id = $(document).data('note').id;
	var data = {noteId:id};
	$.post(url, data, function(result){
		if(result.state==SUCCESS){
			//删除成功, 在当前笔记列表中删除笔记
			//将笔记列表中的第一个设置为当前笔记, 否则清空边编辑区域
			var li = $('#note-list .checked').parent();
			var lis = li.siblings();
			if(lis.size()>0){
				lis.eq(0).click();
			}else{
				$('#input_note_title').val("");
				um.setContent("");
			}
			li.remove();
			closeDialog();//关闭对话框!
		}else{
			alert(result.message);
		}
	});

}

/** 打开删除笔记对话框 */
function showDeleteNoteDialog(){
	var id = $(document).data('note').id;
	if(id){
		$('#can').load('alert/alert_delete_note.html', loadNotebookOptions);
		$('.opacity_bg').show();
		return;
	}
	alert('必须选择笔记!');
}


/** 移动笔记事件处理方法 */
function moveNote(){
	
	var url = 'note/move.do';
	var id = $(document).data('note').id;
	var bookId=$('#moveSelect').val();
	//笔记本ID没有变化, 就不移动了!
	if(bookId==$(document).data('notebookId')){
		return;
	}
	var data = {noteId:id, notebookId:bookId};
	$.post(url, data, function(result){
		if(result.state==SUCCESS){
			//移动成功, 在当前笔记列表中删除移动的笔记
			//将笔记列表中的第一个设置为当前笔记, 否则清空边编辑区域
			var li = $('#note-list .checked').parent();
			var lis = li.siblings();
			if(lis.size()>0){
				lis.eq(0).click();
			}else{
				$('#input_note_title').val("");
				um.setContent("");
			}
			li.remove();
			closeDialog();//关闭对话框!
		}else{
			alert(result.message);
		}
	});
}

/** 显示移动笔记对话框 */
function showMoveNoteDialog(){
	var id = $(document).data('note').id;
	if(id){
		$('#can').load('alert/alert_move.html', loadNotebookOptions);
		$('.opacity_bg').show();
		return;
	}
	alert('必须选择笔记!');
} 
/** 加载移动笔记对话框中的笔记本列表 */
function loadNotebookOptions(){
	var url = 'notebook/list.do';
	var data={userId:getCookie('userId')};
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			var notebooks = result.data;
			//清楚全部的笔记本下拉列表选项
			//添加新的笔记本列表选项
			$('#moveSelect').empty();
			var id=$(document).data('notebookId');
			for(var i=0; i<notebooks.length; i++){
				var notebook = notebooks[i];
				var opt=$('<option></option>')
					.val(notebook.id)
					.html(notebook.name);
				//默认选定当时笔记的笔记本ID
				if(notebook.id==id){
					opt.attr('selected','selected');
				}
				$('#moveSelect').append(opt);
			}
		}else{
			alert(result.message);
		}
	});
}

/** 关闭笔记子菜单事件处理方法 */
function hideNoteMenu(){
	$('.note_menu').hide();
}

/** 显示笔记子菜单处理方法 */
function showNoteMenu(){
	//找到菜单对象, 调用show() 方法
	var btn = $(this);
	//如果当前是被选定的 笔记项目 就弹出子菜单
	btn.parent('.checked').next().toggle();
	//btn.parent('.checked') 获取当前按钮的父元素
	//这个元素必须符合选择器'.checked', 如果不
	//符合就返回空的JQuery元素. 	
	return false;//阻止点击事件的继续传播!
}

function updateNote(){
	var url = 'note/update.do';
	var note = $(document).data('note');
	var data = {noteId:note.id};
	var modified = false;
	var title = $('#input_note_title').val();
	if(title && title!=note.title){
		data.title = title;
		modified = true;
	}
	var body = um.getContent();
	if(body && body != note.body ){
		data.body = body;
		modified = true;
	}
	if(modified){
		
		$.post(url, data, function(result){

			if(result.state == 0){
				//console.log("Success!");
				//内存中的 note 改成新的数据
				note.title = title;
				note.body = body;
				var l = $('#note-list .checked').parent();
				$('#note-list .checked').remove()
				var li = noteTemplate.replace( '[title]', title);
				var a = $(li).find('a');
				a.addClass('checked');
				l.prepend(a);
			}else{
				alert(result.mesage);
			}
		});
	}
}


function loadNote(){
	//获取当前点击的 li 元素
	var li = $(this);
	//获取在显示时候绑定到li中的笔记ID值
	var id = li.data('noteId');
	
	//设置选中高亮效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	
	var url = 'note/load.do';
	var data= {noteId: id };
	
	$.getJSON(url, data, function(result){
		//console.log(result);
		if(result.state==SUCCESS){
			var note = result.data;
			showNote(note);
		}else{
			alert(result.message);
		}
	});
}

function showNote(note){
	//显示笔记标题
	$('#input_note_title').val(note.title);
	//显示笔记内容
	um.setContent(note.body);
	
	//绑定笔记信息, 用于保存操作
	$(document).data('note', note);

}


function closeDialog(){
	$('.opacity_bg').hide();
	$('#can').empty();
}

function addNote(){
	var url = 'note/add.do';
	var notebookId=$(document).data('notebookId');
	var title = $('#input_note').val();
 
	var data = {userId:getCookie('userId'),
		notebookId:notebookId,
		title:title};
	//console.log(data);
	
	$.post(url, data, function(result){
		if(result.state==SUCCESS){
			var note=result.data;
			//console.log(note);
			showNote(note);
			//找到显示笔记列表的ul对象
			var ul = $('#note-list ul');
			//创建新新的笔记列表项目 li 
			var li = noteTemplate.replace(
					'[title]', note.title);
			li = $(li);
			
			//绑定笔记ID到LI
			li.data('noteId', note.id)

			//设置选定效果
			ul.find('a').removeClass('checked');
			li.find('a').addClass('checked');
			//插入到笔记列表的第一个位置
			ul.prepend(li);
			//关闭添加对话框
			closeDialog();   
		}else{
			alert(result.message);
		}
	});

}

function showAddNoteDialog(){
	var id = $(document).data('notebookId');
	if(id){
		$('#can').load('alert/alert_note.html', function(){
			$('#input_note').focus();
		});
		$('.opacity_bg').show();
		return;
	}
	alert('必须选择笔记本!');
}

/** 笔记本项目点击事件处理方法, 加载全部笔记 */
function loadNotes(){
	//在点击笔记本列表中的笔记时候, 为了
	//显示笔记列表: 关闭回收站 打开笔记列表
	$('#trash-bin').hide();
	$('#note-list').show();
	
	var li = $(this);//当前被点击的对象li
	
	//在被点击的笔记本li增加选定效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	
	var url = 'note/list.do';
	var data={notebookId:li.data('notebookId')};
	console.log(data);
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			var notes = result.data;
			showNotes(notes);
		}else{
			alert(result.message);
		}
	});
	
	//绑定笔记本ID， 用于添加笔记功能
	$(document).data('notebookId', li.data('notebookId'));

}
/** 将笔记列表信息显示到屏幕上 */
function showNotes(notes){
	console.log(notes); 
	//将每个笔记对象显示到屏幕的ul区域
	var ul = $('#note-list ul');
	ul.empty();
	for(var i=0; i<notes.length; i++){
		var note = notes[i];
		var li = noteTemplate.replace(
				'[title]', note.title);
		li = $(li);
		
		//将笔记ID绑定到li, 用在点击笔记时候显示笔记详细信息
		li.data('noteId', note.id);
		
		ul.append(li);
	}
}

var noteTemplate = '<li class="online note">'+
	'<a>'+
	'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> [title]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down btn-note-menu"><i class="fa fa-chevron-down"></i></button>'+
	'</a>'+
	'<div class="note_menu" tabindex="-1">'+
	'<dl>'+
		'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
		'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
		'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
	'</dl>'+
	'</div>'+
	'</li>';

/** 加载笔记本列表数据 */
function loadNotebooks(){
	//利用ajax从服务器获取(get)数据, 使用getJSON方法
	var url = 'notebook/list.do';
	var data = {userId:getCookie('userId'),
			name:'demo'};
	$.getJSON(url, data, function(result){
		console.log(result);
		if(result.state==SUCCESS){
			var notebooks = result.data;
			//在showNotebooks方法中将全部的
			//笔记本数据 notebooks 显示到 
			// notebook-list 区域
			showNotebooks(notebooks);
		}else{
			alert(result.message);
		}
	});
}
/** 在notebook-list区域中显示笔记本列表 */
function showNotebooks(notebooks){
	//找显示笔记本列表的区域的ul元素
	//遍历notebooks数组, 将为每个对象创建一个li
	//元素, 添加到 ul元素中.
	var ul = $('#notebook-list ul');
	ul.empty();
	for(var i=0; i<notebooks.length; i++){
		var notebook = notebooks[i];
		var li = notebookTemplate.replace(
				'[name]', notebook.name);
		li = $(li);
		//将 notebook.id 绑定到 li
		$(li).data('notebookId', notebook.id);
		
		ul.append(li);
	}
	

}
var notebookTemplate = 
	'<li class="online notebook">'+
	'<a><i class="fa fa-book" title="online" '+
	'rel="tooltip-bottom"></i> [name]</a>'+
	'</li>';

function demo(){
	var ary = [1,8,3,9,10,6,8];
	sort(ary);
	console.log(ary);
}

function sort(ary){
	for(var i=0; i<ary.length-1; i++){
		for(var j=0; j<ary.length-i-1; j++){
			if(ary[j]>ary[j+1]){
				var t = ary[j];
				ary[j]=ary[j+1];
				ary[j+1]=t;
			}
		}
	}
}
