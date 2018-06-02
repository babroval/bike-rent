<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="http://maps.google.com/maps/api/js?sensor=true"></script>

<style>
#map {
	width: auto;
	height: 500px;
	border: 1px solid #000;
	margin: auto;
}

#map a {
	background: #4791d2;
	color: #fff;
	padding: 5px;
	border: none;
	margin-top: 10px;
}

#map a:hover {
	background: #3d31c5;
}
</style>

<div id="map"></div>

<script type="text/javascript">
	var point = [];
	var allPoints = [];
	'<c:forEach var="point" items="${allPoints}">'
	point = {
		id : '${point.id}',
		numPoint : '${point.numPoint}',
		addressMark : '${point.addressMark}',
		latitude : '${point.latitude}',
		longitude : '${point.longitude}',
		freeBikes : '${point.freeBikes}',
		vacantSlots : '${point.slots - point.freeBikes}'
	};
	allPoints.push(point);
	'</c:forEach>'

	window.onload = initMap;
	function initMap() {
		var map = new google.maps.Map(document.getElementById('map'), {
			center : {
				lat : 53.9044,
				lng : 27.5634
			},
			scrollwheel : true,
			zoom : 11
		});

		for (var i = 0; i < allPoints.length; ++i) {
			var marker = new google.maps.Marker({
				position : {
					lat : allPoints[i].latitude * 1,
					lng : allPoints[i].longitude * 1
				},
				map : map,
				title : allPoints[i].addressMark
			});

			var pointInfo = [];
			pointInfo[i] = "<h3>Bike Point # "
					+ allPoints[i].numPoint
					+ "</h3>"
					+ "<p>Address: <br><b>"
					+ allPoints[i].addressMark
					+ "</b><br>"
					+ "Free bikes: <b>"
					+ allPoints[i].freeBikes
					+ "</b><br>"
					+ "Vacant slots: <b>"
					+ allPoints[i].vacantSlots
					+ "</b></p>"
					+ "<a href=\"<c:url value='/order-" + allPoints[i].numPoint+"' />\">Go to Point</a>";

			showPointInfo(marker, pointInfo[i]);
		}

		function showPointInfo(marker, pointInfo) {
			var infowindow = new google.maps.InfoWindow({
				content : pointInfo
			});
			marker.addListener('mouseover', function() {
				infowindow.open(marker.get('map'), marker);
			});

			marker.addListener('mouseout', function() {
				setTimeout(function() {
					infowindow.close();
				}, 5000);
			});
		}
	}
</script>