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

function loadPCategories(){
	var table = document.getElementById("tableBody");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/productCategories/allPC",
		success : function(data) {
			fillTable(data, table);
		},
		error : function(e) {
			console.log("Error");
		}
	});
}

function loadAllPCategories(){
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/productCategories/allPC",
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

function loadPCategory(){
	var id = getParam("id");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/productCategories/"+id,
		success : function(data) {
			$('#name').val(data.name);
			$('#id').val(data.id);
			$('#maxDiscount').val(data.maxDiscount);
			document.getElementById("category").options.selectedIndex = data.category.id;
		},
		error : function(e) {
			console.log("error");
		}
	});
}

function JSONUpdatePC(name, maxDiscount, category) {
	return JSON.stringify({
		"name" : name,
		"maxDiscount" : maxDiscount,
		"category" : category,
	});
}

function updatePC(){
	var id = getParam("id");
	var name = $('#name').val();
	var maxDiscount = $('#maxDiscount').val();
	var category = $('#category').val();
	
	if (name == "" || maxDiscount == "" || category == ""){
		document.getElementById("errorUpdate").style.display = "inline";
	}
	else{
		document.getElementById("errorUpdate").style.display = "none";
		$.ajax({
			type : "PUT",
			contentType: "application/json",
			url : "/productCategories/"+id,
			data : JSONUpdatePC(name,maxDiscount, category),
			success : function() {
				window.location.href = "managerPCategories.html";
			},
			error : function(e) {
				console.log("Error");
			} 
		});
	}
	
}

function fillTable(data, table){
	$("#tableBody tr").remove();
	var list = data == null ? [] : (data instanceof Array ? data
			: [ data ]);
	$.each(list, function(index, p) {
		var row = table.insertRow(-1);
		row.style.cursor = "pointer";
		row.classList.add('tableHover');

		var id = row.insertCell(0);
		var name = row.insertCell(1);
		var category = row.insertCell(2);
		var maxDiscount = row.insertCell(3);
		var edit = row.insertCell(4);
		
		id.innerHTML = p.id;
		name.innerHTML = p.name;
		category.innerHTML = p.category ? p.category.name : "No category";
		maxDiscount.innerHTML = p.maxDiscount + "%";
		edit.innerHTML = "<a href=\"managerEditPCategory.html?id="+p.id+"\"class=\"icon fa-pencil\"></a>";
	});
}

function JSONNewPC(name, maxDiscount) {
	return JSON.stringify({
		"name" : name,
		"maxDiscount" : maxDiscount
	});
}

function addNewPC(){
	var name = $('#name').val();
	var maxDiscount = $('#maxDiscount').val();
	
	if (name == "" || maxDiscount == ""){
		document.getElementById("error").style.display = "inline";
	}
	else{
		document.getElementById("error").style.display = "none";
		$.ajax({
			type : "POST",
			contentType: "application/json",
			url : "/productCategories",
			data : JSONNewPC(name,maxDiscount),
			success : function() {
				window.location.href = "managerPCategories.html";
			},
			error : function(e) {
				console.log("Error");
			} 
		});
	}
}