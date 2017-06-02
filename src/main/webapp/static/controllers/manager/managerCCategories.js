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

function loadCCategories(){
	var table = document.getElementById("tableBody");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/customerCategories/all",
		success : function(data) {
			fillTable(data, table);
		},
		error : function(e) {
			console.log("Error");
		}
	});
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
		var edit = row.insertCell(2);
		
		id.innerHTML = p.id;
		name.innerHTML = p.name;
		edit.innerHTML = "<a href=\"managerEditCCategory.html?id="+ p.id +"\" class=\"icon fa-pencil\"></a>";
	});
}

function loadCCategory(){
	var id = getParam("id");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/customerCategories/"+id,
		success : function(data) {
			$('#name').val(data.name);
			$('#id').val(data.id);
			if (data.limits.length == 0){
				document.getElementById("noLimits").style.display = "inline";
			}
			else{
				document.getElementById("noLimits").style.display = "none";
				loadLimits(data.limits);
			}
			
		},
		error : function(e) {
			console.log("error");
		}
	});
}

var list = [];
function loadLimits(data){
	list = data == null ? [] : (data instanceof Array ? data
			: [ data ]);
	$.each(list, function(index, l) {
		$("#limitsList").append(
				"<li id=\""+l.id+"\">&nbsp;<b>Range:</b> " + l.minimum + " - "
						+ l.maximum + " <span style=\"text-align:center;\">&emsp;&emsp;<b>Bonus:</b> "
						+ l.discount + "%</span><a class=\"icon button small\" style=\"float:right;\" onClick=\"removeLimit("+l.id+")\">Remove</a></li>");
	});
	
}

function hideAddLimitForm(){
	document.getElementById("addLimitForm").style.display = "none";
	document.getElementById("saveCCBtns").style.display = "block";
}

function removeLimit(id){
	for (i = 0; i < list.length; i++) { 
	    if (list[i].id == id){
	    	if (i > -1) {
	    	    list.splice(i, 1);
	    	}
	    }
	}
	$("#"+id).remove();
}

function addLimit(){
	document.getElementById("addLimitForm").style.display = "block";
	document.getElementById("saveCCBtns").style.display = "none";
}

function addLimitInList(){
	var min = $("#from").val();
	var max = $("#to").val();
	var discount = $("#discount").val();
	var l = {
			id : -1,
			minimum : min,
			maximum : max,
			discount : discount
	};
	list.push(l);
	hideAddLimitForm();
	$("#limitsList").append(
			"<li>&nbsp;<b>Range:</b> " + l.minimum + " - "
					+ l.maximum + " <span style=\"text-align:center;\">&emsp;&emsp;<b>Bonus:</b> "
					+ l.discount + "%</span><a class=\"icon button small\" style=\"float:right;\" onClick=\"removeLimit("+l.id+")\">Remove</a></li>");

}


function updateCC(){
	var id = getParam("id");
	$.ajax({
		type : "PUT",
		contentType : "application/json",
		url : "/customerCategories/"+id,
		data: JSON.stringify(list),
		success : function() {
			window.location.href = "managerCCategories.html";
		},
		error : function(e) {
			console.log("error");
		}
	});
}