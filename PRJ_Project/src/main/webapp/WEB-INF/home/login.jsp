<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập - Cinema Project</title>
    </head>
    <body>
        <form action="LoginServlet" method="POST">
            <h3>Đăng nhập Hệ thống Rạp Phim</h3>
            
            <label>Tên đăng nhập:</label>
            <input type="text" name="txtUserID" required />
            
            <label>Mật khẩu:</label>
            <input type="password" name="txtPassword" required />
            
            <button type="submit">Đăng nhập</button>
            
            <p style="color:red;">${requestScope.ERROR_MESSAGE}</p>
        </form>
    </body>
</html>