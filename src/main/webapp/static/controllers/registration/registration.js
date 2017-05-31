function JSONRegister(name,surname, username, password) {
	return JSON.stringify({
		"name" : name,
		"surname" : surname,
		"username" : username,
		"password" : password
	});
}


function register(){
	var name = $('#name').val();
	var surname = $("#surname").val();
	var username = $('#username').val();
	var password = $("#password").val();
	$.ajax({
			type : "POST",
			contentType: "application/json",
			url : "/customers",
			data : JSONRegister(name,surname, username, password),
			success : function() {
				window.location.href = "login.html";
			},
			error : function(e) {
				toastr.error("Error occured.");
			} 
		});
}