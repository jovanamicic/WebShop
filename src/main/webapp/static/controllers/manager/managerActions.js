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

function loadActions(){
	var table = document.getElementById("tableBody");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/actions/all",
		success : function(data) {
			fillTable(data, table);
		},
		error : function(e) {
			console.log("Error");
		}
	});
}

function loadAction(){
	var id = getParam("id");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/actions/"+id,
		success : function(data) {
			$('#name').val(data.name);
			$('#id').val(data.id);
			$('#from').val(formatDate(data.fromDate));
			$('#to').val(formatDate(data.toDate));
			$('#discount').val(data.discount);
			for(i = 0; i < data.categories.length;i++){
				document.getElementById("category").options[data.categories[i].id].selected = true;
			}
			
		},
		error : function(e) {
			console.log("error");
		}
	});
}

function fillTable(data, table){
	$("#tableBody tr").remove();
	var list = data == null ? [] : (data instanceof Array ? data
			: [ data ]);
	$.each(list, function(index, a) {
		var row = table.insertRow(-1);
		row.style.cursor = "pointer";
		row.classList.add('tableHover');

		var id = row.insertCell(0);
		var name = row.insertCell(1);
		var from = row.insertCell(2);
		var to = row.insertCell(3);
		var discount = row.insertCell(4);
		var categories = row.insertCell(5);
		var edit = row.insertCell(6);
		
		id.innerHTML = a.id;
		name.innerHTML = a.name;
		from.innerHTML = formatDate(a.fromDate);
		to.innerHTML = formatDate(a.toDate);
		discount.innerHTML = a.discount + "%";
		edit.innerHTML = "<a href=\"managerEditAction.html?id="+a.id+"\"class=\"icon fa-pencil\"></a>";
		if(a.categories.length != 0){
			var cats = "";
			for (i=0; i<a.categories.length;i++){
				cats+= a.categories[i].name + ", "; 
			}
			categories.innerHTML = cats.substring(0, cats.length - 2);
		}
		else{
			categories.innerHTML = "No categories";
		}
	});
}

function formatDate(data){
	var date = new Date(data);
	var dd = date.getDate();
	var mm = date.getMonth() + 1;

	var yyyy = date.getFullYear();
	if(dd<10){
	    dd='0'+dd;
	} 
	if(mm<10){
	    mm='0'+mm;
	} 
	var date = dd+'-'+mm+'-'+yyyy;
	return date;
}

function  JSONNewAction(name,discount,from,to,categories) {
	return JSON.stringify({
		"name" : name,
		"discount" : discount,
		"from" : from,
		"to" : to,
		"categories" : categories
	});
}

function addNewAction(){
	var nameA = $('#name').val();
	var discount = $('#discount').val();
	var from = $('#from').val();
	var to = $('#to').val();
	var cId = $('#category').val();
	
	if (nameA == "" || discount == "" || from == "" || to == "" || category == ""){
		document.getElementById("error").style.display = "inline";
	}
	else{
		document.getElementById("error").style.display = "none";
		var categories = [];
		for (i = 0; i < cId.length; i++){
			id = cId[i];
			name = document.getElementById('category').options[id].text
			var c = {
					"id" : id,
					"name" : name
			}
			categories.push(c);
		}
		$.ajax({
			type : "POST",
			contentType: "application/json",
			url : "/actions",
			data : JSONNewAction(nameA,discount,from,to,categories),
			success : function() {
				window.location.href = "managerActionEvents.html";
			},
			error : function(e) {
				console.log("Error");
			} 
		});
	}
}

function updateAction(){
	var nameA = $('#name').val();
	var discountA = $('#discount').val();
	var fromA = $('#from').val();
	var toA = $('#to').val();
	var cId = $('#category').val();
	var idA = getParam("id");
	
	if (nameA == "" || discountA == "" || fromA == "" || toA == "" || cId == ""){
		document.getElementById("error").style.display = "inline";
	}
	else{
		document.getElementById("error").style.display = "none";
		var categories = [];
		for (i = 0; i < cId.length; i++){
			id = cId[i];
			name = document.getElementById('category').options[id].text
			var c = {
					"id" : id,
					"name" : name
			}
			categories.push(c);
		}
		$.ajax({
			type : "PUT",
			contentType: "application/json",
			url : "/actions/"+idA,
			data : JSONNewAction(nameA,discountA,fromA,toA,categories),
			success : function() {
				window.location.href = "managerActionEvents.html";
			},
			error : function(e) {
				console.log("Error");
			} 
		});
	}

}


