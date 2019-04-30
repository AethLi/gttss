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
            if ($scope.whoIsActive === 0) {
                $scope.statusAndTrends = openingReportEditor.getContent();
            } else if ($scope.whoIsActive === 1) {
                $scope.mainDestination = openingReportEditor.getContent();
            } else if ($scope.whoIsActive === 2) {
                $scope.ideasAndSolutions = openingReportEditor.getContent();
            } else if ($scope.whoIsActive === 3) {
                $scope.plan = openingReportEditor.getContent();
            }
            $scope.whoIsActive = who;
            if ($scope.whoIsActive === 0) {
                openingReportEditor.setContent($scope.statusAndTrends);
            } else if ($scope.whoIsActive === 1) {
                openingReportEditor.setContent($scope.mainDestination);
            } else if ($scope.whoIsActive === 2) {
                openingReportEditor.setContent($scope.ideasAndSolutions);
            } else if ($scope.whoIsActive === 3) {
                openingReportEditor.setContent($scope.plan);
            }
        };

        //获取我的开题报告
        $http({
            url: "/openingReport/getMyOpeningReport",
            method: "GET"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.statusAndTrends = result.data.model.statusAndTrends;
                $scope.mainDestination = result.data.model.mainDestination;
                $scope.ideasAndSolutions = result.data.model.ideasAndSolutions;
                $scope.plan = result.data.model.plan;

                $scope.whoIsActive = 0;
                openingReportEditor.setContent($scope.statusAndTrends);
            } else {
                // alert(result.data.message);
            }
        }, function e(result) {
            // alert("網絡錯誤");
        });

        //获取我的选题
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
            // alert("網絡錯誤");
        });
        //获取确认结果
        $http({
            url: "/verify/getOpeningReportVerify",
            method: "GET"
        }).then(function s(result) {
            if (result.data.status === 0) {
                opinion.setContent(result.data.model.teacherVerify.explanation);
                $scope.statusName = result.data.model.teacherVerify.status === 0 ? "已通过审核" : "未通过审核";
                opinion2.setContent(result.data.model.defenseVerify.explanation);
            }
        }, function e(result) {

        });
        //点击保存
        $scope.saveMyOpeningReport = function () {
            if ($scope.whoIsActive === 0) {
                $scope.statusAndTrends = openingReportEditor.getContent();
            } else if ($scope.whoIsActive === 1) {
                $scope.mainDestination = openingReportEditor.getContent();
            } else if ($scope.whoIsActive === 2) {
                $scope.ideasAndSolutions = openingReportEditor.getContent();
            } else if ($scope.whoIsActive === 3) {
                $scope.plan = openingReportEditor.getContent();
            }
            $http({
                url: "/openingReport/saveMyOpeningReport",
                method: "POST",
                data: {
                    statusAndTrends: $scope.statusAndTrends,
                    mainDestination: $scope.mainDestination,
                    ideasAndSolutions: $scope.ideasAndSolutions,
                    plan: $scope.plan,
                }
            }).then(function s(result) {
                alert(result.data.message);
            }, function e(result) {
                alert("网络错误");
            })
        }
    });
