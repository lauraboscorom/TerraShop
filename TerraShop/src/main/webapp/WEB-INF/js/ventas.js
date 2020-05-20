//th:href="@{/venta/devolver/{idVenta}/{idProducto}(idVenta=${venta.idVenta},idProducto=${venta.lineasDC[0].producto.idProducto})}"

$('#devolverEnlace').click(function(){
	devolverProducto();
});

function devolverProducto() {
	
}