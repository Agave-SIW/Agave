function capitalizeFirstLetter(string) {
	if(typeof string == "undefined") return "";
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function countDown(redirect, count){
	countDown(redirect, count, "This page will redirect in ");
}

function countDown(redirect, count, text) {
	var timer = document.getElementById("timer");
	if (count > 0) {
		count--;
		timer.innerHTML = text
				+ count + " seconds.";
		setTimeout(countDown, 1000, redirect, count, text);
	} else {
		window.location.href = redirect;
	}
}

function loadPreviousPage() {
	if(document.referrer) {
		window.location = document.referrer + 
		((document.referrer.indexOf('?')!=-1 && 
				document.referrer.indexOf('rel=')!=-1)? 
						"&amp;rel=1":"");
		}
		else {
			window.history.back();
		}
}

function isIE() {
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      
    	return true;

    return false;
}

function currentDate() {
	var fullDate = new Date();
	var twoDigitMonth = fullDate.getMonth()+""; 
	if(twoDigitMonth.length==1)  twoDigitMonth="0" + twoDigitMonth;
	var twoDigitDate = fullDate.getDate()+"";
	if(twoDigitDate.length==1) twoDigitDate="0" + twoDigitDate;
	return twoDigitMonth + "/" + twoDigitDate + "/" + fullDate.getFullYear();
}

function starsToHtml(stars){
	var html = "";
	for (var i = 0; i < stars; i++) {
		html += "<span class=\"glyphicon glyphicon-star\"></span> ";
	}
	for (var i = stars; i < 5; i++) {
		html += "<span class=\"glyphicon glyphicon-star-empty\"></span> ";
	}
	return html;
}

var totalrecords = 100;