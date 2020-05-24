function devolverVenta(idVenta, idProducto) {
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
	$.ajax({
		url : "http://localhost:8080/TerraShop/venta/devolver/"
			+ idVenta + "/" + idProducto,
		contentType : "application/json; charset=utf-8",
		method : "DELETE",
		success : function(response) {
			console.log("devuelto");
		},
		error : function(xhr, status, error) {
			console.log("no devuelto");
		}
	});
};