$(document).ready(
		function() {
			
			  $('#transaction').click(function(){
			
	$('#band').show();
	$('#ad').hide();
	$('#contact').hide()
	$('#login').hide()
})
 $('#already').click(function(){
			
	$('#band').hide();
	$('#ad').hide();
	$('#contact').hide()
	$('#login').show()
})
  $('#account').click(function(){
		
	$('#band').hide();
	$('#ad').show();
	$('#login').hide()
	$('#contact').hide()
})
  $('#create').click(function(){
	  $('#login').hide()			
	$('#band').hide();
	$('#ad').hide();
	$('#contact').show()
})

			// SUBMIT FORM
			$("#bookForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
			console.log($('#srcAddr').val())
		if($('#srcAddr').val()===""){
			alert("Please log into account")
			$('#band').hide();
	$('#ad').hide();
	$('#contact').show()
	return;
		}
			
			/*$.post("http://localhost:8081/send/transaction",
  {
    address: $("#address").val(),
    value: $("value").val()
  },
  function(data, status){
    alert("Data: " + data + "\nStatus: " + status);
  });*/
  

  
  var data={
    address: $("#address").val(),
    value: $("#value").val(),
    password:$("#pass").val(),
    srcAddress:$('#srcAddr').val()
}  

  $.ajax({
            type: 'post',
            url: 'http://localhost:8081/send/transaction',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: function (data) {
             alert("Your Transaction Receipt No" +data);
            }
        });
		
//
				//ajaxPost();
			});
			
				$("#accountform").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
			alert("click"+$("#address").val())
			/*$.post("http://localhost:8081/send/transaction",
  {
    address: $("#address").val(),
    value: $("value").val()
  },
  function(data, status){
    alert("Data: " + data + "\nStatus: " + status);
  });*/
  
  var data={
    password: $("#pass1").val()
  
}  

  $.ajax({
            type: 'post',
            url: 'http://localhost:8081/add/account',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: function (data) {
             alert(data);
             $('#addr').html(data.address)
             $('#bal').html(data.balance)
             $("#srcAddr").val(data.address)
            }
        });
		
//
				//ajaxPost();
			});
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				$('#loginForm').submit(function(event){

					// Prevent the form from submitting via the browser.
					event.preventDefault();
				alert("click"+$("#address").val())
				/*$.post("http://localhost:8081/send/transaction",
	  {
	    address: $("#address").val(),
	    value: $("value").val()
	  },
	  function(data, status){
	    alert("Data: " + data + "\nStatus: " + status);
	  });*/
	  
	  var data={
	    password: $("#pass2").val(),
	    privateKey:$("#key").val()
	  
	}  

	  $.ajax({
	            type: 'post',
	            url: 'http://localhost:8081/fetch/account',
	            data: JSON.stringify(data),
	            contentType: "application/json; charset=utf-8",
	            traditional: true,
	            success: function (data) {
	             alert(data);
	             $('#addr').html(data.address)
	             $('#bal').html(data.balance)
	             $("#srcAddr").val(data.address)
	            }
	        });
			
	//
					//ajaxPost();
					
				})
				
			
			async function connect() {
      const accounts = await    ethereum.request({method:'eth_requestAccounts'});
     const account = accounts[0];
console.log(account)
 }

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					bookId : $("#bookId").val(),
					bookName : $("#bookName").val(),
					author : $("#author").val()
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "saveBook",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$("#postResultDiv").html(
									"" + result.data.bookName
											+ "Post Successfully! <br>"
											+ "---> Congrats !!" + "</p>");
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})