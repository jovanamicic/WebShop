function fillTable(data, table){
	$("#tableData tr").remove();
	var list = data == null ? [] : (data instanceof Array ? data
			: [ data ]);
	$.each(list, function(index, p) {
		var row = table.insertRow(-1);
		row.style.cursor = "pointer";
		row.classList.add('tableHover');

		var id = row.insertCell(0);
		var date = row.insertCell(1)
		var originalPrice = row.insertCell(2);
		var finalPrice = row.insertCell(3);
		var discount = row.insertCell(4);
		var status = row.insertCell(5);
		var info = row.insertCell(6);
		
		id.innerHTML = p.id;
		originalPrice.innerHTML = p.originalPrice;
		finalPrice.innerHTML = p.finalPrice;
		discount.innerHTML = p.discount;
		date.innerHTML = p.date;
		status.innerHTML = p.status;
		info.innerHTML = "<a class=\"icon fa fa-info-circle\" onClick=\"showBill("+p.id+")\"></a>";
	});
}

function showBill(id){
	window.location.href = "/bill.html?id="+id;
}

function loadBills(){
	var table = document.getElementById("tableData");
	var id = sessionStorage.getItem('user');
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/bills/customer/"+id,
		success : function(data) {
			console.log(data);
			fillTable(data, table);
		},
		error : function(e) {
			console.log("Error");
		}
	});
}