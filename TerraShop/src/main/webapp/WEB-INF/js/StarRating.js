class StarRating extends HTMLElement {
	
	get value () {
		return this.getAttribute('value') || 0;
	}
	
	set value (val) {
		this.setAttribute('value', val);
		this.highlight(this.value - 1);
	}
	
	get number () {
	  return this.getAttribute('number') || 5;
	}
	
	set number (val) {
	  this.setAttribute('number', val);
	  
      this.stars = [];
	
	  while (this.firstChild) {
	      this.removeChild(this.firstChild);
	  }
	
	  for (let i = 0; i < this.number; i++) {
	      let s = document.createElement('ion-icon');
	      s.setAttribute("name","star-outline");
	      this.appendChild(s);
	      this.stars.push(s);
	  }
      
      this.value = this.value;
	}
	
	highlight (index) {
	  this.stars.forEach((star, i) => {
		  if (i <= index) {
			  star.setAttribute("name","star");			  
		  } else {
			  star.setAttribute("name","star-outline");
		  }
	  });
	}
	
	constructor () {
        super();
        
        this.number = this.number;
        
      this.addEventListener('mousemove', e => {
      let box = this.getBoundingClientRect(),
      	starIndex = Math.floor((e.pageX - box.left) / box.width * this.stars.length);

      	this.highlight(starIndex);
      });
      
	  this.addEventListener('mouseout', () => {
		  this.value = this.value;
	  });
	  
	    this.addEventListener('click', e => {
		    let box = this.getBoundingClientRect(),
		        starIndex = Math.floor((e.pageX - box.left) / box.width * this.stars.length);
		
		    this.value = starIndex + 1;
		
		    let rateEvent = new Event('rate');
		    this.dispatchEvent(rateEvent);
		    
		    enviarPuntuacion(this.value);
	    });
        
        
	}
}

function enviarPuntuacion(puntuacion) {

	var idProducto = document.getElementById('idProducto').value;

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	$.ajax({
				url : "http://localhost:8080/TerraShop/producto/enviarPuntuacion/"
						+ idProducto + "/" + puntuacion,
				type : "POST",
				success : function(response) {
				}
			});

}

customElements.define('x-star-rating', StarRating);
