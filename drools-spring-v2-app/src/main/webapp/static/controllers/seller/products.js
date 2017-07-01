function fillTable(data, table){
	$("#tableData tr").remove();
	var list = data == null ? [] : (data instanceof Array ? data
			: [ data ]);
	$.each(list, function(index, p) {
		var row = table.insertRow(-1);
		row.style.cursor = "pointer";
		row.classList.add('tableHover');

		var id = row.insertCell(0);
		var name = row.insertCell(1);
		var category = row.insertCell(2);
		var price = row.insertCell(3);
		var stock = row.insertCell(4);
		var minimumInStock = row.insertCell(5);
		
		id.innerHTML = p.id;
		name.innerHTML = p.name;
		category.innerHTML = p.category.name;
		price.innerHTML = p.price;
		stock.innerHTML = p.stock;
		minimumInStock.innerHTML = p.minimumInStock;
	});
}

function loadProducts(){
	var table = document.getElementById("tableData");
	
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/products/stock",
		success : function(data) {
			if (data.length != 0){
				fillTable(data, table);
				document.getElementById("table").style.display = "block";
				document.getElementById("error").style.display = "none";
			}
			else{
				document.getElementById("table").style.display = "none";
				document.getElementById("error").style.display = "block";
			}
		},
		error : function(e) {
			console.log("Error");
		}
	});
}

function refill(){
	$.ajax({
		type : "GET",
		url : "/products/refill",
		success : function(data) {
			window.location.reload();
		},
		error : function(e) {
			console.log("Error");
		}
	});
}