function getParam(variable) {
	var query = window.location.search.substring(1);
	var vars = query.split("&");
	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split("=");
		if (pair[0] == variable) {
			return pair[1];
		}
	}
	return (false);
}

function loadProduct() {
	var id = getParam("id");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/products/find/" + id,
		success : function(data) {
			$('#id').val(data.id);
			$('#name').val(data.name);
			$('#price').val(data.price);
			$('#category').val(data.category.name);
			$('#stock').val(data.stock);
		},
		error : function(e) {
			console.log("error");
		}
	});
}

function showTotalPrice() {
	var q = parseInt($('#quantity').val());
	var stock = parseInt($("#stock").val());
	if (q <= stock) {
		document.getElementById("error").style.display = "none";
		document.getElementById("totalPrice").style.display = "inline";
		document.getElementById("total").innerHTML = q * $('#price').val();
	} else {
		document.getElementById("error").style.display = "inline";
		document.getElementById("totalPrice").style.display = "none";
	}
}

function addToCart() {
	cart = JSON.parse(sessionStorage.getItem('cart'));
	id = $('#id').val();
	quantity = parseInt($('#quantity').val());
	if (!cart) {
		cart = [];
		item = {
			"id" : id,
			"quantity" : quantity,
			"name" : $('#name').val(),
			"price" : $('#price').val(),
			"total" : $('#price').val() * $('#quantity').val()
		}
		cart.push(item);
	} else {
		for (i = 0; i < cart.length; i++) {
			if (cart[i].id == $('#id').val()) {
				cart[i].quantity = cart[i].quantity + quantity;
				cart[i].total = cart[i].total + (quantity * cart[i].price);
			} else {
				item = {
					"id" : id,
					"quantity" : quantity,
					"name" : $('#name').val(),
					"price" : $('#price').val(),
					"total" : $('#price').val() * quantity
				}

				cart.push(item);
			}
		}
	}
	sessionStorage.setItem('cart', JSON.stringify(cart));
}

function fillTable(data, table) {
	$("#tableBody tr").remove();
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, p) {
		var row = table.insertRow(-1);
		row.style.cursor = "pointer";
		row.classList.add('tableHover');

		var id = row.insertCell(0);
		var name = row.insertCell(1);
		var quantity = row.insertCell(2);
		var price = row.insertCell(3);
		var total = row.insertCell(4);

		id.innerHTML = p.id;
		name.innerHTML = p.name;
		quantity.innerHTML = p.quantity;
		price.innerHTML = p.price;
		total.innerHTML = p.total;
	});
}

function loadCart() {
	cart = JSON.parse(sessionStorage.getItem('cart'));
	if (!cart) {
		document.getElementById("noItems").style.display = "block";
		document.getElementById("table").style.display = "none";
	} else {
		var table = document.getElementById("tableBody");
		fillTable(cart, table);
		document.getElementById("noItems").style.display = "none";
		document.getElementById("table").style.display = "block";
	}
}

function checkOut(){
	cart = JSON.parse(sessionStorage.getItem('cart'));
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/bills/",
		data: JSON.stringify(cart),
		success : function(data) {
			console.log("OK");
		},
		error : function(e) {
			console.log("error");
		}
	});
}