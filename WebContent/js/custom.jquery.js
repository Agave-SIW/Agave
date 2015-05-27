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
$("#buyFrame").attr('allowTransparency', 'true').attr('frameBorder', '0').attr('scrolling', 'no');

$(".login-link").click(function(event) {
	event.preventDefault();
	$(".login-link").toggle('slide');
	$(".login-form").toggle('slide');
});

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
});

