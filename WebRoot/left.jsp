<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMS 后台管理工作平台</title>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="js/jquery.jstree.js"></script>
<script language="javascript">
	$(function()
	{
		//将menuContainer变成一棵树！
		$("#menuContainer").jstree({
			"themes" : {
				"theme" : "apple"
			},
			"plugins" : [ "themes", "html_data" ]
		});

		//给所有的链接设置其target属性为rightFrame，即在右边打开链接地址
		$("a").attr("target", "rightFrame");
	});
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size: 12px;
}
-->
</style>
</head>
<body>
	<div id="menuContainer" style="height:100%;">
		<ul id="navigation">
			<li><a href="#">个人办公</a>
				<ul>
					<li><a href="#">桌面</a>
						<ul>
							<li><a href="#">我的桌面</a>
							</li>
							<li><a href="#">桌面配置</a>
							</li>
						</ul></li>
					<li><a href="#">任务计划</a>
						<ul>
							<li><a href="#">创建计划</a>
							</li>
							<li><a href="#">查询计划</a>
							</li>
							<li><a href="#">计划分类</a>
							</li>
						</ul></li>
					<li><a href="#">日程安排</a>
						<ul>
							<li><a href="#">创建日程</a>
							</li>
							<li><a href="#">查询日程</a>
							</li>
							<li><a href="#">日程分类</a>
							</li>
						</ul></li>
					<li><a href="#">日志管理</a>
						<ul>
							<li><a href="#">创建日志</a>
							</li>
							<li><a href="#">我的日志</a>
							</li>
							<li><a href="#">共享日志</a>
							</li>
							<li><a href="#">日志分类</a>
							</li>
						</ul></li>
					<li><a href="#">工作报告</a>
						<ul>
							<li><a href="#">编写报告</a>
							</li>
							<li><a href="#">查询报告</a>
							</li>
						</ul></li>
					<li><a href="#">电子邮件</a>
						<ul>
							<li><a href="#">收件箱</a>
							</li>
							<li><a href="#">发件箱</a>
							</li>
							<li><a href="#">参数配置</a>
							</li>
						</ul></li>
					<li><a href="#">消息管理</a>
						<ul>
							<li><a href="#">发送消息</a>
							</li>
							<li><a href="#">已接收消息</a>
							</li>
							<li><a href="#">已发送消息</a>
							</li>
							<li><a href="#">已删除消息</a>
							</li>
						</ul></li>
					<li><a href="#">手机短信</a>
						<ul>
							<li><a href="#">发送手机短信</a>
							</li>
							<li><a href="#">已接收短信</a>
							</li>
							<li><a href="#">已发送短信</a>
							</li>
							<li><a href="#">短信网关配置</a>
							</li>
						</ul></li>
					<li><a href="#">通信录</a>
						<ul>
							<li><a href="#">私人通信录</a>
							</li>
							<li><a href="#">公共通信录</a>
							</li>
						</ul></li>
					<li><a href="#">网络硬盘</a>
						<ul>
							<li><a href="#">公共空间</a>
							</li>
							<li><a href="#">私人空间</a>
							</li>
						</ul></li>
					<li><a href="#">个人设置</a>
						<ul>
							<li><a href="#">个人信息</a>
							</li>
							<li><a href="#">修改密码</a>
							</li>
							<li><a href="#">工作状态</a>
							</li>
							<li><a href="#">在线状态</a>
							</li>
						</ul></li>
				</ul></li>
			<li><a href="#">工作流</a>
				<ul>
					<li><a href="#">表单定义</a>
						<ul>
							<li><a href="#">创建新的表单</a>
							</li>
							<li><a href="#">查询已有表单</a>
							</li>
						</ul></li>
					<li><a href="#">流程定义</a>
						<ul>
							<li><a href="#">创建新的流程定义</a>
							</li>
							<li><a href="#">查询已有流程定义</a>
							</li>
						</ul></li>
					<li><a href="#">任务管理</a>
						<ul>
							<li><a href="#">新建任务</a>
							</li>
							<li><a href="#">我的任务</a>
							</li>
							<li><a href="#">待办任务</a>
							</li>
							<li><a href="#">已办任务</a>
							</li>
						</ul></li>
				</ul></li>
			<li><a href="#">行政办公</a>
				<ul>
					<li><a href="#">公告通知</a>
						<ul>
							<li><a href="#">发布公告</a>
							</li>
							<li><a href="#">查阅历史公告</a>
							</li>
							<li><a href="#">公告分类</a>
							</li>
						</ul></li>
					<li><a href="#">会议管理</a>
					</li>
					<li><a href="#">车辆管理</a>
					</li>
					<li><a href="#">图书管理</a>
					</li>
					<li><a href="#">设备管理</a>
					</li>
					<li><a href="#">用品管理</a>
					</li>
				</ul></li>
			<li><a href="#">系统管理</a>
				<ul>
					<li><a href="#">组织机构管理</a>
						<ul>
							<li><a href="system/company.action?method:saveInput">单位/公司信息设置</a>
							</li>
							<li><a href="system/party.action">机构/部门设置</a>
							</li>
							<!-- <li><a href="#">岗位/职位设置</a></li> -->
							<li><a href="system/person.action">人员管理</a>
							</li>
						</ul></li>
					<li><a href="#">权限管理</a>
						<ul>
							<li><a href="system/user.action">用户管理</a>
							</li>
							<li><a href="system/role.action">角色管理</a>
							</li>
							<li><a href="system/menu.action">菜单管理</a>
							</li>
							<li><a href="system/resource.action">资源管理</a>
							</li>
							<li><a href="system/acl!userAuthIndex.action">用户授权</a>
							</li>
							<li><a href="system/acl!roleAuthIndex.action">角色授权</a>
							</li>
							<li><a href="system/acl!partyAuthIndex.action">部门/岗位授权</a>
							</li>
						</ul></li>
				</ul></li>
			<li><a href="#">档案管理</a>
				<ul>
					<li><a href="#">卷库分类</a>
						<ul>
							<li><a href="#">创建分类</a>
							</li>
							<li><a href="#">查询已有分类</a>
							</li>
						</ul></li>
					<li><a href="#">案卷分类</a>
						<ul>
							<li><a href="#">创建新的分类</a>
							</li>
							<li><a href="#">查询已有案卷</a>
							</li>
						</ul></li>
					<li><a href="#">档案管理</a></li>
					<li><a href="#">档案查阅</a>
						<ul>
							<li><a href="#">申请档案查阅</a>
							</li>
							<li><a href="#">审批档案查阅申请</a>
							</li>
							<li><a href="#">查阅档案</a>
							</li>
						</ul></li>
				</ul></li>
			<li><a href="#">内部论坛</a>
				<ul>
					<li><a href="#">板块交流</a>
						<ul>
							<li><a href="#">Java技术园地</a>
							</li>
							<li><a href="#">灌水专区</a>
							</li>
						</ul></li>
					<li><a href="#">论坛设置</a></li>
				</ul></li>
			<li><a href="#">知识管理</a>
				<ul>
					<li><a href="#">知识分类</a>
						<ul>
							<li><a href="#">创建新的分类</a>
							</li>
						</ul></li>
					<li><a href="#">知识维护</a>
						<ul>
							<li><a href="#">创建新的知识</a>
							</li>
							<li><a href="#">查询已有知识</a>
							</li>
						</ul></li>
				</ul></li>
		</ul>
	</div>
</body>
</html>

