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