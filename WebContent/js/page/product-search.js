$(function() {
	$("#select_Id").select2({
		allowClear : true,
		placeholder : "请输入名称 按ENTER",
		minimumInputLength : 2,
		ajax : {
			type : "post",
			dataType : "json",
			url : "getProductAsName.htm",
			quietMillis : 500,
			data : function(term, page) {
				return {
					name : term,
					page_limit : 10,
					apikey : "NoUse"
				};
			},
			results : function(data, page) {
				return {
					results : data.products
				};
			}
		},
		formatResult : movieFormatResult,
		formatSelection : movieFormatSelection
	});
	function movieFormatResult(products) {
		return products.name;
	}
	function movieFormatSelection(products, container) {
		var id = products.id || 0;
		var name = products.name;
		name = name.substring(0,8);
		var classifyName = products.classifyName;
		classifyName = classifyName.substring(0,8);
		var tab = "<table class='table table-striped table-bordered table-condensed'>"
				 +"<tr>"
				 +"<th>商品编号</th>"
				 +"<th>商品名称</th>"
				 +"<th>所属分类</th>"
				 +"<th>商品状态</th>"
				 +"<th>原价</th>"
				 +"<th>现价</th>"
				 +"<th>库存</th>"
				 +"<th width='15%'>操作</th>"
				 +"</tr>"
				 +"<tr>"
				 +" <td width='10%'>"+products.no+"</td>"
				 +"<td title='"+products.name+"'>"+name+"</td>"
		+"<td title='"+products.classifyName+"'>"+classifyName+"</td>"
		+"<td>"+products.statInfo+"</td>"
				    +"<td>￥"+products.priceOld+"</td>"
				    +" <td>￥"+products.priceNow+"</td>"
				    +" <td>"+products.inventoryInfo+"</td>"
				    +" <td>"
				    +"<a href='javascript:edit("+id+");' title='点击修改' class='btn btn-mini btn-danger'>修改</a>&nbsp;"
				    +"<a href='javascript:detail("+id+");' title='点击查看明细' class='btn btn-mini btn-danger'>明细</a>"
				    +"</td>"
				    +" </tr>"
				    $("#pruductInfo").html(tab);
		return products.name;
	}
});
function alertDanger(title) {
	$("body").alert({
		type : "danger",
		title : title || "提示",
		content : "参数出错 请返回重试",
		btntext : "确定",
		modal : true,
		draggabled : false,
		even : "click"
	})
	return;
}
function edit(id) {
	if (id <= 0) {
		alertDanger();
		return;
	}
	location.href = "editProduct.htm?id=" + id + "&search=search";
}
function detail(id) {
	if (id <= 0) {
		alertDanger();
		return;
	}
	location.href = "detailProduct.htm?id=" + id;
	return;
}