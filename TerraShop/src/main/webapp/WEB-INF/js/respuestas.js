function enviarRespuesta(idPregunta) {

	var idProducto = document.getElementById('idProducto').value;
	var idUsuario = document.getElementById('idUsuario').value;
	var respuesta = document.getElementById('respuesta' + idPregunta).value;
	var usuario = document.getElementById('usuario').value;

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	$.ajax({
		url : "http://localhost:8080/TerraShop/producto/enviarRespuesta/"
				+ idProducto + "/" + idPregunta,
		type : "POST",
		data : $("#respuestaForm" + idPregunta).serialize(),
		success : function(response) {
			var fila = "<div class='ml-5'><a href='/TerraShop/usuario/perfil/" + idUsuario
					+ "' class='card-link'>" + "<strong><span>" + usuario
					+ "</span></strong></a>" + "<p><span>\"" + respuesta
					+ "\"</span></p></div>"

			$('#respuestas' + idPregunta).append(fila);

		}
	});

}