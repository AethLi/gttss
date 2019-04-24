angular.module('teacherApp', [])
    .controller("historyTopicCtrl", function ($scope, $http) {
        $scope.topics;
        $http({
            url: "/topic/getTeacherHistoryTopic"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topics = result.data.model;
            } else {
                alert(result.data.messgae);
            }
        }, function e(result) {
            alert("网络错误");
        });
    });
