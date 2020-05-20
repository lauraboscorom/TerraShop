function abrirProductoModal(id) {
	$.ajax({
		url: "/TerraShop/producto/editar/" + id,
		success: function(data) {
			$("#productoModalHolder").html(data);
			$("#productoModal").modal("show");
		}
	})
}