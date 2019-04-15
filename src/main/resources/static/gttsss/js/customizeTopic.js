angular.module('studentApp', [])
    .controller("customizeTopicCtrl", function ($scope) {
        $scope.whoIsActive = 0;
        $scope.iAmActive = function (who) {
            $scope.whoIsActive = who;
        };
    });
