<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="content">
	<h2>Freedom in movement</h2>
	<div class="info">
		<div class="submenu">
			<label for="tab1">News</label> <label for="tab2">Our bicycles</label>
			<label for="tab3">The benefits of bicycle</label> <label for="tab4">City
				tour</label>
		</div>
		<div class="tab_submenu">
			<input type="radio" name="rad" id="tab1" checked>
			<div class="tab_submenu_1">

				<jsp:include page="/WEB-INF/views/common/news.jsp" />

			</div>
			<input type="radio" name="rad" id="tab2">
			<div class="tab_submenu_2">
				<img src="resources/img/dif/nahi_velo.jpg" alt="velo1" />
			</div>
			<input type="radio" name="rad" id="tab3">
			<div class="tab_submenu_3">
				<img src="resources/img/dif/polza_velo.jpg" alt="velo1" />
			</div>
			<input type="radio" name="rad" id="tab4">
			<div class="tab_submenu_4">
				<ol>
					<li><img src="resources/img/dif/minsk1.jpg">
						<ul>
							<li class="i2"><a href="#"> <span><b>Island
											of Tears</b></span> <span class="price_tour"></span>
							</a></li>
						</ul></li>
					<li><img src="resources/img/dif/minsk2.jpg">
						<ul>
							<li class="i2"><a href="#"> <span><b>Troitskoe
											Predmestie</b></span> <span class="price_tour"></span>
							</a></li>
						</ul></li>
					<li><img src="resources/img/dif/minsk3.jpg">
						<ul>
							<li class="i2"><a href="#"> <span><b>Red
											Church</b></span> <span class="price_tour"></span>
							</a></li>
						</ul></li>
					<li><img src="resources/img/dif/minsk4.jpg">
						<ul>
							<li class="i2"><a href="#"> <span><b>National
											Library</b></span> <span class="price_tour"></span>
							</a></li>
						</ul></li>
				</ol>
			</div>
		</div>
	</div>
</div>