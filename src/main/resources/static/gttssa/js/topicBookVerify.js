angular.module('adminApp', [])
    .controller("topicBookVerifyCtrl", function ($scope, $http) {
        let topicBookEditor = UM.getEditor("topicBookEditor");

        $scope.verifyStatus = null;
        let adminExplanation = UM.getEditor("adminExplanation");

        let topicBasisEditor = UM.getEditor("topicBasisEditor");
        let topicGoalEditor = UM.getEditor("topicGoalEditor");
        let externalConditionEditor = UM.getEditor("externalConditionEditor");
        let reference2Editor = UM.getEditor("reference2Editor");


        $scope.topicBasis = "";
        $scope.topicGoal = "";
        $scope.externalCondition = "";
        $scope.reference = "";

        $scope.topics = [];

        $scope.oneTopic;

        $http({
            url: "/topic/queryCurrentTopic",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topics = result.data.model;
            }
        }, function e() {
            alert("网络错误");
        });
        $scope.saveTopicBookVerify = function () {
            $http({
                url: "/verify/saveTopicBookVerify",
                data: {
                    topicId: $scope.oneTopic.id,
                    status: $scope.verifyStatus,
                    explanation: adminExplanation.getContent()
                },
                method: "POST"
            }).then(function s(result) {
                alert(result.data.message)
            }, function e() {
                alert("网络错误");
            })
        };
        $scope.topicDetail = function (id) {
            $scope.oneTopic = "";
            $http({
                url: "/topic/getTopicById",
                data: {
                    id: id
                },
                method: "POST"
            }).then(function s(result) {
                if (result.data.status === 0) {
                    $scope.oneTopic = result.data.model;
                    $("#topicDetail").modal();
                } else {
                    alert(result.data.message);
                }
            }, function e() {
                alert("网络不通");
            });
            $http({
                url: "/topicBook/getTopicBookById",
                method: "POST",
                data: {
                    id: id
                }
            }).then(function s(result) {
                if (result.data.status === 0) {
                    topicBasisEditor.setContent(result.data.model.topicBasis);
                    topicGoalEditor.setContent(result.data.model.topicGoal);
                    externalConditionEditor.setContent(result.data.model.externalCondition);
                    reference2Editor.setContent(result.data.model.reference);
                    $http({
                        url: "/verify/getVerifyById",
                        method: "POST",
                        data: {
                            id: result.data.model.adminVerifyId
                        }
                    }).then(function s(result) {
                        if (result.data.status === 0) {
                            $scope.verifyStatus = result.data.model.status.toString();
                            adminExplanation.setContent(result.data.model.explanation);
                        }
                    });
                }
            }, function e() {
                alert("获取任务书填写情况失败");
            })
        }
        ;
        $scope.saveTopicBook = function () {
            $http({
                url: "/topicBook/saveTopicBook",
                data: {
                    id: $scope.oneTopic.id,
                    topicBasis: topicBasisEditor.getContent(),
                    topicGoal: topicGoalEditor.getContent(),
                    externalCondition: externalConditionEditor.getContent(),
                    reference: reference2Editor.getContent()
                },
                method: "POST"
            }).then(function s(result) {
                alert(result.data.message);
            }, function e() {
                alert("保存失败，网络不通");
            })
        }
    })
;
