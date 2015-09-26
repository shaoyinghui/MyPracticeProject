$(document).ready(function(){

	var is_owner = true;

	$("#submit").click(function(){
		if($("#login_form").valid()){
			
			var account_name = $("#username").val();			
			var account_password = $("#password").val();
			var type = "owner";
			if(!is_owner){
				type = "manager";
			}else{
				type = "owner";
			}

			$.ajax({
				type:'post',
				url:'./servlet/LoginServlet?property=login',
				dataType:'json',
				data:{
					username:account_name,
					password:account_password,
					type:type
				},
				success:function(result){
					if (result['status']) {
						//璺宠浆鍒颁笟涓荤鐞嗙晫闈�绠＄悊鍛樼晫闈�
						alert("登陆成功");
						window.location.href = result['message'];
						
					}else{
						alert(result['message']);
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

	$("#login_form").validate({
		rules:{
			account_name:"required",
			account_password:"required"
		},
		errorPlacement:function(error, element){
			error.appendTo(element.next());
			element.parent().addClass("has-error");
			//error.appendTo(element.parent());
		}
	})

	$(".owner_login").click(function(){

		is_owner = true;
		$(this).addClass("selected");
		$(".manager_login").removeClass("selected");
	})

	$(".manager_login").click(function(){
		is_owner = false;
		$(this).addClass("selected");
		$(".owner_login").removeClass("selected");
	})

})