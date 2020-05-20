$(function() {
	$('#preguntaForm').ajaxForm(function() {
		enviarPregunta();
	});
});

function enviarPregunta() {

	var idProducto = document.getElementById('idProducto').value;
	var idUsuario = document.getElementById('idUsuario').value;
	var usuario = document.getElementById('usuario').value;
	var pregunta = document.getElementById('pregunta').value;

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	$
			.ajax({
				url : "http://localhost:8080/TerraShop/producto/enviarPregunta/"
						+ idProducto,
				type : "POST",
				data : $("#preguntaForm").serialize(),
				success : function(response) {
					var fila = "<hr>"
							+ "<a href='/TerraShop/usuario/perfil/" + idUsuario + "' class='card-link'>"
							+ "<strong><span>" + usuario + "</span></strong></a>"
							+ "<p><span>\"" + pregunta + "\"</span></p>"
							+ "<form><fieldset><div class='form-group'><div class='input-group mb-3'>"
							+ "<textarea type='text' class='form-control mr-3' rows='1'	name='texto' id='respuesta'></textarea>"
							+ "<div class='input-group-append'><button type='submit' class='btn btn-info'>"
							+ "Responder <i class='fas fa-reply'></i></button></div>"
							+ "</div></div></fieldset></form>"
							
					$('#preguntas').append(fila);
				}
			});

}