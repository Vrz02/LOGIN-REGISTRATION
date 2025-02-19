<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register & Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card p-4 shadow-lg">
                    <h3 class="text-center mb-4">Login</h3>
                    <form action="Controller" method="POST">
                      <input type="hidden" name="action" value="login"> 
                        <div class="mb-3">
                            <label class="form-label">Port ID</label>
                            <input type="text" name="portId" class="form-control" placeholder="Enter your Port ID" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" placeholder="Enter password" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Login</button>
                        <p class="text-center mt-3"> Don't have a Account? <a href="register.jsp">Register here</a></p>
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
    