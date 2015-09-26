$(document).ready(function(){
	
	var owner_id;


	$("#add_family_member_form").validate({
		rules:{
			owner_name:"required",
			owner_phone:{
				required:true,
				digits:true
			},
			owner_age:{
				required:true,
				digits:true
			},
			owner_email:{
				required:true,
				email:true
			}
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	})
	
	$("#update_family_member_info_form").validate({
		rules:{
			new_owner_name:"required",
			new_owner_phone:{
				required:true,
				digits:true
			},
			new_owner_age:{
				required:true,
				digits:true
			},
			new_owner_email:{
				required:true,
				email:true
			}
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	})

	$.ajax({
		type:'get',
		url:'./servlet/PersonalInfoServlet?property=findOwner',
		datatype:'json',
		data:{

		},
		success:function(result){
			var r = jQuery.parseJSON(result);
			if(r.status){
				json = eval(r.message);
				for (var i = 0; i < json.length; i++) {
					generateOwnerInfo(
						json[i].owner_id,
						json[i].owner_name,
						json[i].owner_gender,
						json[i].owner_age,
						json[i].owner_phone,
						json[i].owner_email
					);
				};
				$("a[class='update_owner_info']").click(function(){
					var $parent = $(this).parent().parent();
					owner_id = $parent.attr("id");
				});
				$("a[class='delete_owner_info']").click(function(){
					var $parent = $(this).parent().parent();
					owner_id = $parent.attr("id");
				});
			}else{
				//
			}
		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
			alert(XMLHttPRequest.status);
			alert(XMLHttPRequest.readyState);
			alert(errorThrown);
		}
	})
	
	$("#update_family_member_modal").on('show.bs.modal', function () {

		var id = owner_id;

		var owner_name =  $("#"+id).children(".name").text();
		var owner_phone =  $("#"+id).children(".phone").text();
		var owner_email = $("#"+id).children(".email").text();
		var owner_age = $("#"+id).children(".age").text();
		var owner_gender = $("#"+id).children(".gender").text();

		$("#new_owner_name").val(owner_name);
		$("#new_owner_phone").val(owner_phone);
		$("#new_owner_email").val(owner_email);
		$("#new_owner_age").val(owner_age);
		
		alert(generateGenderId(owner_gender));
		
		$("input[name='new_owner_gender'][value="+generateGenderId(owner_gender)+"]").prop("checked",true);
		
	})
	
	

	

	//改
	$("#add_family_member").click(function(){
		if ($("#add_family_member_form").valid()) {

			var owner_name = $("#owner_name").val();
			var owner_age = $("#owner_age").val();
			var owner_phone = $("#owner_phone").val();
			var owner_email = $("#owner_email").val();
			var owner_gender = $("#owner_gender").val();

			$.ajax({
				type:'post',
				url:'./servlet/PersonalInfoServlet?property=addOwner',
				datatype:'json',
				data:{
					owner_name:owner_name,
					owner_age:owner_age,
					owner_phone:owner_phone,
					owner_email:owner_email,
					owner_gender:owner_gender
				},
				success:function(result){
					var r = jQuery.parseJSON(result);
					if(r.status){
						var json = $.parseJSON(r.message);
						generateOwnerInfo(
							json.owner_id,
							json.owner_name,
							json.owner_gender,
							json.owner_age,
							json.owner_phone,
							json.owner_email
						);
					}else{
						//添加失败
					}
					$("#add_family_member_modal").modal('hide');
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
				}
			});

		}
	})
	
	
	$("#update_family_member").click(function(){
		if($("#update_family_member_info_form").valid()){
			
			var id = owner_id;

			var new_owner_name = $("#new_owner_name").val();
			var new_owner_phone = $("#new_owner_phone").val();
			var new_owner_email = $("#new_owner_email").val();
			var new_owner_age = $("#new_owner_age").val();
			var new_owner_gender = $('input:radio:checked').val();
			
			alert(new_owner_gender);

			$.ajax({
				type:'post',
				url:'./servlet/PersonalInfoServlet?property=updateOwner',
				datatype:'json',
				data:{
					owner_id:id,
					owner_age:new_owner_age,
					owner_email:new_owner_email,
					owner_phone:new_owner_phone,
					owner_name:new_owner_name,
					owner_gender:new_owner_gender
				},	
				success:function(result){
					var r = $.parseJSON(result);
					if(r.status){
						
						$("#"+id).children(".name").text(new_owner_name);
						$("#"+id).children(".phone").text(new_owner_phone);
						$("#"+id).children(".email").text(new_owner_email);
						$("#"+id).children(".age").text(new_owner_age);
						$("#"+id).children(".gender").text(getGender(new_owner_gender));
						
					}else{
						//淇敼涓嶆垚鍔�
					}
					$("#update_family_member_modal").modal('hide');
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
					$("#update_family_member_modal").modal('hide');		
				}
			})

		}
	})
	
	$("#delete_family_member").click(function(){
		
		var id = owner_id;
		
		$.ajax({
			type:'get',
			url:'./servlet/PersonalInfoServlet?property=deleteOwner',
			datatype:'json',
			data:{
				owner_id:id
			},
			success:function(result){
				var r = $.parseJSON(result);
				if(r.status){
					$("#"+id).remove();
				}else{
					//鍒犻櫎澶辫触
				}
				$("#delete_family_member_modal").modal('hide');
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
				$("#delete_family_member_modal").modal('hide');
			}
		});
	});

	

})

function generateOwnerInfo(owner_id, owner_name, owner_gender, owner_age, owner_phone, owner_email){

	var $tr = $("<tr></tr>").appendTo($("#owner_family_info_table").children("tbody"));
	var $td1 = $("<td class = 'name'></td>").text(owner_name);
	var $td2 = $("<td class = 'gender'></td>").text(getGender(owner_gender));
	var $td3 = $("<td class = 'age'></td>").text(owner_age);
	var $td4 = $("<td class = 'phone'></td>").text(owner_phone);
	var $td5 = $("<td class = 'email'></td>").text(owner_email);
	var $td6 = $("<td></td");
	var $update = $("<a href='#' data-toggle='modal' class = 'update_owner_info' data-target='#update_family_member_modal'>修改</a>");
	var $remove = $("<a href='#' data-toggle='modal' class = 'delete_owner_info' data-target='#delete_family_member_modal'>删除</a>");
	$tr.attr("id", owner_id);
	$td6.append($update);
	$td6.append($remove);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$tr.append($td4);
	$tr.append($td5);	
	$tr.append($td6);

}

function getGender(gender){
	if(gender==1){
		return "女";
	}else{
		return "男";
	}
}

function generateGenderId(gender){
	if(gender=="男"){
		return "0";
	}else{
		return "1";
	}
}