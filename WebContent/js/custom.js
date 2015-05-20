function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function countDown(redirect, count) {
	var timer = document.getElementById("timer");
	if (count > 0) {
		count--;
		timer.innerHTML = "This page will redirect in "
				+ count + " seconds.";
		setTimeout(countDown, 1000, redirect, count);
	} else {
		window.location.href = redirect;
	}
}