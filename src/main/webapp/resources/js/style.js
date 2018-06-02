window.onload = checkActive;
function checkActive() {
	var active = document.getElementById("active");
	var activeStatus = document.getElementById("activeStatus");
	var activeCheck = document.getElementById("activeCheck");
	if (activeStatus.value == 1) {
		activeCheck.checked = true;
		active.innerHTML = "";
	} else {
		activeStatus.value = 0;
		activeCheck.checked = false;
		active.innerHTML = "not";
	}
}

function active() {
	var active = document.getElementById("active");
	var activeStatus = document.getElementById("activeStatus");
	var activeCheck = document.getElementById("activeCheck");
	if (activeCheck.checked) {
		activeStatus.value = 1;
		active.innerHTML = "";
	} else {
		activeStatus.value = 0;
		active.innerHTML = "not";
	}
}

$(document).ready(function() {
	$('#example').DataTable({
		"pagingType" : "full_numbers"
	});
});