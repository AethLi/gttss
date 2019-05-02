angular.module('studentApp', [])
    .controller("openingReportVerifyCtrl", function ($scope, $http) {
        $scope.openAble;
        $http({
            url: "/openingReport/getMyOpenAble",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.openAble = result.data.model;
            }
        }, function e() {
            alert("获取失败，网络错误");
        })
    });
