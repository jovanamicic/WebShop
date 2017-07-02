function fillTable(data, table){
	$("#tableData tr").remove();
	var list = data == null ? [] : (data instanceof Array ? data
			: [ data ]);
	$.each(list, function(index, p) {
		var row = table.insertRow(-1);
		row.style.cursor = "pointer";
		row.classList.add('tableHover');

		var id = row.insertCell(0);
		var originalPrice = row.insertCell(1);
		var finalPrice = row.insertCell(2);
		var discount = row.insertCell(3);
		var accept = row.insertCell(4);
		var cancel = row.insertCell(5);
		
		id.innerHTML = p.id;
		originalPrice.innerHTML = p.originalPrice;
		finalPrice.innerHTML = p.finalPrice;
		discount.innerHTML = p.discount;
		accept.innerHTML = "<a class=\"icon fa-check\"></a>";
		cancel.innerHTML = "<a class=\"icon fa-times\" onClick=\"cancel("+p.id+")\"></a>";
	});
}

function fillTableNoAcceptCancel(data, table){
	$("#tableData tr").remove();
	var list = data == null ? [] : (data instanceof Array ? data
			: [ data ]);
	$.each(list, function(index, p) {
		var row = table.insertRow(-1);
		row.style.cursor = "pointer";
		row.classList.add('tableHover');

		var id = row.insertCell(0);
		var originalPrice = row.insertCell(1);
		var finalPrice = row.insertCell(2);
		var discount = row.insertCell(3);
		var accept = row.insertCell(4);
		var cancel = row.insertCell(5);
		
		id.innerHTML = p.id;
		originalPrice.innerHTML = p.originalPrice;
		finalPrice.innerHTML = p.finalPrice;
		discount.innerHTML = p.discount;
	});
}

function cancel(id){
	$.ajax({
		type : "PUT",
		url : "/bills/cancel/"+id,
		success : function(data) {
			window.location.reload();
		},
		error : function(e) {
			console.log("Error");
		}
	});
}

function showFBills(){
	document.getElementById("oBtn").className = "button fit";
	document.getElementById("fBtn").className = "button special fit";
	document.getElementById("cBtn").className = "button fit";
	
	var table = document.getElementById("tableData");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/bills/status/finished",
		success : function(data) {
			fillTableNoAcceptCancel(data, table);
		},
		error : function(e) {
			console.log("Error");
		}
	});
}

function showCBills(){
	document.getElementById("oBtn").className = "button fit";
	document.getElementById("fBtn").className = "button fit";
	document.getElementById("cBtn").className = "button special fit";
	
	var table = document.getElementById("tableData");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/bills/status/cancelled",
		success : function(data) {
			fillTableNoAcceptCancel(data, table);
		},
		error : function(e) {
			console.log("Error");
		}
	});
}

function showOBills(){
	document.getElementById("oBtn").className = "button special fit";
	document.getElementById("fBtn").className = "button fit";
	document.getElementById("cBtn").className = "button fit";
	
	var table = document.getElementById("tableData");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/bills/status/ordered",
		success : function(data) {
			fillTable(data, table);
		},
		error : function(e) {
			console.log("Error");
		}
	});
}