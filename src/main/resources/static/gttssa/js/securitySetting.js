angular.module('studentApp', [])
    .controller("securitySettingCtrl", function ($scope, $http) {
        $scope.oldPassword = "";
        $scope.newPassword = "";
        $scope.newPasswordRepeat = "";
        $scope.phoneNum = "";

        $http({
            url: "/user/getMyself",
            method: "POST",
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.phoneNum = result.data.model.phoneNum;
            }
        }, function e() {

        });

        $scope.saveChangePassword = function () {
            if ($scope.newPassword !== $scope.newPasswordRepeat) {
                alert("两次密码不一致");
                return;
            }
            else {
                $http({
                    url: "/user/changePassword",
                    method: "POST",
                    data: {
                        new: $scope.newPassword,
                        old: $scope.oldPassword
                    }
                }).then(function s(result) {
                    alert(result.data.message);
                    if (result.data.status === 0) {
                        $http({
                            url: "/user/logout",
                            method: "GET"
                        }).then(function success(result) {
                            if (result.data.status === 0) {
                                window.location.href = "/";
                            } else {
                                alert(result.data.message);
                                window.location.href = "/";
                            }
                        }, function error(result) {
                            alert(result.data.message);
                            window.location.href = "/";
                        });
                    }
                }, function e() {
                    alert("网络错误");
                })
            }
        };
        $scope.changeConnectionNum = function () {
            $http({
                url: "/user/changeConnectionNum",
                data: {
                    phoneNum: $scope.phoneNum
                },
                method: "POST"
            }).then(function s(result) {
                alert(result.data.message);
            }, function e() {
                alert("网络错误");
            })
        }
    })
;
