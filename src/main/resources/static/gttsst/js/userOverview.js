angular.module('teacherApp', [])
    .controller("userOverviewCtrl", function ($scope, $http) {
        $scope.name = "";
        $scope.unitName = "";
        $http({
            url: "/user/getMyOverview",
            method: "POST"
        }).then(function s(result) {
            $scope.name = result.data.model.userName;
            $scope.unitName = result.data.model.unitName;
        });
    });
