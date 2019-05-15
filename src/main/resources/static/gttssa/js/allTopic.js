angular.module('adminApp', [])
    .controller("allTopicCtrl", function ($scope, $http) {
        $scope.topics;
        $http({
            url: "/topic/getAllTopic"
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
