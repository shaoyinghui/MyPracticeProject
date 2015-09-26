$(document).ready(function(){

	$.ajax({
		type:'get',
		url:'./servlet/ChargeServlet?property=getHistoryEstate',
		datatype:'json',
		data:{

		},
		success:function(result){
			var r = $.parseJSON(result);
			if(r.status){
				$("#owner_no_estate_fee").hide();
				$("#estate_fee_table").show();
				$("#estate_fee_table").children("tbody").children("tr").remove();
				var json = eval(r.message);
				for(var i=0; i<json.length; i++){
					generateEstate(
						json[i].Estate_fee,
						json[i].Estate_fee_date
					);
				}
			}else{
				$("#owner_no_estate_fee").show();
				$("#estate_fee_table").hide();
				$("#estate_fee_table").children("tbody").children("tr").remove();
			}
		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
			alert(XMLHttPRequest.status);
			alert(XMLHttPRequest.readyState);
			alert(errorThrown);
		}
	})

	$("#select_fee").click(function(){
		alert("ggg");
		var estate_fee_begin_date = $("#estate_fee_begin_date").val();
		var estate_fee_end_date = $("#estate_fee_end_date").val();

		if(estate_fee_end_date != " " && estate_fee_end_date != " "){
			$.ajax({
				type:'get',
				url:'',
				datatype:'json',
				data:{
					estate_fee_begin_date:estate_fee_begin_date,
					estate_fee_end_date:estate_fee_end_date
				},
				success:function(result){
					if(result['status']){
						$("#owner_no_estate_fee").hide();
						$("#estate_fee_table").show();
						$("#estate_fee_table").children("tbody").children("tr").remove();
						json = eval(result['message']);
						for(var i=0; i<json.length; i++){
							generateEstate(
								json[i].estate_fee,
								json[i].estate_fee_date
							);
						}
					}else{
						$("#owner_no_estate_fee").show();
						$("#estate_fee_table").hide();
						$("#estate_fee_table").children("tbody").children("tr").remove();
					}
					$("#select_fee_modal").modal('hide');
				},
				error:function(XMLHttPRequest, textStatus, errorThrown){
					alert(XMLHttPRequest.status);
					alert(XMLHttPRequest.readyState);
					alert(errorThrown);
					$("#select_fee_modal").modal('hide');
				}
			})
		}
	})
	

})

function generateEstate(estate_fee, estate_fee_date){
	alert("generateEstate");
	var $tr = $("<tr><tr>").appendTo($("#estate_fee_table").children("tbody"));
	var $td1 = $("<td></td>").text(estate_fee);
	var $td2 = $("<td></td>").text(estate_fee_date);
	$tr.append($td1);
	$tr.append($td2);
}