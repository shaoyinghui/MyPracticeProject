<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String user_type;

%>

<!DOCTYPE html>
<html>
    <head>
        <title>智能小区管理系统</title>
        <!-- Bootstrap -->
        <meta charset = "utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="js/jquery-1.9.1.min.js"></script>
        <script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src = "js/jquery.validate_cn.min.js"></script>
        <script type="text/javascript" src = "js/manage_vehicle.js"></script>
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
                            <a href="manage_vehicle.jsp"><i class="icon-chevron-right"></i>统计信息</a>
                        </li>
                        <li>
                            <a href="add_vehicle.jsp"><i class="icon-chevron-right"></i>登记车辆信息</a>
                        </li>
                        <li>
                            <a href="select_vehicle.jsp"><i class="icon-chevron-right"></i>查询车辆信息</a>
                        </li>
                        <li>
                            <a href="add_park_space.jsp"><i class="icon-chevron-right"></i>登记车位</a>
                        </li>
                        <li class = "active">
                            <a href="select_park_space.jsp"><i class="icon-chevron-right"></i>查看车位</a>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                      <!-- morris stacked chart -->
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">查询车辆信息</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
                                    <form class="form-horizontal" id = "select_park_space_form">
                                      <fieldset>
                                        <legend>选择业主</legend>
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
                                        <div class="form-actions">
                                          <button type="button" id = "submit_select_park_space" class="btn btn-primary" >确定</button>
                                          <button type="reset" id = "cancel" class="btn">取消</button>
                                          <!--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Launch demo modal</button>-->
                                        </div>
                                      </fieldset>
                                    </form>
                                    <legend>查询结果</legend>
                                    <div class="alert alert-info" id = "no_park_space" role="alert">
                                      <strong>很抱歉！该业主没有登记车位！</strong>
                                    </div>
                                    <div class="alert alert-info" id = "update_park_space_has_been_used" role="alert">
                                      <strong>很抱歉！该车位已被登记！</strong>
                                    </div>
                                    <div class="alert alert-info" id = "park_space_has_been_removed" role="alert">
                                      <strong>车位取消登记成功！</strong>
                                    </div>
                                    <table class = "table" id = "select_park_space_result">                                    
                                      <thead>
                                        <tr>
                                          <th>车位编号</th>
                                          <th>车位位置</th>
                                          <th>操作</th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                        
                                      </tbody>                          
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
				        </div>
			      </div>
			   </div>    
         <hr>

        <!-- Modal 删除车位信息 -->
        <div class="modal fade" id="delete_park_space_modal" tabindex="-1" style = "display:none" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">删除车位</h4>
              </div>
              <div class="modal-body">
                <p>是否确定删除该用户车位？</p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id = "cancel_delete">取消</button>
                <button type="button" class="btn btn-primary" id = "submit_delete_park_space">确定</button>
              </div>
            </div>
          </div>
        </div>

        <!-- Modal 修改车位信息 -->
        <div class="modal fade" id="update_park_space_modal" tabindex="-1" style = "display:none" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改用户车位信息</h4>
              </div>
              <div class="modal-body">
                <form>
                  <div class="control-group">
                    <div class="control-group">
                      <label class="control-label" for="new_park_space_id">车位编号</label>
                      <div class="controls">
                        <select id="new_park_space_id">
                          <option>1</option>
                          <option>2</option>
                          <option>3</option>
                          <option>4</option>
                          <option>5</option>  
                        </select>
                      </div>
                    </div>
                  </div>                
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id = "cancel_update">取消</button>
                <button type="button" class="btn btn-primary" id = "submit_update_park_space">确定</button>
              </div>
            </div>
          </div>
        </div>


</html>