$(document).ready(function(){
	
	$.ajax({
		type:'get',
		url:'./servlet/NoticeServlet?property=getNotice',
		datatype:'json',
		data:{
			flag:0,
			notice_type:"gjqs"
		},
		success:function(result){
			
			var r = jQuery.parseJSON(result);
			alert(r.status);
			if(r.status){
				alert("success");
				$("#owner_no_notice").hide();
				var jsonArray = r.message;
				var json = eval(jsonArray);				
				for(var i=0; i<json.length; i++){
					generateNoticeInfo(
						json[i].notice_title,
						json[i].notice_publish_time,
						json[i].notice_content
					);
				}
			}else{
				alert("fail");
				$(".select_result").remove();
				$("#owner_no_notice").show();
			}
		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
			alert(XMLHttPRequest.status);
			alert(XMLHttPRequest.readyState);
			alert(errorThrown);
		}

	})

	$("#owner_select_notice").click(function(){
		alert("ggg");

		var notice_type = $("#notice_type").val();
		var notice_begin_date = $("#notice_begin_date").val();
		var notice_end_date = $("#notice_end_date").val();

		$.ajax({
			type:'get',
			url:'NoticeServlet?property=getNotice',
			datatype:'json',
			data:{
				notice_type:notice_type,
				notice_begin_date:notice_begin_date,
				notice_end_date:notice_end_date
			},
			success:function(result){
				if(result['status']){
					$("#owner_no_notice").hide();
					$(".select_result").remove();
					json = eval(result['message']);
					for(var i=0; i<json.length; i++){
						generateNoticeInfo(
							json[i].notice_title,
							json[i].notice_publish_time,
							json[i].notice_content
						);
					}
				}else{
					$(".select_result").remove();
					$("#owner_no_notice").show();
				}
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
			}
		}) 

	})

})

	function generateNoticeInfo(notice_title, notice_publish_time, notice_content){
		var $div = $("<div class = 'select_result'></div>").appendTo(".show_notice");
		var $title = $("<h4></h4>").text(notice_title);
		var $time = $("<h6></h6>").text(notice_publish_time);
		var $content = $("<p></p>").text(notice_content);
		var $hr = $("<hr/>");
		$div.append($title);
		$div.append($time);
		$div.append($content);
		$div.append($hr);

	}
