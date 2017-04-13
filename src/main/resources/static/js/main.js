function onPayClick() {
	
	var checkedValue = $('.licTypeRadio:checked').val();
	var xhttp = new XMLHttpRequest();
	
	console.log(checkedValue);

	if (checkedValue == "Trial") {
		xhttp.open("POST", "http://35.154.229.97:8080/trialEntitlement", false);
	} else if (checkedValue == "Basic") {
		xhttp.open("POST", "http://35.154.229.97:8080/basicEntitlement", false);
	} else if (checkedValue == "Premium") {
		xhttp.open("POST", "http://35.154.229.97:8080/premiumEntitlement", false);
	} else if (checkedValue == "LifeTime") {
		xhttp.open("POST", "http://35.154.229.97:8080/lifeTimeEntitlement", false);
	} 	
	
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log("Successfully created entitlement.");
	    } else {
	    	alert("Allowable limit for feature Storage is reached.");
	    }
	};
	
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
	
	console.log(xhttp.response);
	
	updateAvlCounts();
};

function onStorageCheckOutClick() {
	
	var storageValue = document.getElementById('storageValue').value;
	
	var xhttp = new XMLHttpRequest();
	var url = "http://35.154.229.97:8080/checkoutLicense?sonId=100&featureName=Storage&count=";
	
	url = url + storageValue;
	
	xhttp.open("POST", url, false);
	
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log("Successfully Checked Out license.");
	    } else {
	    	var jsonResponse = JSON.parse(this.responseText);
	    	alert(jsonResponse["message"]);
	    }
	};
	
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    
    updateAvlCounts();
};

function onStorageCheckInClick() {
	var storageValue = document.getElementById('storageValue').value;
	
	var xhttp = new XMLHttpRequest();
	var url = "http://35.154.229.97:8080/checkInLicense?sonId=100&featureName=Storage&count=";
	
	url = url + storageValue;
	
	xhttp.open("POST", url, false);
	
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log("Successfully Checked In license.");
	    } else {
	    	var jsonResponse = JSON.parse(this.responseText);
	    	alert(jsonResponse["message"]);
	    }
	};
	
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    
    updateAvlCounts();
};

function onProjCheckOutClick() {
	var projValue = document.getElementById('projectValue').value;
	
	var xhttp = new XMLHttpRequest();
	var url = "http://35.154.229.97:8080/checkoutLicense?sonId=100&featureName=ActiveProjects&count=";
	
	url = url + projValue;
	
	xhttp.open("POST", url, false);
	
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log("Successfully Checked Out license.");
	    } else {
	    	var jsonResponse = JSON.parse(this.responseText);
	    	alert(jsonResponse["message"]);
	    }
	};
	
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    
    updateAvlCounts();
};

function onProjCheckInClick() {
	var projValue = document.getElementById('projectValue').value;
	
	var xhttp = new XMLHttpRequest();
	var url = "http://35.154.229.97:8080/checkInLicense?sonId=100&featureName=ActiveProjects&count=";
	
	url = url + projValue;
	
	xhttp.open("POST", url, false);
	
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log("Successfully Checked Out license.");
	    } else {
	    	var jsonResponse = JSON.parse(this.responseText);
	    	alert(jsonResponse["message"]);
	    }
	};
	
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    
    updateAvlCounts();
};

function updateStorageCount () {
	var xhttp = new XMLHttpRequest();
	var url = "http://35.154.229.97:8080/storageVal";
	
	xhttp.open("GET", url, false);
	
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log("Successfully updated storage value.");
	    	var jsonResponse = JSON.parse(this.responseText);
	    	$("#StorageVal").text(jsonResponse);
	    } else {
	    	var jsonResponse = JSON.parse(this.responseText);
	    	alert(jsonResponse["message"]);
	    }
	};
	
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function updateProjectsCount () {
	var xhttp = new XMLHttpRequest();
	var url = "http://35.154.229.97:8080/projectVal";
	
	xhttp.open("GET", url, false);
	
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log("Successfully updated projects value.");
	    	var jsonResponse = JSON.parse(this.responseText);
	    	$("#ProjectsVal").text(jsonResponse);
	    } else {
	    	var jsonResponse = JSON.parse(this.responseText);
	    	alert(jsonResponse["message"]);
	    }
	};
	
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function updateAvlCounts() {
	updateStorageCount();
	updateProjectsCount();
}