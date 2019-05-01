angular.module('teacherApp', [])
    .controller("customizeTopicCheckCtrl", function ($scope, $http) {
        $scope.topics = [];
        $http({
            url: "/topic/teacherGetTopicVerifyCheck",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topics = result.data.model;
            }
        }, function e() {
            alert("网络错误");
        })
    });