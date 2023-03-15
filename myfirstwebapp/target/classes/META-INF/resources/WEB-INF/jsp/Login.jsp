<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<div class="container">
		<h1>Login</h1>
		<pre>${errormsg}</pre>
		<form action="Login" method="post">
			<input type="text" name="name" placeholder="Username"> <br>
			<input type="text" name="password" placeholder="Password"> <br>
			<input type="submit" placeholder="submit">

		</form>
	</div>
	<%-- ${name}! --%>
</body>
</html>