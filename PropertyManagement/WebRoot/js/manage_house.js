$(document).ready(function(){

	console.log("gggg");

	var house_id;
	var owner_id;
	

	var house_building;
	var house_unit;
	var house_floor;
	var house_num;

	//妫�獙閫昏緫
	$("#add_house_form").validate({
		rules:{
			account_name:{
				required:true,
				remote:'./servlet/?EstateServlet?property=isOwner_accountRepeat'/*function(){
					var remote_result = false;
					$.ajax({
						url:'./servlet/EstateServlet?property=isOwner_accountRepeat',
						type:'get',
						datatype:'json',
						data:{
							account_name:function(){
								return $("#account_name").val();
							}
						},
						success:function(result){
							var r = $.parseJSON(result)
							remote_result = !(r.status);
						}
					});
					alert(remote_result);
					return remote_result;
				}*/
			},
			account_password:{
				required:true,
				minlength:6,
			},
			owner_name:"required",
			owner_phone:{
				required:true,
				digits:true
			},
			owner_email:{
				email:true,
				required:true
			},
			owner_age:{
				digits:true,
				required:true
			}
		},
		/*message:{
			account_password:{
				required:"璇疯緭鍏ュ瘑鐮�,
				minlength:"瀵嗙爜涓嶈兘灏忎簬6涓瓧绗�
			},
			owner_name:{
				required:"璇疯緭鍏ヤ笟涓绘墜鏈哄彿鐮�
			},
			owner_email:{
				email:"璇疯緭鍏ユ纭殑閭"
			}
		},*/
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	});

	$("#update_house_form").validate({
		rules:{
			house_remark:"required",
			house_acre:"required"
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	});

	$("#update_owner_form").validate({
		rules:{
			new_owner_name:"required",
			new_owner_phone:"required",
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	});

	//鐧昏鎴夸骇
	$("#submit_add_house").click(function(){

		if ($("#add_house_form").valid()) {
			
			var account_name = $("#account_name").val();
			var account_password = $("#account_password").val();
			var owner_name = $("#owner_name").val();
			var owner_phone = $("#owner_phone").val();
			var owner_email = $("#owner_email").val();
			var owner_age = $("#owner_age").val();
			var owner_gender = $("#owner_gender").val();
			var house_building = $("#house_building").val();
			var house_floor = $("#house_floor").val();
			var house_num = $("#house_num").val();
			var house_unit = $("#house_unit").val();

			$.ajax({
				type:'post',
				url:'./servlet/EstateServlet?property=addAccount',
				datatype:'json',
				data:{
					house_building:house_building,
					house_floor:house_floor,
					house_unit:house_unit,
					house_num:house_num,
					account_name:account_name,
					account_password:account_password,
					owner_name:owner_name,
					owner_phone:owner_phone,
					owner_email:owner_email,
					owner_age:owner_age,
					owner_gender:owner_gender
				},
				success:function(result){
					var r = $.parseJSON(result);
					if(r.status){
						$("#success_add_house").show();
					}else{
						//娣诲姞鎴夸骇澶辫触
						$("#success_add_house").hide();
					}
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
				}
			});
		};
	});

	//鏍规嵁鎴垮瓙鏌ヨ鎴夸骇淇℃伅浠ュ強涓氫富鍙婂叾瀹跺涵鎴愬憳淇℃伅
	$("#submit_inquire_house").click(function(){

		house_building = $("#house_building").val();
		house_unit = $("#house_unit").val();
		house_floor = $("#house_floor").val();
		house_num = $("#house_num").val();

		//鑾峰彇鍏蜂綋淇℃伅
		$.ajax({
			type:'post',
			url:'./servlet/EstateServlet?property=findHouse',
			datatype:'json',
			data:{
				house_building:house_building,
				house_unit:house_unit,
				house_floor:house_floor,
				house_num:house_num
			},
			success:function(result){
				alert(result);
				var r = $.parseJSON(result);
				if(r.status){
					alert("success");
					$("#house_info_table").show();
					removeOldInfo("house_info_table");
					var json = $.parseJSON(r.message);
					alert(json.house_id);
					generateHouseInfo(
						json.house_id,
						json.house_type,
						json.house_area,
						json.house_remark
					);
					$("a[class='update_house_info']").click(function(){
						
						var $parent = $(this).parent().parent();
						
						house_id = getId($parent.attr("id"));
						
						alert("update_house_id");
						
					});
				}else{
					//鏌ヤ笉鍒版埧瀛愪俊鎭�
				}
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
			}
		});

		//鑾峰緱涓氫富淇℃伅
		$.ajax({
			type:'post',
			url:'./servlet/EstateServlet?property=findOwner',
			datatype:'json',
			data:{
				house_building:house_building,
				house_unit:house_unit,
				house_floor:house_floor,
				house_num:house_num
			},
			success:function(result){
				var r = $.parseJSON(result);
				if(r.status){
					$("#no_owner").hide();
					$('#owner_info_table').show();
					removeOldInfo("owner_info_table");
 					json = eval(r.message);
					for(var i = 0; i<json.length; i++){
							genrateOwnerInfo(
								json[i].owner_id,
								json[i].owner_name, 
								json[i].owner_phone, 
								json[i].owner_email,
								json[i].owner_age,
								getGender(json[i].owner_gender)
							);
					}
					$("a[class='update_owner_info']").click(function(){
						var $parent = $(this).parent().parent();
						owner_id = getId($parent.attr("id"));
					});
					$("a[class='delete_owner_info']").click(function(){
						var $parent = $(this).parent().parent();
						owner_id = getId($parent.attr("id"));
					});
				}else{
					//娌℃湁涓氫富
					$("#no_owner").show();
					$("#owner_info_table").hide();

				}
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
			}
		});

	});

	//鍒濆鍖栨ā鎬佹鍐呯殑琛ㄥ崟鍐呭
	$('#update_house_modal').on('show.bs.modal', function () {

		var id = generateId("house", house_id);
		var house_type =  $("#"+id).children(".type").text();
		var house_acre =  $("#"+id).children(".acre").text();
		var house_remark = $("#"+id).children(".remark").text();

		$("#house_acre").val(house_acre);
		$("#house_remark").val(house_remark);
		$("#house_type").val(house_type);

	});

	//鍒濆鍖栨ā鎬佹鍐呯殑鍐呭
	$("#update_owner_modal").on('show.bs.modal', function () {

		var id = generateId("owner", owner_id);

		var owner_name =  $("#"+id).children(".name").text();
		var owner_phone =  $("#"+id).children(".phone").text();
		var owner_email = $("#"+id).children(".email").text();
		var owner_age = $("#"+id).children(".age").text();
		var owner_gender = $("#"+id).children(".gender").text();

		$("#new_owner_name").val(owner_name);
		$("#new_owner_phone").val(owner_phone);
		$("#new_owner_email").val(owner_email);
		$("#new_owner_age").val(owner_age);
		
		$("input[name=new_owner_gender][value="+generateGenderId(owner_gender)+"]").prop("checked",true);
		
	})

	//淇敼鎴夸骇淇℃伅
	$("#update_house").click(function(){
		if($("#update_house_form").valid()){
			var house_type = $("#house_type").val();
			var house_acre = $("#house_acre").val();
			var house_remark = $("#house_remark").val();

			$.ajax({
				type:'post',
				url:'./servlet/EstateServlet?property=updateHouse',
				datatype:'json',
				data:{
					house_type:house_type,
					house_remark:house_remark,
					house_area:house_acre,
					house_building:house_building,
					house_floor:house_floor,
					house_unit:house_unit,
					house_num:house_num,
					house_id:house_id
				},
				success:function(result){
					var r = $.parseJSON(result);
					if(r.status){
						$("#"+generateId("house",house_id)).children(".type").text(house_type);
						$("#"+generateId("house",house_id)).children(".acre").text(house_acre);
						$("#"+generateId("house",house_id)).children(".remark").text(house_remark);
					}else{
						//淇敼涓嶆垚鍔�
					}
					$("#update_house_modal").modal('hide');
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
					$("#update_house_modal").modal('hide');					
				}
			})

		}
	})

	//
	$("#update_owner").click(function(){
		if($("#update_owner_form").valid()){

			var new_owner_name = $("#new_owner_name").val();
			var new_owner_phone = $("#new_owner_phone").val();
			var new_owner_email = $("#new_owner_email").val();
			var new_owner_age = $("#new_owner_age").val();
			var new_owner_gender = $('input:radio:checked').val();

			$.ajax({
				type:'get',
				url:'./servlet/PersonalInfoServlet?property=updateOnwer',
				datatype:'json',
				data:{
					owner_id:owner_id,
					owner_age:new_owner_age,
					owner_email:new_owner_email,
					owner_phone:new_owner_phone,
					owner_name:new_owner_name,
					owner_gender:new_owner_gender
				},	
				success:function(result){
					var r = $.parseJSON(result);
					if(r.status){
						
						$("#"+generateId("owner",owner_id)).children(".name").text(new_owner_name);
						$("#"+generateId("owner",owner_id)).children(".phone").text(new_owner_phone);
						$("#"+generateId("owner",owner_id)).children(".email").text(new_owner_email);
						$("#"+generateId("owner",owner_id)).children(".age").text(new_owner_age);
						$("#"+generateId("owner",owner_id)).children(".gender").text(getGender(new_owner_gender));
						
					}else{
						//淇敼涓嶆垚鍔�
					}
					$("#update_owner_modal").modal('hide');
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
					$("#update_owner_modal").modal('hide');		
				}
			})

		}
	})

	$("#delete_owner").click(function(){
		$.ajax({
			type:'get',
			url:'EstateServlet?property=deleteOwner',
			datatype:'json',
			data:{
				owner_id:owner_id
			},
			success:function(result){
				if(result['status']){
					$("#"+generateId('owner', owner_id)).remove();
				}else{
					//鍒犻櫎澶辫触
				}
				$("#delete_owner_modal").modal('hide');
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
				$("#delete_owner_modal").modal('hide');
			}
		});
	});
	

})

function generateHouseInfo(house_id, house_type, house_area, house_remark){

	var $tr = $("<tr></tr>").appendTo($("#house_info_table").children("tbody"));
	var $td1 = $("<td class = 'type'></td>").text(house_type);
	var $td2 = $("<td class = 'acre'></td>").text(house_area);
	var $td3 = $("<td class = 'remark'></td>").text(house_remark);
	var $td4 = $("<td></td>")
	var $update = $("<a href='#' data-toggle='modal' class = 'update_house_info' data-target='#update_house_modal'>淇敼</a>");
	$td4.append($update);
	$tr.attr("id", "house_"+house_id);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$tr.append($td4);

}


function genrateOwnerInfo(owner_id, owner_name, owner_phone, owner_email, owner_age, owner_gender){

	var $tr = $("<tr></tr>").appendTo($("#owner_info_table").children("tbody"));
	var $td1 = $("<td class = 'name'></td>").text(owner_name);
	var $td2 = $("<td class = 'phone'></td>").text(owner_phone);
	var $td3 = $("<td class = 'email'></td>").text(owner_email);
	var $td4 = $("<td class = 'age'></td>").text(owner_age);
	var $td5 = $("<td class = 'gender'></td>").text(owner_gender);
//	var $td6 = $("<td></td");
//	var $update = $("<a href='#' data-toggle='modal' class = 'update_owner_info' data-target='#update_owner_modal'>淇敼</a>");
//	var $remove = $("<a href='#' data-toggle='modal' class = 'delete_owner_info' data-target='#delete_owner_modal'>鍒犻櫎</a>");
	$tr.attr("id", "owner_"+owner_id);
//	$td6.append($update);
//	$td6.append($remove);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$tr.append($td4);
	$tr.append($td5);
//	$tr.append($td6);
}

function generateFamilyMemberInfo(owner_id, owner_name, owner_phone, owner_email, owner_age, owner_gender){

	var $tr = $("<tr></tr>").appendTo($("#family_member_info_table").children("tbody"));
	var $td1 = $("<td></td>").text(owner_name);
	var $td2 = $("<td></td>").text(owner_phone);
	var $td3 = $("<td></td>").text(owner_email);
	var $td4 = $("<td></td>").text(owner_age);
	var $td5 = $("<td></td>").text(owner_gender);
	$tr.attr("id", "owner_"+owner_id);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$tr.append($td4);
	$tr.append($td5);

}

/**
/*鍒嗚В鍑篿d 
/*
/***/
function getId(key_word){
	var arr= new Array();
	arr = key_word.split('_');
	alert(arr[1]);
	return arr[1];
}

function generateId(key_word, id){
	return key_word + "_" + id;
}

function removeOldInfo(dom_id){
	$("#"+dom_id).children("tbody").children("tr").remove();
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