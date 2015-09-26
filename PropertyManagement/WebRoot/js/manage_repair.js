$(document).ready(function(){
	
	var flag = 0;

	var house_id;
	var repair_report_id;
	
	console.log("test");

	$.ajax({
		type:'get',
		url:'./servlet/RepairServlet?property=getAllRepair',
		datatype:'json',
		data:{
			flag:flag
		},
		success:function(result){
			var r = jQuery.parseJSON(result);
			if(r.status){
				json = eval(r.message);
				$("#no_repair_report").hide();
				$("#repair_report_table").show();
				$("#repair_report_table").children("tbody").children("tr").remove();
				for(var i = 0; i<json.length; i++){
					generateRepairRepor(
						json[i].house_id,
						json[i].repair_report_id,
						json[i].repair_description,
						json[i].repairer_name,
						json[i].repairer_phone,
						json[i].repair_result,
						json[i].repair_fee,
						json[i].apply_time,
						json[i].repair_time
					);
				}
				$("a[class='house_info_link']").click(function(){
					house_id = $(this).text();
					//$("#inquire_house_modal").modal('show');
				});
				$("a[class='complete_repair_info']").click(function(){
					repair_report_id = getId($(this).parent().parent().attr("id"));
				});
			}else{
				$("#no_repair_report").show();
				$("#repair_report_table").hide();
			}
		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
		}
	});

	//查询房产信息
	$("#inquire_house_modal").on('show.bs.modal', function () {

		$.ajax({
			type:'get',
			url:'./servlet/EstateServlet?property=findHouseByHouse_id',
			datatype:'json',
			data:{
				house_id:house_id
			},
			success:function(result){
				var r = $.parseJSON(result);
				if(r.status){
					$("#house_info").children("tbody").children("tr").remove();
					var json = $.parseJSON(r.message);
					generateHouseInfo(
							json.house_building,
							json.house_unit,
							json.house_floor,
							json.house_num,
							json.house_type,
							json.house_area,
							json.house_remark
					);
					
				}else{

				}
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
			}
		});

		$.ajax({
			type:'get',
			url:'./servlet/EstateServlet?property=findOwnerByHouse_id',
			datatype:'json',
			data:{
				house_id:house_id
			},
			success:function(result){
				var r = $.parseJSON(result);
				if(r.status){
					$("#repair_owner_info_table").children("tbody").children("tr").remove();
					var json = $.parseJSON(r.message);
					for (var i = json.length - 1; i >= 0; i--) {
						generateOwnerInfo(
							json[i].owner_name,
							json[i].owner_phone,
							json[i].owner_email,
							json[i].owner_age,
							getGender(json[i].owner_gender)
						);
					};
				}else{

				}
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
			}
		});

	});

	//初始化维修信息
	$("#complete_repair_modal").on('show.bs.modal', function (){
		var $tr = $("#"+generateId("repair", repair_report_id));
		var repairer_name =  $tr.children(".name").text();
		var repairer_phone =  $tr.children(".phone").text();
		var repair_result =  $tr.children(".result").text();
		var repair_fee =  $tr.children(".fee").text();
		$("#repairer_name").val(repairer_name);
		$("#repairer_phone").val(repairer_phone);
		$("#repair_result").val(repair_result);
		$("#repair_fee").val(repair_fee);

	})

	$("#complete_repair").click(function(){
		var repairer_name = $("#repairer_name").val();
		var repairer_phone = $("#repairer_phone").val();
		var repair_result = $("#repair_result").val();
		var repair_fee = $("#repair_fee").val();
		
		if(repairer_name != "" && repairer_phone != ""){
			
			$.ajax({
				type:'post',
				url:'./servlet/RepairServlet?property=setRepairPlan',
				datatype:'json',
				data:{
					repair_report_id:repair_report_id,
					repairer_name:repairer_name,
					repairer_phone:repairer_phone,
					repair_result:repair_result,
					repair_fee:repair_fee
				},
				success:function(result){
					var r = $.parseJSON(result);
					if(r.status){
						//返回repair_report
						alert(r.message);
					}else{

					}
					
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
				}
			})
			
			if(repair_result != "" && repair_fee != ""){
				
				$.ajax({
					type:'post',
					url:'./servlet/RepairServlet?property=setRepairResult',
					datatype:'json',
					data:{
						repair_report_id:repair_report_id,
						repairer_name:repairer_name,
						repairer_phone:repairer_phone,
						repair_result:repair_result,
						repair_fee:repair_fee
					},
					success:function(result){
						var r = $.parseJSON(result);
						if(r.status){
							alert(r.message);
						}else{
							
						}
					},
					error:function(XMLHttPRequest, textStatus, errorThrown){
						alert(XMLHttPRequest.status);
						alert(XMLHttPRequest.readyState);
						alert(errorThrown);
					}
				});

				
			}
			
			$("#complete_repair_modal").modal('hide');

			
		}


	});

	$("#select_repair_report").click(function(){

		alert("ggg");

		var house_building = $("#house_building").val();
		var house_floor = $("#house_floor").val();
		var house_num = $("#house_num").val();
		var house_unit = $("#house_unit").val();

		$.ajax({
			type:'get',
			url:'./servlet/RepairServlet?property=getRepair',
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
					json = eval(r.message);
					$("#no_repair_report").hide();
					$("#repair_report_table").show();
					$("#repair_report_table").children("tbody").children("tr").remove();
					for(var i = 0; i<json.length; i++){
						generateRepairRepor(
							json[i].house_id,
							json[i].repair_report_id,
							json[i].repair_description,
							json[i].repairer_name,
							json[i].repairer_phone,
							json[i].repair_result,
							json[i].repair_fee,
							json[i].apply_time,
							json[i].repair_time
						);
					}
					$("a[class='house_info_link']").click(function(){
						house_id = getId($(this).parent().parent().attr("id"));
					})
					$("a[class='complete_repair_info']").click(function(){
						repair_report_id = getId($(this).parent().parent().attr("id"));
					})
				}else{
					$("#no_repair_report").show();
					$("#repair_report_table").hide();
				}
				$("#select_repair_modal").modal('hide');
			},
			error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);
				$("#select_repair_modal").modal('hide');
			}
		})

	})

})

