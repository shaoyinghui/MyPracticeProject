$(document).ready(function(){
	$.ajax({
		type:'get',
		url:'',
		datatype:'json',
		data:{

		},
		success:function(result){
			if(result['status']){
				json = eval(result['message']);
				$("#estate_fee").val(json.unit_price);
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
})