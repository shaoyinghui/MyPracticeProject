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
        <!--<link href="css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">-->
        <!--<link href="vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">-->
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <script src="js/jquery-1.9.1.min.js"></script>
        <script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src = "js/jquery.validate_cn.min.js"></script>
        <script type="text/javascript" src = "js/owner_inquire_estate.js"></script>
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
    	else if(!session.getAttribute("type").equals("owner"))
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
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1" href="./servlet/LoginServlet?property=exit">注销</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav">
                            <li class = "active">
                                <a href="owner_notice.jsp">物业信息</a>
                            </li>
                            <li class="">
                                <a href="family_info.jsp">家庭信息</a>
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
                        <li class="">
                            <a href="owner_notice.jsp"><i class="icon-chevron-right"></i>查询公告</a>
                        </li>
                        <li>
                            <a href="owner_fee_inquire.jsp"><i class="icon-chevron-right"></i>查询费用</a>
                        </li>
                        <li class = "">
                            <a href="repair_info_inquire.jsp"><i class="icon-chevron-right"></i>查询报修信息</a>
                        </li>
                        <li class = "active">
                            <a href="owner_estate_inquire.jsp"><i class="icon-chevron-right"></i>查询房产车辆信息</a>
                        </li> 
                        <li class = "">
                            <a href="contact_manager.jsp"><i class="icon-chevron-right"></i>联系管理员</a>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">查询房产车辆信息</div>
                            </div>
                            <div class="block-content collapse in">
                                <table class = "table" id = "house_estate_info">
                                  <legend>房产信息</legend>
                                  <thead>
                                    <tr>
                                      <th>房产类型</th>
                                      <th>房产面积（平方米）</th>
                                      <th>房产备注</th>
                                    </tr>
                                  </thead>
                                  <tbody>

                                  </tbody>
                                </table>

                                <legend>登记车辆信息</legend>
                                <div class="alert alert-info" id = "owner_no_vehicle" role="alert">
                                  <strong>很抱歉！没有登记车辆</strong>
                                </div>
                                <table class = "table" id = "vehicle_estate_info">
                                  <thead>
                                    <tr>
                                      <th>车牌号码</th>
                                      <th>登记时间</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    
                                  </tbody>
                                </table>

                                <table class = "table" id= "park_space_estate_info">
                                  <legend>登记车位信息</legend>
                                  
                                <div class="alert alert-info" id = "owner_no_park_space" role="alert">
                                  <strong>很抱歉！没有登记车位</strong>
                                </div>
                                  <thead>
                                    <tr>
                                      <th>车位编号</th>
                                      <th>车位位置</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    
                                  </tbody>
                                </table>
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