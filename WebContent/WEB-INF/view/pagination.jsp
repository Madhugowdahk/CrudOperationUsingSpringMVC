<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<br>
	<ul class="pagination pagination-sm pagination1 justify-content-center"
		style="margin: 20px 0">

		<c:choose>
			<c:when test="${isPostMethod}">
				<li class="page-item">
				<c:if test="${paginationDetails.isFirstSeries == false}">
						<c:url var="firstPage" value="">
							<c:param name="page" value="1" />
						</c:url>

						<c:url var="prev4page" value="${paginationDetails.linkedpage}">
							<c:param name="page"
								value="${paginationDetails.previousSeriesLinkPage}" />
						</c:url>


						<c:url var="firstPage" value="${searchUrl}/1"></c:url>
						<c:url var="prev4page"
							value="${searchUrl}/${paginationDetails.previousSeriesLinkPage}"></c:url>


						<li class="page-item">
							<form action="${firstPage}" id="firstpage" method="post">
								<button class="prev page-link firstbutton" title="First page">First</button>
							</form>
						</li>
						<li class="page-item">
							<form action="${prev4page}" id="prev" method="post">
								<button class="page-link " type="submit">Prev</button>
							</form>
					</c:if></li>
				<c:forEach var="loop" items="${paginationDetails.pages}"
					varStatus="loop1">

					<li class="page-item"><c:url var="Page"
							value="${searchUrl}/${loop}">

						</c:url> <c:set var="clickedpage" value="${paginationDetails.currentPage}" />

						<c:choose>
							<c:when test="${loop == clickedpage}">
								<a class="disabled page-link label_font" title="${loop}">
									${loop}</a>
							</c:when>
							<c:otherwise>
								<form action="${Page}" id="${loop}" method="post">

									<button class="page-link " type="submit">${loop}</button>
								</form>
							</c:otherwise>
						</c:choose></li>
				</c:forEach>
				<li class="page-item"><c:if
						test="${paginationDetails.isLastSeries == false}">


						<c:url var="next4page"
							value="${searchUrl}/${paginationDetails.nextSeriesLinkPage}"></c:url>
						<c:url var="lastPage"
							value="${searchUrl}/${paginationDetails.lastSeriesEnd}"></c:url>



						<li class="page-item">
							<form action="${nex4tpage}" id="next" method="post">
								<button class="next page-link nextbutton" title="Next"
									type="submit">Next ${nex4tpage}</button>

							</form>
						</li>
						<li class="page-item">
							<form action="${lastPage}" id="lastpage" method="post">
								<button class="next page-link lastbutton" title="Last page"
									type="submit">Last</button>

							</form>
						</li>
					</c:if></li>

			</c:when>

			<c:otherwise>


				<li class="page-item"><c:if
						test="${paginationDetails.isFirstSeries == false}">
						<c:url var="firstPage" value="">
							<c:param name="page" value="1" />
						</c:url>

						<c:url var="prev10page" value="${paginationDetails.linkedpage}">
							<c:param name="page"
								value="${paginationDetails.previousSeriesLinkPage}" />
						</c:url>

						<li class="page-item"><a href="${firstPage}"
							class="prev page-link" title="First page">First </a></li>


						<li class="page-item"><a href="${prev10page}"
							class="prev page-link" title="Prev">Prev</a></li>
					</c:if></li>
				<c:forEach var="loop" items="${paginationDetails.pages}"
					varStatus="loop1">
					<li class="page-item"><c:url var="Page" value="">
							<c:param name="page" value="${loop}" />
						</c:url> <c:set var="clickedpage" value="${paginationDetails.currentPage}" />
						<%-- 	<c:set var="clickedpage2" value ="${nextSeriesLinkPage}"/>
											 --%> <c:choose>
							<c:when test="${loop == clickedpage}">
								<li class="page-item"><a
									class="disabled label_font page-link" title="${loop}">
										${loop}</a></li>
							</c:when>
							<%--  <c:when test="${loop == clickedpage2}">
												<a class="disabled"> ${loop} </a>
												</c:when> 
					 --%>
							<c:otherwise>
								<li class="page-item"><a href="${Page}"
									class="label_font page-link" title="${loop}">${loop}</a></li>

							</c:otherwise>
						</c:choose></li>

				</c:forEach>
				<li class="page-item">
					<%--  <c:out value="${paginationDetails.isLastSeries}"></c:out> --%>
					<c:if test="${paginationDetails.isLastSeries == false}">




						<c:url var="next10page" value="">
							<c:param name="page"
								value="${paginationDetails.nextSeriesLinkPage}" />

						</c:url>
						<c:url var="lastPage" value="">
							<c:param name="page" value="${paginationDetails.lastSeriesEnd}" />

						</c:url>

						<li class="page-item"><a href="${next10page}"
							class="next page-link" title="Next"> Next</a></li>

						<li class="page-item"><a href="${lastPage}"
							class="next page-link" title="Last page"> Last </a></li>


					</c:if>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>