function abrirEditarProductoModal(id) {
	$.ajax({
		url: "/TerraShop/producto/mostrarEditar/" + id,
		success: function(data) {
			$("#productoModalHolder").html(data);
			$("#productoModal").modal("show");
		}
	})
}


