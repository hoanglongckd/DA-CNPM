<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../_header.jsp" />
<%-- <%@include file="header.jsp" %> --%>
<title>Thêm nhân viên</title>
<jsp:include page="../_top.jsp" />
<jsp:include page="../_menu.jsp" />

<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Thêm <small>Nhân viên</small>
				</h1>
			</div>
			<jsp:include page="../_message-block.jsp" />
			<!-- /.col-lg-12 -->
			<div class="col-lg-7" style="padding-bottom: 120px">
				<form action="${pageContext.request.contextPath}/them-nhan-vien"
					method="POST">
					<div class="form-group">
						<label>Mã nhân viên: </label> <input class="form-control"
							type="text" name="ma-nhan-vien" required />
					</div>
					<div class="form-group">
						<label>Họ và tên: </label> <input class="form-control" type="text"
							name="ho-va-ten" required/>
					</div>
					<div class="form-group">
						<label>Ngày sinh: </label> <input class="form-control" type="text"
							name="ngay-sinh" required/>
					</div>
					<br>
					<button type="submit" class="btn btn-default">Thêm</button>
					<a href="<%=request.getContextPath()%>/danh-sach-nhan-vien" class = "btn btn-default">Cancel</a>
					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->

<jsp:include page="../_footer-start.jsp" />

<script type="text/javascript">
	$(function() {
		$('input[name="ngay-sinh"]').daterangepicker({
			singleDatePicker : true,
			showDropdowns : true
		});
	});
</script>


<jsp:include page="../_footer-end.jsp" />