function JSONLogin(username, password) {
	return JSON.stringify({
		"username" : username,
		"password" : password
	});
}


function login(){
	var username = $('#username').val();
	var password = $("#password").val();
	
	$.ajax({
			type : "POST",
			contentType: "application/json",
			url : "/customers/login",
			data : JSONLogin(username, password),
			success : function(data) {
				document.getElementById("errorSpan").style.display = 'none';
				sessionStorage.setItem('user', data.username);
				url = "index.html";
				if (data.role == "customer"){
					url = "customer.html";
				}
				else if (data.role == "manager"){
					url = "manager.html";
				}
				else if (data.role == "seller"){
					url = "seller.html";
				}
				
				setTimeout(function(){
					   window.location.href = url;
					}, 1000);
			},
			error : function(e) {
				document.getElementById("errorSpan").style.display = 'inline';
			} 
		});
}