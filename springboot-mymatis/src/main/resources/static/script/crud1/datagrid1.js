function newUser() {
	$('#dlg').dialog('open').dialog('setTitle', 'New User');
	$('#fm').form('clear');
	url = _ctx + '/user/save';
}
function editUser() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
		$('#fm').form('load', row);
		url = _ctx + '/user/save?id=' + row.id;
	}
}
function saveUser() {
	$('#fm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.errorMsg) {
				$.messager.show({
					title : 'Error',
					msg : result.errorMsg
				});
			} else {
				$('#dlg').dialog('close');
				$('#dg').datagrid('reload');
			}
		}
	});
}