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

function loadBill(){
	var id = getParam("id");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/bills/" + id,
		success : function(data) {
			$('#originalPrice').val(data.originalPrice);
			$('#finalPrice').val(data.finalPrice);
			$('#discount').val(data.discount);
			$('#spentPoints').val(data.spentPoints);
			$('#gainedPoints').val(data.gainedPoints);
			
			var ulItems = document.getElementById("itemsD");
			$.each(data.discountItems, function(index, p) {
				var li = document.createElement("li");
				li.innerHTML = "Discount <b>" + p.discount + "%</b> for product " + p.item;
				ulItems.appendChild(li);
			});
			
			var ulBill = document.getElementById("billD");
			$.each(data.discountBill, function(index, p) {
				var li = document.createElement("li");
				li.innerHTML = "Discount <b>" + p.discount + "%</b>.";
				ulBill.appendChild(li);
			});
		},
		error : function(e) {
			console.log("error");
		}
	});
}