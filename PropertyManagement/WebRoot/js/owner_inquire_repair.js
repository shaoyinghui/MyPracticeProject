$(document).ready(function(){

	$("#add_repair_report_form").validate({
		rules:{
			repair_description:"required"
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	})

//
//	for(var i = 0; i<5; i++){
//		generateRepairReport(
//			"涓�尯浜屽骇鐢垫鍧忎簡",
//			"2015骞�鏈�4鏃�14:00",
//			"涓囨柟鑸�,
//			"123456789",
//			"鎭㈠姝ｅ父浣跨敤",
//			"22",
//			"2015骞�鏈�4鏃�15:00"
//		);
//	}

	$.ajax({
		type:'get',
		url:'./servlet/RepairServlet?property=getRepair',
		datatype:'json',
		data:{

		},
		success:function(result){
			var r = jQuery.parseJSON(result);
			if(r.status){
				$("#owner_no_notice").hide();
				$("#owner_repair_report").show();
				json = eval(r.message);
				for(var i=0; i<json.length; i++){
					generateRepairReport(
						json[i].repair_description,
						json[i].apply_time,
						json[i].repairer_name,
						json[i].repairer_phone,
						json[i].repair_result,
						json[i].repair_fee,
						json[i].repair_time
					);
				}
			}else{
				$("#owner_no_notice").show();
				$("#owner_repair_report").hide();
			}
		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
			alert(XMLHttPRequest.status);
			alert(XMLHttPRequest.readyState);
			alert(errorThrown);
		}
	})

	$("#add_repair").click(function(){
		if($("#add_repair_report_form").valid()){

			var repair_description = $("#repair_description").val();
			var repair_type = $("#repair_type").val();

			$.ajax({
				type:'post',
				url:'./servlet/RepairServlet?property=addRepair',
				datatype:'json',
				data:{
					repair_description:repair_description,
					repair_type:repair_type
				},
				success:function(result){
					var r = jQuery.parseJSON(result);
					if(r.status){
						alert(r.message);
						//娣诲姞涓�潯璁板綍
						//杩斿洖杩欐潯璁板綍锛�
					}else{
						//淇敼澶辫触
					}
					$("#add_repair_modal").modal('hide');
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
					$("#add_repair_modal").modal('hide');
				}
			})
		}
	})

	$("#select_repair").click(function(){
		alert("ggg");
		var repair_begin_date = $("#repair_begin_date").val();
		var repair_end_date = $("#repair_end_date").val();
		var repair_type = $("#repair_type").val();

		if(repair_begin_date != " " && repair_end_date != " "){
			$.ajax({
				type:'get',
				url:'',
				datatype:'json',
				data:{
					repair_begin_date:repair_begin_date,
					repair_end_date:repair_end_date,
					repair_type:repair_type
				},
				success:function(result){
					if(result['status']){
						$("#owner_no_notice").hide();
						$("#owner_repair_report").show();
						$("#owner_repair_report").children("tbody").children("tr").remove();
						json = eval(result['message']);
						for(var i=0; i<json.length; i++){
							generateRepairReport(
								json[i].repair_description,
								json[i].apply_time,
								json[i].repairer_name,
								json[i].repairer_phone,
								json[i].repair_result,
								json[i].repair_fee,
								json[i].repair_time
							);
						}
					}else{
						$("#owner_no_notice").show();
						$("#owner_repair_report").hide();
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
		}
	})


})

function generateRepairReport(repair_description, apply_time, repairer_name, repairer_phone, repair_result, repair_fee, repair_time){

	var $tr = $("<tr></tr>").appendTo($("#owner_repair_report").children("tbody"));
	var $td1 = $("<td></td>").text(repair_description);
	var $td2 = $("<td></td>").text(apply_time);
	var $td3 = $("<td></td>").text(repairer_name);
	var $td4 = $("<td></td>").text(repairer_phone);
	var $td5 = $("<td></td>").text(repair_result);
	var $td6 = $("<td></td>").text(repair_fee);	
	var $td7 = $("<td></td>").text(repair_time);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$tr.append($td4);
	$tr.append($td5);
	$tr.append($td6);
	$tr.append($td7);

}