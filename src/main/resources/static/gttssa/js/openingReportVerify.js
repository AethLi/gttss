angular.module('adminApp', [])
    .controller("openingReportVerifyCtrl", function ($scope, $http) {
        $scope.topics = [];
        $scope.oneTopic;
        $scope.verifyStatus;

        let statusAndTrendsEditor = UM.getEditor("statusAndTrendsEditor");
        let mainDestinationEditor = UM.getEditor("mainDestinationEditor");
        let ideasAndSolutionsEditor = UM.getEditor("ideasAndSolutionsEditor");
        let planEditor = UM.getEditor("planEditor");

        let explanationEditor = UM.getEditor("explanationEditor");

        $http({
            url: "/topic/getAllTopicHasSelect",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topics = result.data.model;
            }
        }, function e() {
            alert("网络错误");
        });

        $scope.topicDetail = function (id) {
            $scope.oneTopic = "";
            statusAndTrendsEditor.setContent("");
            mainDestinationEditor.setContent("");
            ideasAndSolutionsEditor.setContent("");
            planEditor.setContent("");

            $http({
                url: "/topic/getTopicById",
                data: {
                    id: id
                },
                method: "POST"
            }).then(function s(result) {

                if (result.data.status === 0) {
                    $scope.oneTopic = result.data.model;
                    $http({
                        url: "/openingReport/getOpeningReportById",
                        data: {
                            id: id
                        },
                        method: "POST"
                    }).then(function s(result) {
                        if (result.data.status === 0) {
                            statusAndTrendsEditor.setContent(result.data.model.statusAndTrends);
                            mainDestinationEditor.setContent(result.data.model.mainDestination);
                            ideasAndSolutionsEditor.setContent(result.data.model.ideasAndSolutions);
                            planEditor.setContent(result.data.model.plan);
                            $http({
                                url: "/verify/getVerifyById",
                                data: {
                                    id: result.data.model.defenseVerifyId
                                },
                                method: "POST"
                            }).then(function s(result) {
                                if (result.data.status === 0) {
                                    $scope.verifyStatus = result.data.model.status.toString();
                                    explanationEditor.setContent(result.data.model.explanation);
                                }
                            }, function e(result) {

                            });
                            $('#topicDetail').modal();
                        } else {
                            alert(result.data.message);
                        }
                    }, function e() {
                        alert("获取开题报告失败，网络错误")
                    })
                } else {
                    alert(result.data.message);
                }
            }, function e() {
                alert("获取开题报告失败，网络错误")
            });

            $scope.saveOpeningReportVerify = function () {
                $http({
                    url: '/verify/saveOpeningReportVerifyA',
                    data: {
                        id: $scope.oneTopic.id,
                        status: $scope.verifyStatus,
                        explanation: explanationEditor.getContent()
                    },
                    method: "POST"
                }).then(function s(result) {
                    alert(result.data.message);
                }, function e() {
                    alert("保存失败，网络错误");
                })
            }
        }
    });
