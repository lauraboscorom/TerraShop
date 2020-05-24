var stock = document.getElementById('stock').value;

$(function() {
	$('#comprarForm').ajaxForm(function() {
		comprarProducto();
	});
});

function comprarProducto() {

	var idProducto = document.getElementById('idProducto').value;
	var seleccionUnidades = document.getElementById('seleccionUnidades').value;
	var spanStockProducto = document.getElementById('spanStockProducto');
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	$.ajax({
				url : "http://localhost:8080/TerraShop/producto/comprar/"
						+ idProducto,
				type : "POST",
				data : $("#comprarForm").serialize(),
				success : function(response) {
					while(spanStockProducto.firstChild) {
						spanStockProducto.removeChild(spanStockProducto.firstChild);
					}
					stock -= seleccionUnidades;
					spanStockProducto.appendChild(document.createTextNode(stock));
				},
			    error: function() { 
			        alert("Lo sentimos. No queda suficiente stock de este producto para comprar tantas unidades.");
			    }
			});

}