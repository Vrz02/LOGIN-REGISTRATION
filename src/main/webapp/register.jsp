<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card p-4 shadow-lg">
					<h3 class="text-center mb-4">Register</h3>
					<form action="Controller" method="POST">
					<input type="hidden" name="action" value="register">
						<div class="mb-3">
							<label class="form-label">Port ID</label>  <input type="text"  name="portId" required class="form-control"
								placeholder="Enter your Port ID" required>
						</div>
						<div class="mb-3">
							<label class="form-label">Password</label> <input type="password"
								name="password" id="password" class="form-control"
								placeholder="Enter password" required>
						</div>
						<div class="mb-3">
							<label class="form-label">Confirm Password</label> <input
								type="password" id="confirm_password" class="form-control"
								placeholder="Confirm password"  name="confirm_password"required> <small
								id="passwordError" class="text-danger d-none">Passwords
								do not match!</small>
						</div>
						<div class="mb-3">
							<label class="form-label">Location</label> <input type="text"
								name="location" class="form-control"
								placeholder="Enter your location" required>
						</div>
						<div class="mb-3">
							<label class="form-label">User Type</label> <select
								name="userType" class="form-control" required>
								<option value="">Select User Type</option>
								<option value="consumer">Consumer</option>
								<option value="seller">Seller</option>
							</select>
						</div>
						<button type="submit" class="btn btn-primary w-100">Register</button>
						<p class="text-center mt-3">
							Already have an account? <a href="login.jsp">Login</a>
						</p>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		function validatePassword() {
			let password = document.getElementById("password").value;
			let confirmPassword = document.getElementById("confirm_password").value;
			let errorText = document.getElementById("passwordError");

			if (password !== confirmPassword) {
				errorText.classList.remove("d-none");
				return false;
			} else {
				errorText.classList.add("d-none");
				return true;
			}
		}
	</script>
</body>
</html>
