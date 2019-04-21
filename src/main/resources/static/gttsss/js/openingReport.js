angular.module('studentApp', [])
    .controller("openingReportCtrl", function ($scope, $http) {
        $scope.statusAndTrends = "";
        $scope.mainDestination = "";
        $scope.ideasAndSolutions = "";
        $scope.plan = "";

        $scope.whoIsActive = 0;

        $scope.topicName = "";
        $scope.teacherName = "";
        $scope.connectionNum = "";

        let opinion = UM.getEditor('opinion');
        opinion.setDisabled();
        let opinion2 = UM.getEditor('opinion2');
        opinion2.setDisabled();
        let openingReportEditor = UM.getEditor('openingReportEditor');
        $scope.iAmActive = function (who) {
            if ($scope.whoIsActive == 0) {
                $scope.statusAndTrends = openingReportEditor.getContent();
            } else if ($scope.whoIsActive == 1) {
                $scope.mainDestination = openingReportEditor.getContent();
            } else if ($scope.whoIsActive == 2) {
                $scope.ideasAndSolutions = openingReportEditor.getContent();
            } else if ($scope.whoIsActive == 3) {
                $scope.plan = openingReportEditor.getContent();
            }
            $scope.whoIsActive = who;
            if ($scope.whoIsActive == 0) {
                openingReportEditor.setContent($scope.statusAndTrends);
            } else if ($scope.whoIsActive == 1) {
                openingReportEditor.setContent($scope.mainDestination);
            } else if ($scope.whoIsActive == 2) {
                openingReportEditor.setContent($scope.ideasAndSolutions);
            } else if ($scope.whoIsActive == 3) {
                openingReportEditor.setContent($scope.plan);
            }
        };
        $http({
            url: "/topic/getMyTopic",
            method: "GET"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topicName = result.data.model.topic.name;
                $scope.teacherName = result.data.model.teacher.name;
                $scope.connectionNum = result.data.model.phoneNum;
            } else {
                alert(result.data.message);
            }
        }, function e(result) {
            alert("網絡錯誤");
        });
        $http({
            url: "/verify/getOpeningReportVerify",
            method: "GET"
        }).then(function s(result) {
            opinion.setContent(result.data.model.teacherVerify.explanation);
            $scope.statusName = result.data.model.teacherVerify.status === 0 ? "已通过审核" : "未通过审核";
            opinion2.setContent(result.data.model.defenseVerify.explanation);
        }, function e(result) {

        })
    });
