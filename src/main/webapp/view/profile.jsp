<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main class="py-6 bg-surface-secondary">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xl-7 mx-auto">
					<form class="mb-6" action="/ltwebst4/profile" enctype='multipart/form-data' method="post">
						<!-- Profile picture -->
						<div class="card shadow border-0 mt-4 mb-10">
							<div class="card-body">
								<div class="d-flex align-items-center">
									<div>
										<div class="d-flex align-items-center">
											<a href="#"
												class="avatar avatar-lg bg-warning rounded-circle text-white">
												<img alt="..." src="${image}" height="200px">
											</a>
											<div class="ms-4">
												<span class="h4 d-block mb-0">${fullname}</span>
											</div>
										</div>
									</div>
									<div class="ms-auto">
										<input type="file" name="multiPartServlet" />
									</div>
								</div>
							</div>
						</div>
						<hr>
						<!-- Form -->
						<div class="mb-5">
							<h5 class="mb-0">Contact Information</h5>
						</div>

						<div class="row mb-5">
							<div class="col-md-6">
								<div class="">
									<label class="form-label">Username</label> <input type="text"
										class="form-control" name="username" id="username"
										value="${username}" readonly>
								</div>
							</div>
							<div class="col-md-6">
								<div class="">
									<label class="form-label">Full name</label> <input type="text"
										class="form-control" name="fullname" id="fullname"
										value="${fullname}">
								</div>
							</div>
						</div>
						<div class="row g-5">
							<div class="col-md-6">
								<div class="">
									<label class="form-label" for="email">Email</label> <input
										type="email" class="form-control" name="email" id="email"
										value="${email}" readonly>
								</div>
							</div>
							<div class="col-md-6">
								<div class="">
									<label class="form-label" for="phone">Phone number</label> <input
										type="tel" class="form-control" name="phone" id="phone"
										value="${phone}">
								</div>
							</div>
						</div>
						<div class="text-end padding-top-20">
							<button type="submit" class="btn btn-sm btn-primary">Save</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
</body>
</html>