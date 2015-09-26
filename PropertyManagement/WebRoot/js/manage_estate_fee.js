$(document).ready(function(){

	$.ajax({
		type:'get',
		url:'./servlet/ChargeServlet?property=getHistoryUnit_price',
		datatype:'json',
		data:{

		},
		success:function(result){
			var r = $.parseJSON(result);
			if(r.status){
				json = eval(r.message);
				$("#estate_fee").val(json[json.length-1].unit_price);
			}
		},
		error:function(XMLHttPRequest, textStatus, errorThrown){
				alert(XMLHttPRequest.status);
				alert(XMLHttPRequest.readyState);
				alert(errorThrown);	
		}
	})

	$("#update_estate_fee_form").validate({
		rules:{
			new_estate_fee:"required",
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	})

	$("#submit_new_fee").click(function(){
		if($("#update_estate_fee_form").valid()){

			var new_estate_fee = $("#new_estate_fee").val();

			$.ajax({
				type:'post',
				url:'./servlet/ChargeServlet?property=setUnit_price',
				datatype:'json',
				data:{
					unit_price:new_estate_fee
				},
				success:function(result){
					var r = $.parseJSON(result);
					if(r.status){
						$("#estate_fee").val(new_estate_fee);
					}else{

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

})