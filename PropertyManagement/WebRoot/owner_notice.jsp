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
        <script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src = "js/jquery.validate_cn.min.js"></script>
        <script type="text/javascript" src = "js/owner_inquire.js"></script>
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
                        <li class="active">
                            <a href="owner_notice.jsp"><i class="icon-chevron-right"></i>查询公告</a>
                        </li>
                        <li>
                            <a href="owner_fee_inquire.jsp"><i class="icon-chevron-right"></i>查询费用</a>
                        </li>
                        <li class = "">
                            <a href="repair_info_inquire.jsp"><i class="icon-chevron-right"></i>查询报修信息</a>
                        </li>
                        <li>
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
                                <div class="muted pull-left">查询公告</div>
                                <span class = "select_notice"><a href="#" data-toggle="modal" data-target="#select_notice_modal">筛选</a></span>
                            </div>
                            <div class="block-content collapse in">
                                <div class="row marketing" id = "manage_show_notice"> 
                                    <div class = "show_notice"> 

                                        <legend>所有公告</legend>

                                        <div class="alert alert-info" id = "owner_no_notice" role="alert">
                                          <strong>很抱歉！没有符合条件的公告 </strong>
                                        </div>

                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
                        </div>
                  </div>
              </div>


        <!-- Modal 筛选公告 -->
        <div class="modal fade" id="select_notice_modal" tabindex="-1" role="dialog" style="display: none;" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">筛选公告</h4>
              </div>
              <div class="modal-body">
                <form class="form-horizontal">
                  <div class="control-group">
                   <label class="control-label" for="notice_type">类型</label>
                    <div class="controls">
                      <select id="notice_type">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                      </select>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="notice_begin_date">开始日期</label>
                    <div class="controls">
                      <input class="input-xlarge focused" id="notice_begin_date" type="date" placeholder="">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="notice_end_date">结束日期</label>
                    <div class="controls">
                      <input class="input-xlarge focused" id="notice_end_date" type="date" placeholder="">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="notice_key_word">关键字</label>
                    <div class="controls">
                      <input class="input-xlarge focused" id="notice_key_word" type="text" placeholder="">
                    </div>
                  </div>      
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id = "cancel_select">取消</button>
                <button type="button" class="btn btn-primary" id = "owner_select_notice">确定</button>
              </div>
            </div>
          </div>
        </div>

            </body>

</html>