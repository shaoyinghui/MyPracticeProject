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
                            <a href="manage_house.jsp"><i class="icon-chevron-right"></i>统计信息</a>
                        </li>
                        <li>
                            <a href="add_house.jsp"><i class="icon-chevron-right"></i>添加房产</a>
                        </li>
                        <li class = "active">
                            <a href="select_house.jsp"><i class="icon-chevron-right"></i>查询房产</a>
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
                                <div class="muted pull-left">查询房产信息</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
                                    <form class="form-horizontal">
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
                                        <div class="form-actions">
                                          <button type="button" id = "submit_inquire_house" class="btn btn-primary">确定</button>
                                          <button type="reset" id = "cancel" class="btn">取消</button>
                                          <!--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Launch demo modal</button>-->
                                        </div>
                                      </fieldset>
                                    </form>
                                    <legend>房产信息</legend>
                                    <table class = "table" id = "house_info_table">
                                      <thead>
                                        <tr>
                                          <th>房产类型</th>
                                          <th>房产面积（平方米）</th>
                                          <th>房产备注</th>
                                          <th>操作</th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                        
                                      </tbody>                          
                                    </table>
                                    <legend>业主信息</legend>
                                    <div class="alert alert-info" id = "no_owner" role="alert">
                                      <strong>很抱歉！该房产暂时没有业主</strong>
                                    </div>
                                    <table class = "table" id ="owner_info_table" >

                                      <thead>
                                        <tr>
                                          <th>姓名</th>
                                          <th>联系电话</th>
                                          <th>邮箱</th>
                                          <th>年龄</th>
                                          <th>性别</th>
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

        <!-- Modal 删除业主信息 -->
        <div class="modal fade" id="delete_owner_modal" tabindex="-1" style = "display:none;" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">删除业主</h4>
              </div>
              <div class="modal-body">
                <p>是否确定删除该业主？</p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id = "cancel_delete">取消</button>
                <button type="button" class="btn btn-primary" id = "delete_owner">确定</button>
              </div>
            </div>
          </div>
        </div>

        <!-- Modal 修改业主信息 -->
        <div class="modal fade" id="update_owner_modal" tabindex="-1" style = "display:none;" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改业主信息</h4>
              </div>
              <div class="modal-body">
                <form class="form-horizontal" id = 'update_owner_form'>
                  <div class="control-group">
                    <label class="control-label" for="new_owner_name">姓名</label>
                    <div class="controls">
                      <input class="input-xlarge focused" name = 'new_owner_name' id="new_owner_name" type="text" placeholder="姓名">
                      <span class = "error_hint"></span>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="new_owner_phone">手机</label>
                    <div class="controls">
                      <input class="input-xlarge focused" name = 'new_owner_phone' id="new_owner_phone" type="text" placeholder="手机">
                      <span class = "error_hint"></span>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="new_owner_email">邮箱</label>
                    <div class="controls">
                      <input class="input-xlarge focused" name = 'new_owner_email' id="new_owner_email" type="text" placeholder="邮箱">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="new_owner_age">年龄</label>
                    <div class="controls">
                      <input class="input-xlarge focused" name = 'new_owner_age' id="new_owner_age" type="text" placeholder="年龄">
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
                <button type="button" class="btn btn-default" data-dismiss="modal" id = "cancel_update">取消</button>
                <button type="button" class="btn btn-primary" id = "update_owner">确定</button>
              </div>
            </div>
          </div>
        </div>

        <!--修改房产信息-->
        <div class="modal fade" id="update_house_modal" tabindex="-1" style = "display:none;" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改房产信息</h4>
              </div>
              <div class="modal-body">
                <form class="form-horizontal" id = "update_house_form">
                  <div class="control-group">
                    <label class="control-label" for="house_type">类型</label>
                    <div class="controls">
                      <select name = "house_type" id="house_type">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                      </select>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="house_acre">面积</label>
                    <div class="controls">
                      <input class="input-xlarge focused" name = "house_acre" id="house_acre" type="text" placeholder="面积">
                      <span class = "error_hint"></span>
                    </div>
                  </div>                  
                  <div class="control-group">
                    <label class="control-label" for="house_remark">备注</label>
                    <div class="controls">
                      <textarea class="form-control" name = "house_remark" id = "house_remark" rows="3"></textarea>
                      <span class = "error_hint"></span>
                    </div>
                  </div>
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id = "cancel_update">取消</button>
                <button type="button" class="btn btn-primary" id = "update_house">确定</button>
              </div>
            </div>
          </div>
        </div>


</html>
