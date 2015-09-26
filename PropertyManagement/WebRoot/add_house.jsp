<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String user_type= "用户";

%>
<!DOCTYPE html>
<html>
    <head>
        <title>智能小区管理系统</title>
        <!-- Bootstrap -->
        <meta charset = "utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <script src="js/jquery-1.9.1.min.js"></script>
        <script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src = "js/jquery.validate_cn.min.js"></script>
        <script type="text/javascript" src = "js/manage_house.js"></script>
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    
    
    <%
    	if(session==null)
    		response.sendRedirect("index.jsp");
    	else if(session.getAttribute("type")==null){
        response.sendRedirect("index.jsp");
      }else if(!session.getAttribute("type").equals("manager")){
        response.sendRedirect("index.jsp");

      }
    		
      user_type = (String)session.getAttribute("type");
    
     %>
    
    
    <body>
        <div class="navbar navbar-fixed-top">
          <div class="navbar-inner">
            <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="index.jsp">智能小区管理系统</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <!--用户名-->
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i>
                                  <%= user_type %><i class="caret"></i>

                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href="personal_info.jsp">个人信息管理</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1" href="./servlet/LoginServlet?property=exit">注销</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav">
                            <li class="dropdown active">
                                <a href="#" data-toggle="dropdown" class="dropdown-toggle">物业管理<b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu" id="menu1">
                                    <li>
                                        <a href="manage_vehicle.jsp">车辆管理</a>
                                    </li>
                                    <li>
                                        <a href="manage_house.jsp">房产管理</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">费用管理<i class="caret"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href="manage_estate_fee.jsp">物业管理费</a>
                                    </li>
                                    <li>
                                        <a tabindex="-1" href="#">水费</a>
                                    </li>
                                    <li>
                                        <a tabindex="-1" href="#">电费</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">保卫绿化<i class="caret"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href="#">保卫管理</a>
                                    </li>
                                    <li>
                                        <a tabindex="-1" href="#">绿化管理</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="">
                                <a href="manage_notice.jsp">公告管理</a>
                            </li>

                            <li class="">
                                <a href="manage_repair.jsp">保修管理</a>
                            </li>
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
            </div>
          </div>
        </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li>
                            <a href="manage_house.jsp"><i class="icon-chevron-right"></i>统计信息</a>
                        </li>
                        <li class = "active">
                            <a href="add_house.jsp"><i class="icon-chevron-right"></i>添加房产</a>
                        </li>
                        <li>
                            <a href="select_house.jsp"><i class="icon-chevron-right"></i>查询房产</a>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">添加房产</div>
                            </div>
                            <div class="block-content collapse in">
                            
                        	<div class="alert alert-info" id = "success_add_house" role="alert">
                          		<strong>添加房产成功</strong>
                        	</div>
                                <div class="span12">
                                    <form class="form-horizontal" id = "add_house_form">
                                      <fieldset>
                                        <legend>选择房产</legend>
                                        <div class="control-group">
                                          <label class="control-label" for="house_building">栋宇</label>
                                          <div class="controls">
                                            <select id="house_building">
                                              <option>1</option>
                                              <option>2</option>
                                              <option>3</option>
                                              <option>4</option>
                                              <option>5</option>
                                            </select>
                                          </div>
                                        </div>
                                        <div class="control-group">
                                          <label class="control-label" for="house_unit">单元</label>
                                          <div class="controls">
                                            <select id="house_unit">
                                              <option>1</option>
                                              <option>2</option>
                                              <option>3</option>
                                              <option>4</option>
                                              <option>5</option>
                                            </select>
                                          </div>
                                        </div>
                                        <div class="control-group">
                                          <label class="control-label" for="house_floor">楼层</label>
                                          <div class="controls">
                                            <select id="house_floor">
                                              <option>1</option>
                                              <option>2</option>
                                              <option>3</option>
                                              <option>4</option>
                                              <option>5</option>
                                            </select>
                                          </div>
                                        </div>
                                        <div class="control-group">
                                          <label class="control-label" for="house_num">房号</label>
                                          <div class="controls">
                                            <select id="house_num">
                                              <option>1</option>
                                              <option>2</option>
                                              <option>3</option>
                                              <option>4</option>
                                              <option>5</option>
                                            </select>
                                          </div>
                                        </div>
                                        <legend>填写账号初始信息</legend>
                                        <div class="control-group">
                                           <label class="control-label" for="account_name">账号</label>
                                           <div class="controls">
                                              <input class="input-xlarge focused" id="account_name" name = "account_name" type="text" placeholder="账号">
                                              <span class = "error_hint"></span>
                                           </div>
                                        </div>
                                        <div class="control-group">
                                           <label class="control-label" for="account_password">密码</label>
                                           <div class="controls">
                                              <input class="input-xlarge  focused" id="account_password" name = "account_password" type="password" placeholder="密码">
                                              <span class = "error_hint"></span>
                                           </div>
                                        </div>
                                        <legend>填写业主信息</legend>
                                        <div class="control-group">
                                           <label class="control-label" for="owner_name">姓名</label>
                                           <div class="controls">
                                              <input class="input-xlarge  focused" name = "owner_name" id="owner_name" type="text" placeholder="姓名">
                                              <span class = "error_hint"></span>
                                           </div>
                                        </div>
                                        <div class="control-group">
                                           <label class="control-label" for="owner_phone">手机</label>
                                           <div class="controls">
                                              <input class="input-xlarge focused" name = "owner_phone" id="owner_phone" type="text" placeholder="手机">
                                              <span class = "error_hint"></span>
                                           </div>
                                        </div>                                        
                                        <div class="control-group">
                                           <label class="control-label" for="owner_email">邮箱</label>
                                           <div class="controls">
                                              <input class="input-xlarge focused" name = "owner_email" id="owner_email" type="text" placeholder="邮箱">
                                              <span class = "error_hint"></span>
                                           </div>
                                        </div>
                                        <div class="control-group">
                                           <label class="control-label" for="owner_age">年龄</label>
                                           <div class="controls">
                                              <input class="input-xlarge focused" name = "owner_age" id="owner_age" type="text" placeholder="年龄">
                                              <span class = "error_hint"></span>
                                           </div>
                                        </div>
                                        <div class="control-group">
                                          <label class="control-label" for="owner_gender">性别</label>
                                            <label class="radio-inline">
                                              <input type="radio" name="owner_gender" checked = "checked" id="owner_gender" value="0"> 男
                                            </label>
                                            <label class="radio-inline">
                                              <input type="radio" name="owner_gender" id="owner_gender" value="1"> 女
                                            </label>
                                        </div>
                                        <div class="form-actions">
                                          <button type="button" class="btn btn-primary" id = "submit_add_house">提交</button>
                                          <button type="reset" class="btn" id = "cancel">取消</button>
                                        </div>
                                      </fieldset>
                                    </form>

                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
				        </div>
			      </div>
			  </div>
</html>