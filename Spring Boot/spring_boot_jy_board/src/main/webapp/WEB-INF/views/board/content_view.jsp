<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#a-delete')
								.click(
										function(event) {
											//prevendDefault()는 href로 연결해 주지 않고 단순히 click에 대한 처리를 하도록 해준다.
											event.preventDefault();
											console.log("ajax 호출전");

											$
													.ajax({
														type : "DELETE",
														url : "${pageContext.request.contextPath}/restful/board/"
																+ "${content_view.bid}",
														data : {
															"bid" : "${content_view.bid}"
														},
														success : function(
																result) {
															console.log(result);
															if (result == "SUCCESS") {
																//getList();

															}

														},
														error : function(e) {
															console.log(e);
														}
													})

										});
					});
</script>
</head>
<body>
	<form action="modify" method="post">
		<table id="list-table" width="500" cellpadding="0" cellspacing="0"
			border="1">

			<input type="hidden" name="bid" value="${content_view.bid}">
			<tr>
				<td>번호</td>
				<td>${content_view.bid}</td>
			</tr>
			<tr>
				<td>히트</td>
				<td>${content_view.bhit}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="bname"
					value="${content_view.bname}"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="btitle"
					value="${content_view.btitle}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="bcontent">${content_view.bcontent}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정">
					&nbsp;&nbsp; <a href="list">목록보기</a> &nbsp;&nbsp; <a href="delete?bid=${content_view.bid}">삭제</a>
					&nbsp;&nbsp; <a href="reply_view?bid=${content_view.bid}">답변</a></td>
			</tr>

		</table>
	</form>
</body>
</html>