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

function find(){
	var search = $('#search').val();
	var minPrice = $('#minPrice').val();
	var maxPrice = $('#maxPrice').val();
	var category = $('#category').val();
	var table = document.getElementById("productsTable");
	if (search != ""){
		$.ajax({
			type : "GET",
			dataType : "json",
			url : "/products/"+search,
			success : function(data) {
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

					var name = row.insertCell(0);
					var category = row.insertCell(1);
					var price = row.insertCell(2);
					var stock = row.insertCell(3);
					var buy = row.insertCell(4);
					var id = row.insertCell(5);
					
					name.innerHTML = p.name;
					category.innerHTML = p.category.name;
					price.innerHTML = p.price;
					stock.innerHTML = p.stock;
					buy.innerHTML = "<a href=\"#\" class=\"icon fa-shopping-cart\"></a>"
					id.innerHTML = p.id;
					id.style.visibility = 'hidden';
				});

			},
			error : function(e) {
				document.getElementById("errorMsg").style.display = "inline";
				$('#search').val("");
			}
		});
	}
	else if (minPrice != "" && maxPrice != ""){
		console.log("prince");
	}
	else if (category != ""){
		console.log("cat");
	}
}