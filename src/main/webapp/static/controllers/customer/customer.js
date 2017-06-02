function loadCategories(){
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/productCategories/all",
		success : function(data) {
			var comboBox = document.getElementById("category");
			for (i = 0; i < data.length; i++) { 
				var option = document.createElement("option");
			    option.text = data[i].name;
			    option.value = data[i].id;
			    comboBox.add(option);
			}
		},
		error : function(e) {
			console.log("error");
		}
	});
}

function fillTable(data, table){
	document.getElementById("errorMsg").style.display = "none";
	document.getElementById("results").style.display = "block";
	$('#search').val("");
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
		var buy = row.insertCell(5);
		
		id.innerHTML = p.id;
		name.innerHTML = p.name;
		category.innerHTML = p.category.name;
		price.innerHTML = p.price;
		stock.innerHTML = p.stock;
		buy.innerHTML = "<a href=\"#\" id=\""+p.id+"\" class=\"icon fa-shopping-cart\"></a>"
	});
}

function JSONPriceSearch(minPrice, maxPrice) {
	return JSON.stringify({
		"minPrice" : minPrice,
		"maxPrice" : maxPrice
	});
}


function find(){
	var search = $('#search').val();
	var minPrice = $('#minPrice').val();
	var maxPrice = $('#maxPrice').val();
	var category = $('#category').val();
	var table = document.getElementById("tableData");
	
	
	if (search != ""){
		$.ajax({
			type : "GET",
			dataType : "json",
			url : "/products/"+search,
			success : function(data) {
				fillTable(data, table);
			},
			error : function(e) {
				document.getElementById("errorMsg").style.display = "inline";
				$('#search').val("");
			}
		});
	}
	else if (minPrice != "" && maxPrice != ""){
		$.ajax({
			type : "POST",
			contentType : "application/json",
			dataType : "json",
			url : "/products/price",
			data: JSONPriceSearch(minPrice, maxPrice),
			success : function(data) {
				fillTable(data, table);
			},
			error : function(e) {
				document.getElementById("errorMsg").style.display = "inline";
				$('#search').val("");
			}
		});
	}
	else if (category != ""){
		$.ajax({
			type : "GET",
			dataType : "json",
			url : "/products/category/"+category,
			success : function(data) {
				fillTable(data, table);
			},
			error : function(e) {
				document.getElementById("errorMsg").style.display = "inline";
				$('#search').val("");
			}
		});
	}
}