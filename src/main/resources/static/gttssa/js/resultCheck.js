angular.module('teacherApp', [])
    .controller("resultCheckCtrl", function ($scope, $http) {
        $scope.topics = [];
        $http({
            url: "/file/getHasSubmitResultStudentsA",
            method: "GET"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topics = result.data.model;
            } else {
                alert(result.data.message);
            }
        }, function e(result) {
            alert("网络错误");
        })
    });