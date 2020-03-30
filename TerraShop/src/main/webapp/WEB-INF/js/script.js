function abrirProductoModal(id) {
	$ajax({
		url: "/producto/" + id,
		success: function(data) {
			$("#productoModalHolder").html(data);
			$("#productoModal").modal("show");
		}
	})
}