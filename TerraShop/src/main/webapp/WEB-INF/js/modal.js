function abrirEditarProductoModal(id) {
	$
			.ajax({
				url : "/TerraShop/producto/mostrarEditar/" + id,
				success : function(data) {
					$("#productoModalHolder").html(data);
					$("#productoModal").modal("show");

					var button = "<button type='button' class='btn btn-info w-100 mt-3' "
							+ "onclick=\"javascript:abrirEditarProductoModal('"
							+ id
							+ "');\">"
							+ "EDITAR <i class='fas fa-edit'></i>"

					$('#productoModalHolder').append(button);

					var token = $("meta[name='_csrf']").attr("content");
					var header = $("meta[name='_csrf_header']").attr("content");
					$(document).ajaxSend(function(e, xhr, options) {
						xhr.setRequestHeader(header, token);
					});

					$(function() {
						$('#editarForm')
								.ajaxForm(
										function() {

											var idProducto = document
													.getElementById('idProducto').value;
											var nuevoNombre = document
													.getElementById('nuevoNombre').value;
											var nuevoPrecio = document
													.getElementById('nuevoPrecio').value;
											var nuevoStock = document
													.getElementById('nuevoStock').value;
											var spanNombreProducto = document
													.getElementById('spanNombreProducto');
											var spanPrecioProducto = document
													.getElementById('spanPrecioProducto');
											var spanStockProducto = document
													.getElementById('spanStockProducto');

											var token = $("meta[name='_csrf']")
													.attr("content");
											var header = $(
													"meta[name='_csrf_header']")
													.attr("content");
											$(document).ajaxSend(
													function(e, xhr, options) {
														xhr.setRequestHeader(
																header, token);
													});

											$
													.ajax({
														url : "http://localhost:8080/TerraShop/producto/editar/"
																+ idProducto,
														type : "POST",
														data : $("#editarForm")
																.serialize(),
														success : function(
																response) {
															while (spanNombreProducto.firstChild) {
																spanNombreProducto
																		.removeChild(spanNombreProducto.firstChild);
															}
															spanNombreProducto
																	.appendChild(document
																			.createTextNode(nuevoNombre));

															while (spanPrecioProducto.firstChild) {
																spanPrecioProducto
																		.removeChild(spanPrecioProducto.firstChild);
															}
															spanPrecioProducto
																	.appendChild(document
																			.createTextNode(nuevoPrecio));
															
															while (spanStockProducto.firstChild) {
																spanStockProducto
																		.removeChild(spanStockProducto.firstChild);
															}
															spanStockProducto
																	.appendChild(document
																			.createTextNode(nuevoStock));
															
															$('#productoModal').modal('hide')
														}
													});
										});
					});
				}
			})
}