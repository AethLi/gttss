angular.module('teacherApp', [])
    .controller("topicBookCtrl", function ($scope) {
        $scope.whoIsActive = 0;
        $scope.iAmActive = function (who) {
            $scope.whoIsActive = who;
        };
    });
