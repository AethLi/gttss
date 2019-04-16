angular.module('studentApp', [])
    .controller("openingReportCtrl", function ($scope) {
        $scope.whoIsActive = 0;
        let statusAndTrends = UE.getEditor('statusAndTrends');
        // let mainDestination = UE.getEditor('mainDestination');
        // let ideasAndSolutions = UE.getEditor('ideasAndSolutions');
        // let plan = UE.getEditor('plan');
        $scope.iAmActive = function (who) {
            $scope.whoIsActive = who;
        };
    });
