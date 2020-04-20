var profesores = new Bloodhound(
		{
			datumTokenizer : Bloodhound.tokenizers.obj.whitespace('name'),
			queryTokenizer : Bloodhound.tokenizers.whitespace,
			remote : {
				url : "http://localhost:8080/PruebaSpring-AuthenticationAuthoritation/producto/search/%QUERY",
				wildcard : '%QUERY'
			}
		});

$('.typeahead').typeahead({
	minLength : 1,
	highlight : true
}, {
	name : 'profesores',
	display : 'username',
	source : profesores
}).on("typeahead:select", function(e, profesor) {

	matricularProfesor(profesor);
});

function desmatricularProfesor() {

	var obj = $(this);
	var idProfesor = $(this).closest("tr") // Finds the closest row <tr>
	.find("#idTd") // Gets a descendent with class="nr"
	.text();

	var idModulo = document.getElementById('idModulo').value;

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	$.ajax({
		url : "http://localhost:8080/PruebaSpring-AuthenticationAuthoritation/modulo/desmatricularProfesor/"
				+ idModulo + "/" + idProfesor,
		contentType : "application/json; charset=utf-8",
		method : "DELETE",
		success : function(response) {

			$(obj).closest("tr").remove();
		},
		error : function(xhr, status, error) {
			var aviso = ""
					+ "<div class='alert alert-danger' role='alert'>"
					+ "El profesor ya no imparte este módulo"
					+ "</div>" + "";

			$('#aviso').html(aviso);
		}
	});
};	

function matricularProfesor (profesorDto){
	
	var idModulo = document.getElementById('idModulo').value;

	var token = $("meta[name='_csrf']").attr("content");
	  var header = $("meta[name='_csrf_header']").attr("content");
	  $(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
	  });
	
	$.ajax({
      url: "http://localhost:8080/PruebaSpring-AuthenticationAuthoritation/modulo/matricularprofesor/"+idModulo,
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(profesorDto),
      type: "POST",
      success: function (response) {
    	  
    	var fila ="<tr sec:authorize='hasAuthority('ROL_ADMIN')'>"+
    	"<td style='display:none;' id='idTd'><span id='idSpan'>"+ profesorDto.idProfesor+"</span></td>"+
			"<td><span>"+ profesorDto.nombreProfesor + "</span></td>"+
			"<td><span>"+ profesorDto.apellidosProfesor +"</span></td>"+
			"<td><a href='/PruebaSpring-AuthenticationAuthoritation/profesor/perfil/"+profesorDto.idProfesor +"' class='btn btn-primary'><i class='fas fa-user'></i></a></td>"+
			"<td><a class='btn btn-danger borrar'><i class='fas fa-user-times'></i></a></td>"+
			"</tr>"
			
			$('#bodytabla').append(fila);
    	
    	var boton= document.getElementsByClassName('borrar');
    	for (var i = 0 ; i < boton.length; i++) {
    		boton[i].addEventListener('click' , desmatricularProfesor , false ) ; 
    	}
    	
    	$('#aviso').html("");
		},
		error: function(xhr, status, error) {
			
			var aviso ="" +
				"<div class='alert alert-danger' role='alert'>"+
				"El profesor ya imparte este módulo"+
  				"</div>";
			
			$('#aviso').html(aviso);
		}
  });
	
}



$(document).ready(function() {
	var boton= document.getElementsByClassName('.borrar');
	for (var i = 0 ; i < boton.length; i++) {
		boton[i].addEventListener('click' , desmatricularProfesor , false ) ; 
		}
});


//$('.borrar').click(
//function eliminarProfesor (){
//	
//	var obj = $(this);
//	var idProfesor = $(this).closest("tr")   // Finds the closest row <tr> 
//    .find("#idTd")     // Gets a descendent with class="nr"
//    .text();  
//
//	var idModulo = document.getElementById('idModulo').value;
//
//	var token = $("meta[name='_csrf']").attr("content");
//	  var header = $("meta[name='_csrf_header']").attr("content");
//	  $(document).ajaxSend(function(e, xhr, options) {
//	    xhr.setRequestHeader(header, token);
//	  });
//	$.ajax({
//      url: "http://localhost:8080/PruebaSpring-AuthenticationAuthoritation/modulo/eliminarprofesor/"+idModulo+"/"+idProfesor,
//      contentType: "application/json; charset=utf-8",
//      type: "GET",
//      success: function (response) {
//    	 removed=true;
//    	 
//    	 $(obj).closest("tr").remove();
//
//		},
//		error: function(xhr, status, error) {
//			
//			var aviso ="" +
//				"<div class='alert alert-danger' role='alert'>"+
//				"El profesor ya no imparte este módulo"+
//  				"</div>" +
//					"";
//			
//			$('#aviso').html(aviso);
//		}
//  });
//});

