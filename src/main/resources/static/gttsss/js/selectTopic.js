angular.module('studentApp', [])
    .controller("selectTopicCtrl", function ($scope, $http) {
        $scope.allTopic;
        $scope.topics;
        $http({
            url: "/topic/queryCurrentTopic",
            method: "GET"
        }).then(function s(result) {
            if (result.data.status == 0) {
                $scope.allTopic = result.data.model;
                $scope.topics = $scope.allTopic;
            } else {
                alert(result.data.message);
            }
        }, function e(result) {

        });
        $scope.hasSelect = false;
        $scope.selectedTopic;
        $scope.topicDetail = function (id) {
            // $http({});
            $('#topicDetail').modal();
        }
    });
