class StarAverage extends HTMLElement {
	
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
	      
		}
		
		highlight (index) {
			this.stars.forEach((star, i) => {
				  if (i < index) {
					  star.setAttribute("name","star");
				  } else {
					  star.setAttribute("name","star-outline");
				  }
			  });
		}
	
	constructor () {
		super();
		
		this.number = this.number;
		
		this.highlight(document.getElementById('media').value);
		
	}
}

//function getPuntuacionMedia() {
//
//	var idProducto = document.getElementById('idProducto').value;
//
//	var token = $("meta[name='_csrf']").attr("content");
//	var header = $("meta[name='_csrf_header']").attr("content");
//	$(document).ajaxSend(function(e, xhr, options) {
//		xhr.setRequestHeader(header, token);
//	});
//	
//	$.get('http://localhost:8080/TerraShop/producto/getPuntuacionMedia/'
//			+ idProducto).done(function(data){
//	    console.log(data);
//		return data;
//	});
//
//}

customElements.define('x-star-average', StarAverage);