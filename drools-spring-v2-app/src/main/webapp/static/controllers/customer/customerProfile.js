function getCustomer() {
	var username = sessionStorage.getItem('user');
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/customers/" + username,
		success : function(data) {
			console.log(data);
			document.getElementById("nameSurname").innerHTML = data.name + " " + data.surname;
			document.getElementById("username").innerHTML = data.username;
			document.getElementById("points").innerHTML = data.points;
			
			document.getElementById("address").innerHTML = data.address;
			document.getElementById("ccategory").innerHTML = data.ccategory + " customer";
			
			var date = new Date(data.regDate);
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
			document.getElementById("regDate").innerHTML = date;
			
		},
		error : function(e) {
			console.log("error");
		}
	});
}