
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div>
	<c:forEach items="${comments }" var="comm">
		<p>
			<strong> <span> <c:if
						test="${comm.isShowName()==true }">
						<c:out value="${comm.getStudent().getFirstName() }" />
					</c:if> <c:if test="${comm.isShowName()==false }">
									  										Anonymous
									  									</c:if>
			</span></strong> <br> <span></span>
			<c:out value="${comm.getCommentDescription() }" />

		</p>
	</c:forEach>
	
</div>
