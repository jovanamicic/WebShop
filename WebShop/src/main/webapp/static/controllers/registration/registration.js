function JSONRegister(name,surname, username, password, address) {
	return JSON.stringify({
		"name" : name,
		"surname" : surname,
		"username" : username,
		"password" : password,
		"address" : address
	});
}


function register(){
	var name = $('#name').val();
	var surname = $("#surname").val();
	var username = $('#username').val();
	var password = $("#password").val();
	var address = $("#address").val();
	$.ajax({
			type : "POST",
			contentType: "application/json",
			url : "/customers",
			data : JSONRegister(name,surname, username, password, address),
			success : function() {
				window.location.href = "index.html";
			},
			error : function(e) {
				console.log("Error");
			} 
		});
}