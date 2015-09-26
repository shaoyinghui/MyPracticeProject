$(document).ready(function(){
	

	//鑾峰緱鎴夸骇淇℃伅
	$.ajax({
		type:'post',
		url:'./servlet/PersonalInfoServlet?property=findHouse',
		datatype:'json',
		data:{

		},
		success:function(result){
			var r = $.parseJSON(result);
			alert(r.status);
			if(r.status){
				alert(r.message);
				var json = $.parseJSON(r.message);		
				generateHouseInfo(
					json.house_type,
					json.house_area,
					json.house_remark
				);

			}else{
				//鑾峰彇涓嶆垚鍔�
			}
		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);	
		}
	})

	//鑾峰緱涓氫富杞﹁締淇℃伅
	$.ajax({
		type:'get',
		url:'./servlet/PersonalInfoServlet?property=findVehicle',
		datatype:'json',
		data:{

		},
		success:function(result){
			var r = jQuery.parseJSON(result);
			if(r.status){
				$("#owner_no_vehicle").hide();
				$("#vehicle_estate_info").show();
				json = eval(r.message);
				for (var i = json.length - 1; i >= 0; i--) {
					generateVehicleInfo(
						json[i].vehicle_plate,
						json[i].vehicle_registe_time
					);
				};
			}else{
				$("#vehicle_estate_info").hide();
				$("#owner_no_vehicle").show();
			}
		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);	
		}
	})

	//鑾峰緱涓氫富杞︿綅淇℃伅
	$.ajax({
		type:'get',
		url:'./servlet/PersonalInfoServlet?property=findPark',
		datatype:'json',
		data:{

		},
		success:function(result){
			var r = jQuery.parseJSON(result);
			if(r.status){
				$("#owner_no_park_space").hide();
				$("#park_space_estate_info").show();
				var json = eval(r.message);
				for (var i = 0; i<json.length; i++) {
					generateParkSpaceInfo(
						json[i].park_space_id,
						json[i].park_position
					);
				};

			}else{

				$("#owner_no_park_space").show();
				$("#park_space_estate_info").hide();

			}
		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);	
		}

	});

});

function generateHouseInfo(house_type, house_area, house_remark){
	
	alert("generateHouseInfo");
	
	var $tr = $("<tr></tr>").appendTo($("#house_estate_info").children("tbody"));
	var $td1 = $("<td class = 'type'></td>").text(house_type);
	var $td2 = $("<td class = 'acre'></td>").text(house_area);
	var $td3 = $("<td class = 'remark'></td>").text(house_remark);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$tr.append($td4);

}


function generateVehicleInfo(vehicle_plate, vehicle_registe_time){
	var $tr = $("<tr></tr>").appendTo($("#vehicle_estate_info").children("tbody"));
	var $td1 = $("<td></td>").text(vehicle_plate);
	var $td2 = $("<td></td>").text(vehicle_registe_time);
	$tr.append($td1);
	$tr.append($td2);
}

function generateParkSpaceInfo(park_space_id, park_space_position){

	var $tr = $("<tr></tr").appendTo($("#park_space_estate_info").children("tbody"));
	var $td1 = $("<td></td>").text(park_space_id);
	var $td2 = $("<td></td>").text(park_space_position);
	$tr.append($td1);
	$tr.append($td2);

}