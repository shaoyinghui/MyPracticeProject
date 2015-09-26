$(document).ready(function(){

	var vehicle_id;
	var park_space_id;

	console.log("text");

	$("#add_vehicle_form").validate({
		rules:{
			vehicle_plate:"required",
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	})

	$("#update_vehicle_form").validate({
		rules:{
			new_vehicle_plate:"required"
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	})

	//娣诲姞杞﹁締锛屽厛鏌ヨ涓氫富浠ョ‘璁�
	$("#submit_plate").click(function(){

		if($("#add_vehicle_form").valid()){

			var house_building = $("#house_building").val();
			var house_floor = $("#house_floor").val();
			var house_unit = $("#house_unit").val();
			var house_num = $("#house_num").val();
			var vehicle_plate = $("#vehicle_plate").val();

			$.ajax({
				type:'get',
				url:'./servlet/EstateServlet?property=findOwner',
				datatype:'json',
				data:{
					house_building:house_building,
					house_unit:house_unit,
					house_floor:house_floor,
					house_num:house_num,
					vehicle_plate:vehicle_plate
				},
				success:function(result){
					var r = jQuery.parseJSON(result);
					$("#add_vehicle_success").hide();
					if (r.status) {
						$("#house_null").hide();
						json = eval(r.message);
						for(var i = 0; i<json.length; i++){
							generateOwnerInfo(
								"add_vehicle_config_table",
								json[i].owner_name, 
								json[i].owner_phone, 
								json[i].owner_email
							);
						} 
						$("#add_vehicle_modal").modal('show');
					}else{
						$("#house_null").show();
					}
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
				}
			})
		}
	})

	//娣诲姞杞﹁締
	$("#submit_vehicle").click(function(){

		var house_building = $("#house_building").val();
		var house_floor = $("#house_floor").val();
		var house_unit = $("#house_unit").val();
		var house_num = $("#house_num").val();
		var vehicle_plate = $("#vehicle_plate").val();

		$.ajax({
			type:'get',
			url:'./servlet/EstateServlet?property=addVehicle',
			datatype:'json',
			data:{
				house_building:house_building,
				house_unit:house_unit,
				house_floor:house_floor,
				house_num:house_num,
				vehicle_plate:vehicle_plate
			},
			success:function(result){
				var r = $.parseJSON(result);
				if (r.status) {
					$("#add_vehicle_success").show();
				}else{
					$("#add_vehicle_success").hide();
				}
				$("#add_vehicle_modal").modal('hide');
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
				$("#add_vehicle_modal").modal('hide');
			}
		})
	})


	//鏌ヨ杞﹁締
	$("#submit_select_vehicle").click(function(){

		var house_building = $("#house_building").val();
		var house_floor = $("#house_floor").val();
		var house_unit = $("#house_unit").val();
		var house_num = $("#house_num").val();

		$.ajax({
			type:'get',
			url:'./servlet/EstateServlet?property=findVehicle',
			datatype:'json',
			data:{
				house_building:house_building,
				house_unit:house_unit,
				house_floor:house_floor,
				house_num:house_num,
			},
			success:function(result){
				var r = jQuery.parseJSON(result);
				if (r.status) {
					$("#show_vehicle_info").show();
					$("#show_vehicle_info").children("tbody").children("tr").remove();
					$("#no_owner").hide();
					json = eval(r.message);
					alert(r.message);
					for(var i = 0; i<json.length; i++){
						generateVehicleInfo(
							json[i].vehicle_id, 
							json[i].vehicle_plate, 
							json[i].vehicle_registe_time
						);
					}
					//涓篴缁戝畾click浜嬩欢
					$("a[class='delete_vehicle']").click(function(){
						var $parent = $(this).parent().parent();
				   		vehicle_id = $parent.attr("id");
				   		alert(vehicle_id);
					})

					//涓篴缁戝畾click浜嬩欢
					$("a[class='update_vehicle']").click(function(){
						var $parent = $(this).parent().parent();
				   		vehicle_id = $parent.attr("id");
				   		alert(vehicle_id);
					})
				}else{
					//娌℃湁杞�
					$("#no_owner").show();
				}
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
			}
		})

	})

	//鍒犻櫎杞﹁締
	$("#delete_vehicle").click(function(){
		$.ajax({
			type:'get',
			url:'./servlet/EstateServlet?property=deleteVehicle',
			datatype:'json',
			data:{
				vehicle_id:vehicle_id
			},
			success:function(result){
				var r = jQuery.parseJSON(result);
				if (r.status) {
					$("#"+vehicle_id).remove();
				}else{
					//娌℃湁杞�
					$("#no_owner").show();
				}
				$("#delete_vehicle_modal").modal('hide');
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
				$("#delete_vehicle_modal").modal('hide');
			}
		})
	})

	//淇敼杞﹁締淇℃伅
	$("#update_vehicle").click(function(){

		if($("#update_vehicle_form").valid()){
			var vehicle_plate = $("#new_vehicle_plate").val();
			$.ajax({
				type:'get',
				url:'./servlet/EstateServlet?property=updateVehicle',
				datatype:'json',
				data:{
					vehicle_id:vehicle_id,
					vehicle_plate:vehicle_plate
				},
				success:function(result){
					var r = jQuery.parseJSON(result);
					if (r.status) {
						$("#"+vehicle_id).children(".plate").text(vehicle_plate);
					}else{

					}
					$("#update_vehicle_modal").modal('hide');
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
					$("#update_vehicle_modal").modal('hide');
				}
			})
		}

	})

	//娣诲姞杞︿綅
	$("#submit_park_space").click(function(){

		var house_building = $("#house_building").val();
		var house_floor = $("#house_floor").val();
		var house_unit = $("#house_unit").val();
		var house_num = $("#house_num").val();
		var park_space_id = $("#park_space_id").val();

		$.ajax({
			type:'get',
			url:'./servlet/EstateServlet?property=findOwner',
			datatype:'json',
			data:{
				house_building:house_building,
				house_unit:house_unit,
				house_floor:house_floor,
				house_num:house_num,
				park_space_id:park_space_id
			},
			success:function(result){
				var r = jQuery.parseJSON(result);
				if(r.status){
					$("#add_park_space_success").hide();
					$("#house_not_exist").hide();
					$("#add_park_space_config_owner_info").children("tbody").children("tr").remove();
					json = eval(r.message);
					for(var i = 0; i<json.length; i++){
						generateOwnerInfo(
							"add_park_space_config_owner_info",
							json[i].owner_name, 
							json[i].owner_phone, 
							json[i].owner_email
						);
					}
					$("#config_park_space").children("span").remove();
					$("#config_park_space").append("<span>" + park_space_id + "</span>");
					$("#add_park_space_modal").modal('show');
				}else{
					$("#house_not_exist").show();
				}
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
			}
		})

	})

	$("#add_park_space").click(function(){

		var house_building = $("#house_building").val();
		var house_floor = $("#house_floor").val();
		var house_unit = $("#house_unit").val();
		var house_num = $("#house_num").val();
		var park_space_id = $("#park_space_id").val();

		$.ajax({
			type:'get',
			url:'./servlet/EstateServlet?property=addPark',
			datatype:'json',
			data:{
				house_building:house_building,
				house_unit:house_unit,
				house_floor:house_floor,
				house_num:house_num,
				park_space_id:park_space_id
			},
			success:function(result){
				var r = jQuery.parseJSON(result);
				if(r.status){
					//娣诲姞鎴愬姛
					//閲嶅畾鍚�
					alert("success");
					$("#add_park_space_success").show();
					$("#park_space_has_been_used").hide(); 
				}else{
					//杞︿綅宸茶浣跨敤
					$("#add_park_space_success").hide();
					$("#park_space_has_been_used").show();
				}
				$("#add_park_space_modal").modal('hide');

			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
					$("#add_park_space_modal").modal('hide');
			}
		})

	})

	//鏌ヨ鐢ㄦ埛杞︿綅
	$("#submit_select_park_space").click(function(){

		var house_building = $("#house_building").val();
		var house_floor = $("#house_floor").val();
		var house_unit = $("#house_unit").val();
		var house_num = $("#house_num").val();

		$.ajax({
			type:'get',
			url:'./servlet/EstateServlet?property=findPark',
			datatype:'json',
			data:{
				house_building:house_building,
				house_unit:house_unit,
				house_floor:house_floor,
				house_num:house_num
			},
			success:function(result){
				var r = jQuery.parseJSON(result);
				if(r.status){
					$("#no_park_space").hide();
					$("#select_park_space_result").show();
					$("#select_park_space_result").children("tbody").children("tr").remove();
					json = eval(r.message);
					for(var i = 0; i<json.length; i++){
						generateParkSpaceInfo(
							json[i].park_space_id,
							json[i].park_position
						);
					}
					//鏌ヨ瀹屽悗涓篴鏍囩缁戝畾click浜嬩欢
					$("a[class='update_park_space']").click(function(){
						var $parent = $(this).parent().parent();
						park_space_id = $parent.attr("id");
						console.log(park_space_id);
					});
					$("a[class='delete_park_space']").click(function(){
						var $parent = $(this).parent().parent();
						park_space_id = $parent.attr("id");
						console.log(park_space_id);
					});
				}else{
					$("#no_park_space").show();
					$("#select_park_space_result").hide();
				}
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
			}
		})

	})

	//淇敼鐢ㄦ埛杞︿綅
	$("#submit_update_park_space").click(function(){

		var new_park_space_id = $("#new_park_space_id").val();

		$.ajax({
			type:'get',
			url:'./servlet/EstateServlet?property=updatePark',
			datatype:'json',
			data:{
				park_space_id:park_space_id,
				newpark_space_id:new_park_space_id
			},
			success:function(result){
				var r = jQuery.parseJSON(result);
				if(r.status){
					//淇敼鎴愬姛
					//鏈夐棶棰�
					$("#park_space_has_been_removed").hide();
					$("#update_park_space_has_been_used").hide();
					$("#"+park_space_id).children(".park_space").text(new_park_space_id);
					$("#"+park_space_id).attr("id", new_park_space_id);
				}else{
					//淇敼涓嶆垚鍔�
					//杞︿綅宸茶鐧昏
					$("#update_park_space_has_been_used").show();
				}				
				$("#update_park_space_modal").modal('hide');
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
				$("#update_park_space_modal").modal('hide');
			}
		})
	})

	//鍒犻櫎鐢ㄦ埛杞︿綅
	$("#submit_delete_park_space").click(function(){
		$.ajax({
			type:'get',
			url:'./servlet/EstateServlet?property=deletePark',
			datatype:'json',
			data:{
				park_space_id:park_space_id
			},
			success:function(result){
				var r = $.parseJSON(result);
				if(r.status){
					$("#"+park_space_id).remove();
					$("#park_space_has_been_removed").show();
					$("#select_park_space_result").hide();
				}else{
					
				}
				$("#delete_park_space_modal").modal('hide');
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
				$("#delete_park_space_modal").modal('hide');
			}
		})
	})


})

function generateOwnerInfo(dom_parent_id, owner_name, owner_phone, owner_email){

	var $tr = $("<tr></tr>").appendTo($("#"+dom_parent_id).children("tbody"));
	var $td1 = $("<td></td>").text(owner_name);
	var $td2 = $("<td></td>").text(owner_phone);
	var $td3 = $("<td></td>").text(owner_email);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);

}

function generateVehicleInfo(vehicle_id, vehicle_plate, vehicle_registe_time){

	var $tr = $("<tr></tr>").appendTo($("#show_vehicle_info").children("tbody"));
	var $td1 = $("<td class = 'plate'></td>").text(vehicle_plate);
	var $td2 = $("<td></td>").text(vehicle_registe_time);
	var $td3 = $("<td></td>");
	var $update = $("<a href='#' data-toggle='modal' class = 'update_vehicle' data-target='#update_vehicle_modal'>淇敼</a>");
	var $remove = $("<a href='#' data-toggle='modal' class = 'delete_vehicle' data-target='#delete_vehicle_modal'>鍒犻櫎</a>");
	$tr.attr("id", vehicle_id);	
	$td3.append($update);
	$td3.append($remove);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);

}

function generateParkSpaceInfo(park_space_id, park_space_position){

	var $tr = $("<tr></tr>").appendTo($("#select_park_space_result").children("tbody"));
	var $td1 = $("<td class = 'park_space'></td>").text(park_space_id);
	var $td2 = $("<td></td>").text(park_space_position);
	var $td3 = $("<td></td>");
	var $update = $("<a href='#' data-toggle='modal' class = 'update_park_space' data-target='#update_park_space_modal'>淇敼</a>");
	var $remove = $("<a href='#' data-toggle='modal' class = 'delete_park_space' data-target='#delete_park_space_modal'>鍒犻櫎</a>");
	$tr.attr("id", park_space_id);
	$td3.append($update);
	$td3.append($remove);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);

}
