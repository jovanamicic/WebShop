function fillTable(data, table){
	$("#tableData tr").remove();
	var list = data == null ? [] : (data instanceof Array ? data
			: [ data ]);
	$.each(list, function(index, p) {
		var row = table.insertRow(-1);
		row.style.cursor = "pointer";
		row.classList.add('tableHover');

		var id = row.insertCell(0);
		var date = row.insertCell(1);
		var originalPrice = row.insertCell(2);
		var finalPrice = row.insertCell(3);
		var discount = row.insertCell(4);
		var status = row.insertCell(5)
		var accept = row.insertCell(6);
		var cancel = row.insertCell(7);
		
		id.innerHTML = p.id;
		date.innerHTML = p.date;
		originalPrice.innerHTML = p.originalPrice;
		finalPrice.innerHTML = p.finalPrice;
		discount.innerHTML = p.discount;
		status.innerHTML = p.status;
		accept.innerHTML = "<a class=\"icon fa-check\" onClick=\"accept("+p.id+")\"></a>";
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
		var date = row.insertCell(1);
		var originalPrice = row.insertCell(2);
		var finalPrice = row.insertCell(3);
		var discount = row.insertCell(4);
		var status = row.insertCell(5)
		var accept = row.insertCell(6);
		var cancel = row.insertCell(7);
		
		id.innerHTML = p.id;
		date.innerHTML = p.date;
		originalPrice.innerHTML = p.originalPrice;
		finalPrice.innerHTML = p.finalPrice;
		discount.innerHTML = p.discount;
		status.innerHTML = p.status;
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

function accept(id){
	$.ajax({
		type : "PUT",
		url : "/bills/accept/"+id,
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