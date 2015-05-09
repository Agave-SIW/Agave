$(".price").priceFormat({
	prefix: '',
	suffix: ' &euro;',
	centsSeparator: ',',
	thousandsSeparator: '.',
	centsLimit: 2
});
$(".needPlaceholder").attr("placeholder", function(){ var arr = this.name.split(":"); return arr[arr.length - 1]});
$(".needRequired").prop("required",true);