function generateRepairRepor(house_id, repair_report_id, repair_description, repairer_name, repairer_phone, repair_result, repair_fee, apply_time, repair_time){

	var $tr = $("<tr></tr>").appendTo($("#repair_report_table").children("tbody"));
	var $td1 = $("<td></td>").text(repair_description);
	var $td2 = $("<td class = 'name'></td>").text(repairer_name);
	var $td3 = $("<td class = 'phone'></td>").text(repairer_phone);
	var $td4 = $("<td class = 'result'></td>").text(repair_result);
	var $td5 = $("<td class = 'fee'></td>").text(repair_fee);
	var $td6 = $("<td></td>").text(apply_time);
	var $td7 = $("<td></td>").text(repair_time);
	var $td8 = $("<td></td>");
	var $td9 = $("<td class = 'house'></td>");
	var $house_info_link = $("<a href='#' class = 'house_info_link' data-toggle='modal' data-target='#inquire_house_modal'></a>").text(house_id);
	var $complete = $("<a href='#' class = 'complete_repair_info' data-toggle='modal' data-target='#complete_repair_modal'>完善维修信息</a>");
	$td8.append($complete);
	$td9.append($house_info_link);
	$tr.attr("id", "repair_"+repair_report_id);
	$tr.append($td9);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$tr.append($td4);
	$tr.append($td5);
	$tr.append($td6);
	$tr.append($td7);
	$tr.append($td8);

}

function generateHouseInfo(house_building, house_unit, house_floor, house_num, house_type, house_acre, house_remark){

	var $tr = $("<tr></tr>").appendTo($("#house_info").children("tbody"));
	var $td1 = $("<td></td>").text(house_building);
	var $td2 = $("<td></td>").text(house_unit);
	var $td3 = $("<td></td>").text(house_floor);
	var $td4 = $("<td></td>").text(house_num);
	var $td5 = $("<td></td>").text(house_type);
	var $td6 = $("<td></td>").text(house_acre);
	var $td7 = $("<td></td>").text(house_remark);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$tr.append($td4);
	$tr.append($td5);
	$tr.append($td6);
	$tr.append($td7);

}

function generateOwnerInfo(owner_name, owner_phone, owner_email, owner_age, owner_gender){
	
	
	var $tr = $("<tr></tr>").appendTo($("#repair_owner_info_table").children("tbody"));
	var $td1 = $("<td></td>").text(owner_name);
	var $td2 = $("<td></td>").text(owner_phone);
	var $td3 = $("<td></td>").text(owner_email);	
	var $td4 = $("<td></td>").text(owner_age);
	var $td5 = $("<td></td>").text(owner_gender);
	
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$tr.append($td4);
	$tr.append($td5);
	
}


function getId(key_word){
	var arr= new Array();
	arr = key_word.split('_');
	alert(arr[1]);
	return arr[1];
}

function generateId(key_word, id){
	return key_word + "_" + id;
}

function getGender(gender){
	if(gender==1){
		return "女";
	}else{
		return "男";
	}
}