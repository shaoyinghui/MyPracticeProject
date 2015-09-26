<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String user_type;

%>
<!DOCTYPE html>
<html class="no-js">
    
    <head>
        <title>智能小区管理系统</title>
        <!-- Bootstrap -->
        <meta charset = "utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <!--<link href="vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">-->
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <script src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src = "js/jquery.md5.js"></script>
        <script type="text/javascript" src = "js/jquery.validate_cn.min.js"></script>
        <script type="text/javascript" src = "js/manage_person_info.js"></script>
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!--<script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>-->
    </head>
    
    
    
    <%
    	if(session==null)
    		response.sendRedirect("index.jsp");
    	else if(session.getAttribute("type")==null)
    		response.sendRedirect("index.jsp");
    	else if(!session.getAttribute("type").equals("manager"))
    		response.sendRedirect("index.jsp");

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
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> <%= user_type %> <i class="caret"></i>
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
                            <li class="dropdown">
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
                        <li class="active">
                            <a href="personal_info.jsp"><i class="icon-chevron-right"></i>个人信息</a>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">个人信息</div>
                            </div>
                            <div class="block-content collapse in">
                                <form class="form-horizontal">
                                    <legend>个人信息</legend>
                                    <div class="control-group">
                                        <label class="control-label" for="manager_name">姓名</label>
                                        <div class="controls">
                                            <input class="input-xlarge focused" disabled id="manager_name" value = "郭家琪" type="text" placeholder="标题">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="manager_phone">个人联系电话</label>
                                        <div class="controls">
                                            <input class="input-xlarge focused" disabled id="manager_phone" value = "18392889250" type="text" placeholder="">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="manager_teil">办公联系电话</label>
                                        <div class="controls">
                                            <input class="input-xlarge focused" id="manager_teil" disabled type="text" value = "0757-82295280" placeholder="">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="manager_email">邮箱</label>
                                        <div class="controls">
                                            <input class="input-xlarge focused" disabled id="manager_email" type="text" placeholder="">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="manager_age">年龄</label>
                                        <div class="controls">
                                            <input class="input-xlarge focused" disabled id="manager_age" type="text" placeholder="">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
                        </div>
                  </div>
              </div>
        
        <!--/.fluid-container-->

    </body>

</html>