var productos = new Bloodhound({
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
}).on("typeahead:select", function(e, productoDto) {
	mostrarProducto(productoDto);
});

function mostrarProducto(productoDto) {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	$.ajax({
		url : "http://localhost:8080/TerraShop/producto/perfilAjax/"
				+ productoDto.idProducto,
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(productoDto),
		type : "POST",
		success : function(response) {
			location.href = "perfil/" + productoDto.idProducto;
		}
	});

}