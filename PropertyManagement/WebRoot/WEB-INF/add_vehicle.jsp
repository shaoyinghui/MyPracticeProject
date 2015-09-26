<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <title>智能小区管理系统</title>
        <!-- Bootstrap -->
        <meta charset = "utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="css/styles.css" rel="stylesheet" media="screen">
        <script src="js/jquery-1.9.1.min.js"></script>
        <script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src = "js/jquery.validate_cn.min.js"></script>
        <script type="text/javascript" src = "js/manage_vehicle.js"></script>
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    
    <%
    	if(session==null)
    		response.sendRedirect("index.jsp");
    	else if(session.getAttribute("type")==null)
    		response.sendRedirect("index.jsp");
    	else if(!session.getAttribute("type").equals("manager"))
    		response.sendRedirect("index.jsp");
    
     %>
    
    
    
    
    <body>
        <div class="navbar navbar-fixed-top">
          <div class="navbar-inner">
            <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="index.html">智能小区管理系统</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <!--用户名-->
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> 郭家琪 <i class="caret"></i>

                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href="personal_info.html">个人信息管理</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1" href="login.html">注销</a>
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
                                        <a href="manage_vehicle.html">车辆管理</a>
                                    </li>
                                    <li>
                                        <a href="manage_house.html">房产管理</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">费用管理<i class="caret"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href="manage_estate_fee.html">物业管理费</a>
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
                                <a href="manage_notice.html">公告管理</a>
                            </li>

                            <li class="">
                                <a href="manage_repair.html">保修管理</a>
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
                            <a href="manage_vehicle.html"><i class="icon-chevron-right"></i>统计信息</a>
                        </li>
                        <li  class="active">
                            <a href="add_vehicle.html"><i class="icon-chevron-right"></i>登记车辆信息</a>
                        </li>
                        <li>
                            <a href="select_vehicle.html"><i class="icon-chevron-right"></i>查询车辆信息</a>
                        </li>
                        <li>
                            <a href="add_park_space.html"><i class="icon-chevron-right"></i>登记车位</a>
                        </li>
                        <li>
                            <a href="select_park_space.html"><i class="icon-chevron-right"></i>查看车位</a>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">

                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">添加车辆信息</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="alert alert-info" id = "add_vehicle_success" role="alert">
                                  <strong>登记车辆成功</strong>
                                </div>
                                <div class="alert alert-info" id = "house_null" role="alert">
                          			<strong>很抱歉！该业主不存在！</strong>
                        		</div>
                                <div class="span12">
                                    <form class="form-horizontal" id = "add_vehicle_form">
                                      <fieldset>
                                        <legend>选择业主</legend>
                                        <div class="control-group">
                                          <label class="control-label" for="house_building">栋宇</label>
                                          <div class="controls">
                                            <select id="house_building" required name = "house_building">
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
                                            <select id="house_unit" required name = "house_unit">
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
                                            <select id="house_floor" required name = "house_floor">
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
                                            <select id="house_num" required name = "house_num">
                                              <option>1</option>
                                              <option>2</option>
                                              <option>3</option>
                                              <option>4</option>
                                              <option>5</option>
                                            </select>
                                          </div>
                                        </div>
                                        <legend>填写车辆信息</legend>
                                        <div class="control-group">
                                           <label class="control-label" for="vehicle_plate">车牌号码</label>
                                           <div class="controls">
                                              <input class="input-xlarge focused" required name = "vehicle_plate" id="vehicle_plate" type="text" placeholder="粤E12C26">
                                              <span class = "error_hint"></span>
                                           </div>
                                        </div>
                                        <div class="form-actions">
                                          <button type="button" class="btn btn-primary" id = "submit_plate">提交</button>
                                          <button type="reset" class="btn">取消</button>
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


        <!-- Modal 登记车辆确认信息 -->
        <div class="modal fade" id="add_vehicle_modal" style = "display:none;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">确认业主信息</h4>
              </div>
              <div class="modal-body">
                <table class = "table" id = "add_vehicle_config_table">
                  <thead>
                    <tr>
                      <th>姓名</th>
                      <th>电话</th>
                      <th>邮箱</th>
                    </tr>
                  </thead>
                  <tbody>
                  </tbody>
                </table>
                <hr>
                <p>是否确定要为该业主登记车辆？</p>
                <p id = "config_vehicle_plate"></p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id = "cancel_add">取消</button>
                <button type="button" class="btn btn-primary" id = "submit_vehicle">确定</button>
              </div>
            </div>
          </div>
        </div>
</html>