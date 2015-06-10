if(typeof fieldDis != "undefined" && fieldDis){
	$("#loginField").attr('disabled', 'disabled');
}

$(function() {
	$('#datetimepicker').datetimepicker({
		language: 'en',
	});
});

$(".needPlaceholder").attr("placeholder", function(){
	var arr = this.name.split(":"); 
	var str = arr[arr.length - 1];
	var arr = str.split(/(?=[A-Z])/);
	if(arr[0]=="dateof") return "MM/dd/yyyy";
	return capitalizeFirstLetter(arr[0]) + " " + (arr[1]? arr[1]:"") + " " + (arr[2]? arr[2]:"");
});
$(".needType").attr("type", function(){ var arr = this.name.split(":"); return arr[arr.length - 1];});
$(".needRequired").prop("required",true);
$(".needGlyph").html(function(){ 
	return '<span class="glyphicon glyphicon-'+this.textContent.toLowerCase().trim().replace(" ", "-")+'"></span> ' + this.textContent; 
});

$("#reviewFrame").attr('allowTransparency', 'true').attr('frameBorder', '0').attr('scrolling', 'no');
if(isIE()) $("#reviewFrame").removeAttr("seamless");

$("#buyFrame").attr('allowTransparency', 'true').attr('frameBorder', '0').attr('scrolling', 'no');
if(isIE()) $("#buyFrame").removeAttr("seamless");

/*$(".login-link").click(function(event) {
	event.preventDefault();
	$(".login-link").toggle('slide');
	$(".login-form").toggle('slide');
});*/

$("#addReviewButton").click(function(event) {
	event.preventDefault();
	$("#addReviewButton").toggle('slide');
	$("#reviewFrameDiv").toggle('slide');
});

$("#closedOrderTab").click(function(event){
	event.preventDefault();
	$(".tab-li-1").addClass("btn-primary");
	$(".tab-li-2").removeClass("btn-primary");
	$(".tab-li-1").removeClass("btn-default");
	$(".tab-li-2").addClass("btn-default");
	$("#closedOrders").show();
	$("#evadedOrders").hide();
});

$("#evadedOrderTab").click(function(event){
	event.preventDefault();
	$(".tab-li-1").removeClass("btn-primary");
	$(".tab-li-2").addClass("btn-primary");
	$(".tab-li-1").addClass("btn-default");
	$(".tab-li-2").removeClass("btn-default");
	$("#closedOrders").hide();
	$("#evadedOrders").show();
});

$("#evadeOrder").click(function(event){
	event.preventDefault();
	var orderId = parseInt($("#evadeOrder").attr('href').replace("#", ""));
	$.get( "orderEvader.xhtml", { id: orderId } )
	  .done(function( data ) {
		 var arr = data.split("<!-- TRY -->"); 
		 if(arr.length > 0) {
			 var arr = arr[1].split("<!-- CATCH -->");
			 var arr = arr[0].split("<!-- RESULT -->");
			 if(arr.length > 0) {
				 var arr = arr[1].split("<!-- END -->");
				 var message = arr[0];
			 }
			 else message="Error: admin not logged";
		 }
		 else message="Error";
		 
		 message.trim();
	     if(message == "Success") {
	    	 $("#evadeOrder").slideUp();
	    	 $("#evadeMessage").addClass("label label-success");
	    	 $("#evadeMessage").html("Order successfully evaded!");
	    	 $("#evadeMessage").slideDown();
	     }
	     else {
	    	 $("#evadeMessage").addClass("label label-warning");
	    	 $("#evadeMessage").html("There was an error - " + message);
	    	 $("#evadeMessage").slideDown();
	     }
	  });
});

