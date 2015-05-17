function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
$(".needPlaceholder").attr("placeholder", function(){
		var arr = this.name.split(":"); 
		var str = arr[arr.length - 1];
		var arr = str.split(/(?=[A-Z])/);
		if(arr[0]=="dateof") return "dd/MM/yyyy";
		return capitalizeFirstLetter(arr[0]) + " " + (arr[1]? arr[1]:"") + " " + (arr[2]? arr[2]:"");
	});
$(".needType").attr("type", function(){ var arr = this.name.split(":"); return arr[arr.length - 1];});
$(".needRequired").prop("required",true);

$(".login-link").click(function() {
	$(".login-link").toggle('slide');
	$(".login-form").toggle('slide');
});

$(".label-warning").text(function(){ 
	var arr = this.textContent.split(":"); 
	var str = capitalizeFirstLetter(arr[1]).trim();
	if (str.indexOf("float") !=-1) {
		return "Price must be a valid number";
	}
	if (str.indexOf("numero intero") !=-1) {
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

$(document).ready(function() {
	$(".indexDesc").dotdotdot({
		ellipsis: 	'... ',
		watch: 		'window'
	});
	$(".productsDesc").dotdotdot({
		ellipsis: 	'... ',
		watch: 		'window'
	});
});