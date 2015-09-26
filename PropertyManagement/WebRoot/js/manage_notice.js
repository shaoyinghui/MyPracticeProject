$(document).ready(function(){

	var flag = 0;

	//记录当前notice_id
	var notice_id;

	$.ajax({
		type:'get',
		url:'./servlet/NoticeServlet?property=getNotice',
		data:{
			flag:flag,
			notice_type:'gjqs'
		},
		datatype:'json',
		success:function(result){
			var r = jQuery.parseJSON(result);
			if(r.status){
				var json = eval(r.message);
				for(var i=0; i<json.length; i++){
					generateNoticeDiv(
						json[i].notice_title,
						json[i].notice_publish_time,
						json[i].notice_content,
						json[i].notice_id
					);
				}
				
				$("a[class='upadte_notice']").click(function(){
			   		var $parent = $(this).parent();
			   		notice_id = $parent.attr("id");
			   		var title = $parent.children("h4").text();
			   		var content = $parent.children("p").text();
			   		$("#notice_title").val(title);
			   		$("#notice_content").val(content);
			   	});

			   	$("a[class='delete_notice']").click(function(){
			   		var $parent = $(this).parent();
			   		notice_id = $parent.attr("id");
			   	});
			}

		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
			alert(XMLHttPRequest.status);
			alert(XMLHttPRequest.readyState);
			alert(errorThrown);
		}
	})

	/*var notice_title = "温馨提醒";
	var notice_publish_time = "2015年7月14日 14:00";
	var notice_content = "由于受“灿鸿”台风和北方冷空气共同影响，近期扬州可能出现大风、大雨的极端天气。届时请各位业主关好自己门窗，拿回放在阳台上的花草及物品，谨防从高层掉落造成危险，车库里面尽量不要摆放贵重物品，防止受潮造成损失。";


	for (var j = 1; j<6; j++){
		var notice_id = j;
		generateNoticeDiv(notice_title, notice_publish_time, notice_content, notice_id);
	}*/

	$("#update_notice_form").validate({
		rules:{
			notice_title:"required",
			notice_content:"required"
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	})

	$("#delete_notice").click(function(){
		$.ajax({
			type:'get',
			url:'./servlet/NoticeServlet?property=deleteNotice',
			data:{
				notice_id:notice_id
			},
			datatype:'json',
			success:function(result){
				var r = jQuery.parseJSON(result);
				if (r.status) {
					//删除对应notice
					$("#"+notice_id).remove();
				}else{
					alert(r.message);
				}
				$('#delete_notice_modal').modal('hide');
			},
			error:function(MLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
				$('#delete_notice_modal').modal('hide');
			}
		})
	});

	$("#update_notice").click(function(){
		//检验是notice_title  && notice_content是否为空
		if($("#update_notice_form").valid()){
			var notice_title = $("#notice_title").val();
			var notice_content = $("#notice_content").val();	
			
			$.ajax({
				type:'post',
				url:'./servlet/NoticeServlet?property=updateNotice',
				dataType:'json',
				data:{
					notice_id:notice_id,
					notice_title:notice_title,
					notice_content:notice_content,
				},
				success:function(result){
					var r = eval(result);
					alert(r.status);
					if (r['status']) {
						$("#"+notice_id).children("h4").text(notice_title);
						$("#"+notice_content).children("p").text(notice_content);

					}else{
						alert(r.message);
					}
					$("#update_notice_modal").modal('hide');
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
					$("#update_notice_modal").modal('hide');
				}
			})
		}else{
			
		}		
	});


	$("#select_notice").click(function(){

		var notice_type = $("#notice_type").val();
		var notice_begin_date = $("#notice_begin_date").val();
		var notice_end_date = $("#notice_end_date").val();
		var notice_key_word = $("notice_key_word").val();

		$.ajax({
			type:'get',
			url:'./servlet/NoticeServlet?property=getNotice',
			datatype:'json',
			data:{
				notice_type:notice_type,
				notice_begin_date:notice_begin_date,
				notice_end_date:notice_end_date,
				notice_key_word:notice_key_word,
				flag:flag
			},
			success:function(result){
				var r = jQuery.parseJSON(result);
				if(r.status){
					$("#select_no_result").hide();
					$(".notice").remove();
					var json = eval(r.message);
					for(var i=0; i<json.length; i++){
						generateNoticeDiv(
							json[i].notice_title,
							json[i].notice_publish_time,
							json[i].notice_content,
							json[i].notice_id
						);
					}
				}else{
					//筛选不成功
					$(".notice").remove();
					$("#select_no_result").show();
				}
				$("#select_notice_modal").modal('hide');
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
				$("#select_notice_modal").modal('hide');
			}
		})

	})

})


/**--------------------**/
/**生成展示notice的Div-**/
/**--------------------**/
function generateNoticeDiv(notice_title, notice_publish_time, notice_content,notice_id){

	var $noticeDiv = $("<div class = 'notice' ></div>").appendTo($("#notice_list_div"));
	var $notice_title = $("<h4 class = 'notice_title'></h4>").html(notice_title);
	var $notice_publish_time = $("<h6></h6>").html(notice_publish_time);
	var $notice_content = $("<p></p>").html(notice_content);
	var $hr = $("<hr/>");
	var $update_notice = $("<a href='#' class = 'upadte_notice' data-toggle='modal' data-target='#update_notice_modal'>修改</a>");
	var $delete_notice = $("<a href='#' class = 'delete_notice' data-toggle='modal' data-target='#delete_notice_modal'>删除</a>");
	$noticeDiv.attr("id",notice_id);
	$noticeDiv.append($notice_title);
	$noticeDiv.append($notice_publish_time);
	$noticeDiv.append($notice_content);
	$noticeDiv.append($update_notice);
	$noticeDiv.append($delete_notice);
	$noticeDiv.append($hr);

}