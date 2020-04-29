function abrirProductoModal(id) {
	$.ajax({
		url: "/TerraShop/producto/editar/" + id,
		success: function(data) {
			$("#productoModalHolder").html(data);
			$("#productoModal").modal("show");
		}
	})
}

var productos = new Bloodhound(
		{
			datumTokenizer : Bloodhound.tokenizers.obj.whitespace('name'),
			queryTokenizer : Bloodhound.tokenizers.whitespace,
			remote : {
				url : "http://localhost:8080/TerraShop/producto/search/%QUERY",
				wildcard : '%QUERY'
			}
		});

$('.typeahead').typeahead({
	minLength : 1,
	highlight : true
}, {
	name : 'productos',
	display : 'nombre',
	source : productos
});