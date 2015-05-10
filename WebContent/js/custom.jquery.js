function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
$(".needPlaceholder").attr("placeholder", function(){ var arr = this.name.split(":"); return capitalizeFirstLetter(arr[arr.length - 1]);});
$(".needRequired").prop("required",true);
$(".price").priceFormat({
	prefix: '',
	suffix: ' &euro;',
	centsSeparator: ',',
	thousandsSeparator: '.',
	centsLimit: 2
});