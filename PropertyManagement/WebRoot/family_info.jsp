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
        <script type="text/javascript" src = "js/jquery.validate_cn.min.js"></script>
        <script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src = "js/owner_inquire_family.js"></script>
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
                            <li class = "">
                                <a href="owner_notice.jsp">物业信息</a>
                            </li>
                            <li class="active">
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
                            <a href="family_info.jsp"><i class="icon-chevron-right"></i>家庭信息查询</a>
                        </li>
                        <li class = "">
                            <a href="update_owner_account.jsp"><i class="icon-chevron-right"></i>家庭账号信息修改</a>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">家庭信息查询</div>
                            </div>
                            <div class="block-content collapse in">
                                <table class = "table" id = 'owner_family_info_table'>
                                  <legend>业主信息</legend>
                                  <thead>
                                    <tr>
                                      <th>姓名</th>
                                      <th>性别</th>
                                      <th>年龄</th>
                                      <th>手机</th>
                                      <th>邮箱</th>
                                      <th>操作</th>
                                    </tr>
                                  </thead>
                                  <tbody>

                                  </tbody>
                                </table>
  
                                <span><a href="#" data-toggle="modal" data-target="#add_family_member_modal">添加家庭成员</a></span>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
                        </div>
                  </div>
              </div>

              <!--添加家庭成员-->
              <div class="modal fade" id="add_family_member_modal" tabindex="-1" role="dialog" style="display: none;" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title" id="myModalLabel">添加家庭成员</h4>
                    </div>
                    <div class="modal-body">
                      <form class="form-horizontal" id = "add_family_member_form">
                        <div class="control-group">
                          <label class="control-label" for="owner_name">姓名</label>
                          <div class="controls">
                            <input class="input-xlarge focused" name = "owner_name" id="owner_name" type="text" placeholder="姓名">
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
                          <label class="control-label" for="owner_phone">电话</label>
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
                          <div class="control-group">
                           <label class="control-label" for="owner_gender">性别</label>
                             <label class="radio-inline">
                               <input type="radio" name="owner_gender" checked = "checked" id="owner_gender" value="0"> 男
                             </label>
                             <label class="radio-inline">
                               <input type="radio" name="owner_gender" id="owner_gender" value="1"> 女
                             </label>
                         </div>
                        </div>
                      </form>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal" id = "cancel_add">取消</button>
                      <button type="button" class="btn btn-primary" id = "add_family_member">确定</button>
                    </div>
                  </div>
                </div>
              </div>

              <!--修改家庭成员信息-->
              <div class="modal fade" id="update_family_member_modal" tabindex="-1" role="dialog" style="display: none;" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title" id="myModalLabel">修改家庭成员信息</h4>
                    </div>
                    <div class="modal-body">
                      <form class="form-horizontal" id = "update_family_member_info_form">
                        <div class="control-group">
                          <label class="control-label" for="new_owner_name">姓名</label>
                          <div class="controls">
                            <input class="input-xlarge focused" name = "new_owner_name" id="new_owner_name" type="text" placeholder="姓名">
                            <span class = "error_hint"></span>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label" for="new_owner_age">年龄</label>
                          <div class="controls">
                             <input class="input-xlarge focused" name = "new_owner_age" id="new_owner_age" type="text" placeholder="年龄">
                             <span class = "error_hint"></span>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label" for="new_owner_phone">电话</label>
                          <div class="controls">
                             <input class="input-xlarge focused" name = "new_owner_phone" id="new_owner_phone" type="text" placeholder="手机">
                             <span class = "error_hint"></span>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label" for="new_owner_email">邮箱</label>
                          <div class="controls">
                             <input class="input-xlarge focused" name = "new_owner_email" id="new_owner_email" type="text" placeholder="邮箱">
                             <span class = "error_hint"></span>
                          </div>
                        </div>
		                  <div class="control-group">
		                    <label class="control-label" for="new_owner_gender">性别</label>
		                      <label class="radio-inline">
		                        <input type="radio" name="new_owner_gender"  id="new_owner_gender_male" value="0"> 男
		                      </label>
		                      <label class="radio-inline">
		                        <input type="radio" name="new_owner_gender" id="new_owner_gender_female" value="1"> 女
		                      </label>
		                  </div> 
                      </form>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal" id = "cancel_add">取消</button>
                      <button type="button" class="btn btn-primary" id = "update_family_member">保存</button>
                    </div>
                  </div>
                </div>
              </div>

               <!-- Modal 删除家庭成员 -->
              <div class="modal fade" id="delete_family_member_modal" tabindex="-1" role="dialog" style="display: none;" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title" id="myModalLabel">删除家庭成员</h4>
                    </div>
                    <div class="modal-body">
                      <p>是否确定删除该家庭成员？</p>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal" id = "cancel_delete">取消</button>
                      <button type="button" class="btn btn-primary" id = "delete_family_member">确定</button>
                    </div>
                  </div>
                </div>
              </div>

        <!--/.fluid-container-->
    </body>

</html>