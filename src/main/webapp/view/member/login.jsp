<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="로그인" />
</jsp:include>


<body>

    <body>
        <jsp:include page="/view/common/header.jsp"/>



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