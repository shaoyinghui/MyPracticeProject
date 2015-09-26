$(document).ready(function(){
	alert("hhhh");

	$("#owner_update_info_form").validate({
		rules:{
			owner_new_password:{
				required: true,
                minlength: 6
			},
            owner_config_password: {
                required: true,
                minlength: 6,
                equalTo: "#owner_new_password"
            }
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
		}
	})

	$("#submit_owner_new_password").click(function(){
		
		alert("ggg");
		
		if($("#owner_update_info_form").valid()){

			var owner_new_password = $.md5($("#owner_new_password").val());
			var owner_config_password = $.md5($("#owner_config_password").val());
			
			console.log(owner_new_password);

			$.ajax({
				type:'post',
				url:'./servlet/PersonalInfoServlet?property=updatePassword',
				datatype:'json',
				data:{
					newpassword:owner_new_password,
				},
				success:function(result){
					var r = jQuery.parseJSON(result);
					if(r.status){
						alert(r.message);
						window.location.href = r.message;
						
					}else{
						alert("error");
					}
				},
				errorPlacement:function(error, element){
					error.appendTo(element.next());
					element.parent().addClass("has-error");
				}

			})
		}

	})


})