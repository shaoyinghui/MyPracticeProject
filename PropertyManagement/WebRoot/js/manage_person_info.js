$(document).ready(function(){

	$.ajax({
		type:'get',
		url:'./servlet/PersonalInfoServlet?property=getInfo',
		datatype:'json',
		data:{

		},
		success:function(result){
			var r = $.parseJSON(result);
			if(r.status){
				var json = $.parseJSON(r.message);
				alert(json);
				$("#manager_name").val(json.manager_name);
				$("#manager_phone").val(json.manager_phone);
				$("#manager_teil").val(json.manager_tel);
				$("#manager_email").val(json.manager_email);
				$("#manager_age").val(json.manager_age);
			}else{

			}
		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);		
		}
	})

	

})