$("#addReview").click(function(event){
	event.preventDefault();
	var productId  = parseInt($("#addReview").attr('href').replace("#", ""));
	var app = $("#stars option:selected").text().split(" ");
	var stars = parseInt(app[0]);
	var comment = $('#comment').val();
	$.get( "reviewAdder.xhtml", { id: productId, stars: stars, comment: comment } )
	  .done(function( data ) {
		 var arr = data.split("<!-- TRY -->"); 
		 if(arr.length > 0) {
			 var arr = arr[1].split("<!-- CATCH -->");
			 var arr = arr[0].split("<!-- RESULT -->");
			 if(arr.length > 0) {
				 var arr = arr[1].split("<!-- END -->");
				 var message = arr[0];
			 }
			 else message="Error";
		 }
		 else message="Error";
		 
		 message = $('<div/>').html(message).text();
		 //console.log(message);
		 var arr = message.trim().split(" <::> ");
		 if(arr.length < 3) message = "Error";
		 //console.log(message);
	     if(message != "Error") {
	    	 $("#revForm").slideUp();
	    	 $("#revNewStars").html(starsToHtml(arr['0']));
	    	 $("#revNewName").text(arr['1']);
	    	 $("#revNewComment").text(arr['2']);
	    	 $("#revNewDate").html(currentDate());
	    	 $("#revNew").slideDown();
	     }
	     else {
	    	 $("#reviewError").html("There was an error");
	     }
	  });
});

$(".label-warning").text(function(){ 
	var arr = this.textContent.split(":"); 
	var str = (arr[1]? capitalizeFirstLetter(arr[1]).trim():arr[0]);
	if (str.indexOf("float") !=-1) {
		return "Price must be a valid number";
	}
	if (str.indexOf("numero intero") !=-1) {
		return "Quantity must be a valid number";
	}
	if (str.indexOf("Quantity") !=-1) {
		return "Quantity must be a valid number";
	}
	if (str.indexOf("DateofBirth") !=-1) {
		return "Could not be intended as a valid date. Example: 17/05/2015";
	}

	return str + (arr[2]? arr[2]:"") + (arr[3]? arr[3]:"")
});

$(".price").priceFormat({
	prefix: '',
	suffix: ' &#8364;', //&euro;
	centsSeparator: ',',
	thousandsSeparator: '.',
	centsLimit: 2
});

//file input form made with input and button has to act like a normal file input
$(document).on('change', '.btn-file :file', function() {
	var input = $(this),
	numFiles = input.get(0).files ? input.get(0).files.length : 1,
			label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	input.trigger('fileselect', [numFiles, label]);
});

$(document).ready(function() {
	$("#buyFrame").slideDown();

	$('[data-toggle="tooltip"]').tooltip(); 

	$(".tab-li-1").addClass("active");
	$(".tab-li-2").removeClass("active");
	$("#closedOrders").show();
	$("#evadedOrders").hide();

	$(".indexDesc").dotdotdot({
		ellipsis: 	'... ',
		watch: 		'window'
	});
	$(".productsDesc").dotdotdot({
		ellipsis: 	'... ',
		watch: 		'window'
	});

	// word wrap already in css file
	$(".indexDesc").removeAttr("style");
	$(".productsDesc").removeAttr("style");

	$('.btn-file :file').on('fileselect', function(event, numFiles, label) {

		var input = $(this).parents('.input-group').find(':text'),
		log = numFiles > 1 ? numFiles + ' files selected' : label;

		if( input.length ) {
			input.val(log);
		} /*else {  if( log ) alert(log);   }*/

	});
	
	$('.modal-footer button').click(function(){
		var button = $(this);

		if ( button.attr("data-dismiss") != "modal" ){
			var inputs = $('form input');
			var title = $('.modal-title');
			var progress = $('.progress');
			var progressBar = $('.progress-bar');

			inputs.attr("disabled", "disabled");

			button.hide();

			progress.show();

			progressBar.animate({width : "100%"}, 100);

			progress.delay(1000)
					.fadeOut(600);

			button.text("Close")
					.removeClass("btn-primary")
					.addClass("btn-success")
    				.blur()
					.delay(1600)
					.fadeIn(function(){
						title.text("Log in is successful");
						button.attr("data-dismiss", "modal");
					});
		}
	});

	$('#loginModal').on('hidden.bs.modal', function (e) {
		var inputs = $('form input');
		var title = $('.modal-title');
		var progressBar = $('.progress-bar');
		var button = $('.modal-footer button');

		inputs.removeAttr("disabled");

		title.text("Customer Log in");

		progressBar.css({ "width" : "0%" });

		button.removeClass("btn-success")
				.addClass("btn-primary")
				.text("Ok")
				.removeAttr("data-dismiss");
                
	});
});

