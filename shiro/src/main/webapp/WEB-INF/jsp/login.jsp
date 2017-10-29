<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body,html{margin:0;padding:0;}
        .wrap{width:1000px;margin:20px auto;text-align: center;}
        form input{margin-bottom:5px;}
    </style>
</head>
<body>
<div class="wrap">
    <h1>login</h1>
    <form method="post" action="/login">
        <input type="text" name="username"><br>
        <input type="text" name="password"><br>
        <input type="submit" value="提交">
    </form>
</div>
</body>
</html>
