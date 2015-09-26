<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String user_type= "用户";

%>
<!DOCTYPE html>
<html class="no-js">
    
    <head>
        <title>智能小区管理系统</title>
        <!-- Bootstrap -->
        <meta charset = "utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <!--<link href="vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">-->
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <script src="js/jquery-1.9.1.min.js"></script>
        <script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <!--<script type="text/javascript" src = "js/jquery.validate_cn.min.js"></script>-->
        <!--<script type="text/javascript" src = "js/add_notice.js"></script>-->
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
    	else if(!session.getAttribute("type").equals("manager")){
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
                            <li class="active">
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
                            <a href="manage_notice.jsp"><i class="icon-chevron-right"></i>查询公告</a>
                        </li>
                        <li class="active">
                            <a href="add_notice.jsp"><i class="icon-chevron-right"></i>添加公告</a>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">添加公告</div>
                            </div>
                            <div class="block-content collapse in">
                                <form class="form-horizontal" id = "add_notice_form" action="./servlet/NoticeServlet?property=addNotice" method = "post">
                                  <legend>填写公告信息</legend>
                                  <div class="control-group">
                                   <label class="control-label" for="notice_type">类型</label>
                                    <div class="controls">
                                      <select name = "notice_type" id="notice_type">
                                        <option>gjqs</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                      </select>
                                    </div>
                                  </div>
                                  <div class="control-group">
                                    <label class="control-label" for="notice_title">标题</label>
                                    <div class="controls">
                                      <input class="input-xlarge focused" id="notice_title" name = "notice_title" type="text" placeholder="标题" required >
                                    </div>
                                  </div>
                                  <div class="control-group">
                                    <label class="control-label" for="notice_content">内容</label>
                                    <div class="controls">
                                      <textarea class="form-control" name = "notice_content" id = "notice_content" rows="3" cols = "12" required ></textarea>
                                    </div>
                                  </div>
                                  <div class="form-actions">
                                     <button type="submit" class="btn btn-primary" id = "submit">提交</button>
                                     <button type="reset" class="btn" id = "cancel">取消</button>
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
