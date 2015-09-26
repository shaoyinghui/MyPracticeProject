<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

  <title>智能小区管理</title>

  <script type="text/javascript" src = "js/jquery-1.9.1.min.js"></script>
 
  <!-- Bootstrap core CSS -->
  <link href="bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet" media="screen"> 

  <!-- Custom styles for this template -->
  <link href="css/jumbotron-narrow.css" rel="stylesheet">

  <link rel="stylesheet" type="text/css" href="css/index.css">

  <script type="text/javascript" src = "js/bootstrap.min.js"></script>
  <script type="text/javascript" src = "js/jquery.validate_cn.min.js"></script>
  <script type="text/javascript" src = "js/jquery.md5.js"></script>
  <script type="text/javascript" src = "js/index.js"></script>

</head>

<body>

<% if(session!=null){	
	if(session.getAttribute("type")!=null){
		String type = (String)session.getAttribute("type");
		if(type.equals("owner"))
			response.sendRedirect("owner_notice.jsp");
		else if(type.equals("manager"))
			response.sendRedirect("manage_vehicle.jsp");
		else if(type.equals("root"))
			response.sendRedirect("Super_Login.jsp");
	}
}
%>




  <div class = "container">
    <header class="header clearfix">
      <nav>
        <ul class="nav nav-pills pull-right">
          <li role="presentation" class="active"><a href="#">主页</a></li>
          <li role="presentation"><a href="#">关于我们</a></li>
          <li role="presentation"><a href="#">联系我们</a></li>
        </ul>
      </nav>
      <h3 class="text-muted">智能小区管理系统</h3>
    </header>

    <div class="jumbotron jumbotron_extra">
      <div class = "plant">
        <!--<p class = "village_name">碧翠雅轩</p>-->
        <div class = "row select_div">
          <div class = "col-xs-6 owner_login selected">业主登录</div>
          <div class = "col-xs-6 manager_login">管理员登录</div>
        </div>
        <form id = "login_form">
          <div class = "form-group form_group_extra">
            <label for = "username" class="col-sm-2 control-label sr-only "></label>
            <input type = "text" class = "form-control" id = "username" name = "account_name"  placeholder = "用户名" required />
            <span class = "error_hint"></span>
          </div>
          <div class = "form-group form_group_extra">    
            <label for = "password" class="col-sm-2 control-label sr-only">密码</label>
            <input type = "password" id = "password" class = "form-control " name = "account_password" placeholder = "密码" required />
            <span class = "error_hint"></span>
          </div>
          <button type="button" class="btn btn-default btn-sm btn-block btn_color_extra" id = "submit">登录</button>
          <span class = "error_hint"></span>
          <a href="" id = "forget_password">忘记密码</a>
        </form>
      </div>
    </div>

    <hr>

    <div class="row marketing">
  
      <div>
        <h4>温馨提醒</h4>
        <h6>2015-7-3 11:14:21</h6>
        <p>由于受“灿鸿”台风和北方冷空气共同影响，近期扬州可能出现大风、大雨的极端天气。届时请各位业主关好自己门窗，拿回放在阳台上的花草及物品，谨防从高层掉落造成危险，车库里面尽量不要摆放贵重物品，防止受潮造成损失。</p>

        <hr>
 
        <h4>关于禁止在楼道内堆放杂物的通知</h4>
        <h6>2015-7-3 11:14:21</h6>
        <p>近期物业人员在检查和巡视过程中发现个别单元楼道内堆放杂物和易燃物品，极易发生火灾。为确保您自身及广大业主的生命财产安全，防止火灾发生，请在7月13日前将您的私人物品整理好妥善保存到户内，不要将其堆放在楼道、消防通道和单元门口等公共区域。对楼道内未及时清理的杂物，物业公司将视为无人认领之物，代为处置。请广大业主积极配合我们的楼道清理工作，防患于未“燃”！</p>

      </div>
    </div>

    <footer class="footer">
      <p>&copy; Company 2014</p>
    </footer>


    </div>
</body>

</html>