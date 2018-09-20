<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic Panel - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="../easyui/eu/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/eu/themes/icon.css">
	<script type="text/javascript" src="../easyui/eu/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/eu/jquery.easyui.min.js"></script>
</head>
<body>
	<table id="dg" title="My Users" class="easyui-datagrid" style="width:550px;height:250px"
		url="/spm/user/all"
		toolbar="#toolbar"
		rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="firstname" width="50">First Name</th>
				<th field="lastname" width="50">Last Name</th>
				<th field="phone" width="50">Phone</th>
				<th field="email" width="100">Email</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
	</div>
	<!-- 编辑部分-->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
	<div class="ftitle">User Information</div>
	<form id="fm" method="post">
		<div class="fitem">
			<label>First Name:</label>
			<input name="firstname" class="easyui-validatebox" required="true">
		</div>
		<div class="fitem">
			<label>Last Name:</label>
			<input name="lastname" class="easyui-validatebox" required="true">
		</div>
		<div class="fitem">
			<label>Phone:</label>
			<input name="phone">
		</div>
		<div class="fitem">
			<label>Email:</label>
			<input name="email" class="easyui-validatebox" validType="email">
		</div>
	</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
</body>
<script>
	var _ctx = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/crud1/datagrid1.js"></script>
</html>
