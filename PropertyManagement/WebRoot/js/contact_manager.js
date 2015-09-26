$(document).ready(function(){

	$.ajax({
		type:'get',
		url:'./servlet/PersonalInfoServlet?property=findManager',
		datatype:'json',
		data:{

		},
		success:function(result){
			var r = $.parseJSON(result);
			if(r.status){
				var json = $.parseJSON(r.message);
				for(var i = 0; i<json.length; i++){
					generateManagerInfo(
							json[i].manager_name,
							json[i].manager_phone,
							json[i].manager_tel,
							json[i].manager_email,
							getGender(json[i].manager_gender)
					);
				}
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

function generateManagerInfo(manager_name, manager_phone, manager_teil, manager_email, manager_gender){

	var $tr = $("<tr></tr>").appendTo($("#manager_info_table").children("tbody"));
	var $td1 = $("<td></td>").text(manager_name);
	var $td2 = $("<td></td>").text(manager_teil);
	var $td3 = $("<td></td>").text(manager_phone);
	var $td4 = $("<td></td>").text(manager_email);
	var $td5 = $("<td></td>").text(manager_gender);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$tr.append($td4);
	$tr.append($td5);
}

function getGender(gender){
	if(gender==1){
		return "女";
	}else{
		return "男";
	}
}