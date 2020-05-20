$(function() {
	$('#editarForm').ajaxForm(function() {

		console.log("hola");
		var idProducto = document.getElementById('idProducto').value;
		var nuevoNombre = document.getElementById('nombre').value;
		var nuevoPrecio = document.getElementById('precio').value;
		var nuevoStock = document.getElementById('stock').value;
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});

		$.ajax({
					url : "http://localhost:8080/TerraShop/producto/editar/"
							+ idProducto,
					type : "POST",
					data : $("#editarForm").serialize(),
					success : function(response) {
						console.log("Producto " + nuevoNombre + " actualizado");
					}
				});
	});
});