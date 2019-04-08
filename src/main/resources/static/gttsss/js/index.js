function login() {
    window.location = "/gttsss/mainPage.html";
    $.ajax()
}

function changeACAPTCHA() {
    document.getElementById("ACAPTCHAImg").src = "ACAPTCHA.do?" + Math.random();
}