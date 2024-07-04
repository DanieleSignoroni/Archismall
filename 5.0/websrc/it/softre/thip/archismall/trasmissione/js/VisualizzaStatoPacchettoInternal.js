function visualizzaStatoPacchettoInternalOnLoad(){
	window.resizeTo(window.screen.availWidth, window.screen.availHeight);
}

function getURLWS() {
	var ris;
	var url = window.location.href;
	var cut = url.indexOf(webAppPath);
	ris = url.substring(0, cut);
	ris += webAppPath;
	ris += "/api";
	return ris;
}

function getBearerTokenFromLocalStorage() {
	var jwt = null;
	jwt = 'Bearer ' + localStorage.getItem("pth-" + location.pathname.split("/")[1].toLowerCase() + "-jwt");
	return jwt;
}