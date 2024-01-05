<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<style>
    * {
        box-sizing: border-box;
        font-family: 'Noto Sans KR', sans-serif;
    }

    body {
        margin: 0;
    }

    .login-form {
        box-sizing: border-box;
        width: 300px;
        padding: 32px;
        margin: 0 auto;
        margin-top: 100px;
        background: #fff;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
        -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
        -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
        box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
    }

    .text-field {
        font-size: 14px;
        margin-bottom: 8px;
        border-radius: 7px;
        padding: 10px;
        border: none;
        width: 100%;
        background-color: #eeeff1;

    }

    .links {
        text-align: center;
    }

    .links a {
        font-size: 12px;
        color: #9b9b9b;
    }

    .submit-btn {
        color: white;
        font-size: 14px;
        background-color: #1b1b1b;
        margin-bottom: 30px;
        padding: 10px;
        border: none;
        border-radius: 7px;
        width: 100%;
    }

    h2 {
        text-align: center;
    }

    .text-muted {
        font-size: 12px;
    }

    #wrap {
        width: 100%;
        /* margin-top = header height */
        margin-top: 60px;
    }

    /* Navigation bar */
    header {
        /* for sticky header */
        position: fixed;
        top: 0;

        width: 100%;
        height: 60px;
        z-index: 2000;
        background-color: #fff;
        box-shadow: 0 2px 2px rgba(0, 0, 0, 0.05), 0 1px 0 rgba(0, 0, 0, 0.05);
    }

    .logo {
        display: inline-block;
        height: 36px;
        margin: 12px 0 12px 25px;
    }

    .logo>img {
        height: 36px;
    }

    nav {
        float: right;

    }

    .nav-items {
        margin-right: 20px;
        text-decoration: none;
    }

    .nav-items>li {
        display: inline-block;
        /* 가로정렬 */
    }

    .nav-items>li>a {
        /* for Vertical Centering */
        line-height: 60px;
        padding: 0 30px;
        color: rgba(0, 0, 0, 0.4);
    }

    .nav-items>li>a:hover {
        color: rgba(0, 0, 0, 0.8);
    }
</style>

<body>

    <body>
        <header>
            <a class="logo" href="/board/list"><img src="https://poiemaweb.com/img/logo.png"></a>
            <nav>
                <ul class="nav-items">
                    <li><a href="/board/list">게시글목록</a></li>
                    <li><a href="/board/createForm">게시글등록</a></li>
                    <li><a href="/board/updateForm">게시글수정</a></li>
                    <li><a href="/view/member/join.jsp">회원가입</a></li>
                    <li><a href="/view/member/registration.jsp">회원정보수정</a></li>
                    <li><a href="/view/member/login.jsp">로그인</a></li>
                </ul>
            </nav>
        </header>



        <div class="login-form">
            <form>
                <h2><b>Bootstrap</b></h2>
                <hr>
                <br>
                <input type="text" name="userId" class="text-field" placeholder="아이디를 입력해주세요">
                <input type="password" name="userPassword" class="text-field" placeholder="비밀번호를 입력해주세요">
                <input type="submit" value="로그인" class="submit-btn btn btn-secondary btn-block">
            </form>

            <div class="links">
                <a href="#">비밀번호를 잊어버리셨나요?</a>
            </div>
        </div>

        <div class="p-2">
            <div class="footer">
                <footer>
                    <span class="text-muted d-flex justify-content-center">Copyright &copy; 2024 Bootstrap board</span>
                </footer>
            </div>
        </div>

    </body>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>

</html>