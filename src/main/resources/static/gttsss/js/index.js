function login() {
    $.ajax({
        type: 'post',
        contentType: 'application/json;charset=utf-8',
        dataType: "json",
        async: true,
        url: '/user/login',
        data: JSON.stringify({
            "account": $("#inputAccount").val(),
            "password": md5(md5($("#inputPassword").val()) + $("#inputAcaptcha").val().toLowerCase()),
            "ACAPTCHA": $("#inputAcaptcha").val().toLowerCase()
        }),
        success: function (result) {
            console.log(result);
            if (result.status === 0) {
                window.location.href = "/gttsss/mainPage.html";
                changeACAPTCHA();
            } else {
                alert(result.message);
                changeACAPTCHA();
            }
        }
    });
    return false
}

function changeACAPTCHA() {
    document.getElementById("ACAPTCHAImg").src = "ACAPTCHA?" + Math.random();
}