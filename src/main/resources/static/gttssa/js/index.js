function login() {
    $.ajax({
        type: 'post',
        contentType: 'application/json;charset=utf-8',
        dataType: "json",
        async: true,
        url: '/user/loginA',
        data: JSON.stringify({
            "account": $("#inputAccount").val(),
            "password": md5($("#inputPassword").val()),
            "ACAPTCHA": "sdfddasfhasfhasi"
        }),
        success: function (result) {
            console.log(result);
            if (result.status === 0) {
                window.location.href = "/gttssa/mainPage.html";
            } else {
                alert(result.message);
            }
        }
    });
}
