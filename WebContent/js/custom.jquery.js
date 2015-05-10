function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
$(".needPlaceholder").attr("placeholder", function(){ var arr = this.name.split(":"); return capitalizeFirstLetter(arr[arr.length - 1]);});
$(".needRequired").prop("required",true);
$(".label-warning").text(function(){ 
	var arr = this.textContent.split(":"); 
	var str = capitalizeFirstLetter(arr[arr.length - 1]).trim();
	if (str.indexOf("float") !=-1) {
		return "Price must be a valid number";
	}
	return str;
	});

$(".price").priceFormat({
	prefix: '',
	suffix: ' &euro;',
	centsSeparator: ',',
	thousandsSeparator: '.',
	centsLimit: 2
});