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

function loadProduct(){
	var id = getParam("id");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/products/find/"+id,
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

function showTotalPrice(){
	var q = $('#quantity').val();
	var stock = $("#stock").val();
	if (q <= stock){
		document.getElementById("error").style.display = "none";
		document.getElementById("totalPrice").style.display = "inline";
		document.getElementById("total").innerHTML = q * $('#price').val();
	}
	else {
		document.getElementById("error").style.display = "inline";
		document.getElementById("totalPrice").style.display = "none";
	}
}

function addToCart(){
	
}