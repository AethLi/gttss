angular.module('adminApp', [])
    .controller("customizeTopicVerifyCtrl", function ($scope, $http) {
        $scope.topics = [];
        let contentEditor = UM.getEditor('content');
        let resultEditor = UM.getEditor('result');
        let referenceEditor = UM.getEditor('reference');
        let planEditor = UM.getEditor('plan');
        let guideEditor = UM.getEditor('guide');
        let teacherExplanation = UM.getEditor('teacherExplanation');

        $scope.verifyStatus;

        $scope.oneTopic;

        $http({
            url: "/topic/getAdminVerifyTopic",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topics = result.data.model;
            } else {
                alert(result.data.message);
            }
        }, function e(result) {
            alert("网络错误，获取教师审核列表失败");
        });

        $scope.saveTeacherExplanation = function () {
            $http({
                url: "/verify/customizeTopicAdminVerify",
                method: "POST",
                data: {
                    topicId: $scope.oneTopic.id,
                    status: $scope.verifyStatus,
                    explanation: teacherExplanation.getContent()
                }
            }).then(function s(result) {
                alert(result.data.message);
            }, function e(result) {
                alert("network error");
            })
        };

        $scope.topicVerify = function (id) {
            //todo 确认UM加载方式
            $scope.oneTopic = "";
            contentEditor.setContent("");
            resultEditor.setContent("");
            referenceEditor.setContent("");
            planEditor.setContent("");
            guideEditor.setContent("");
            $http({
                url: "/topic/getTopicById",
                method: "POST",
                data: {
                    id: id
                }
            }).then(function s(result) {
                if (result.data.status === 0) {
                    $scope.oneTopic = result.data.model;
                    contentEditor.setContent($scope.oneTopic.content);
                    resultEditor.setContent($scope.oneTopic.result);
                    referenceEditor.setContent($scope.oneTopic.reference);
                    planEditor.setContent($scope.oneTopic.plan);
                    guideEditor.setContent($scope.oneTopic.guide);
                    if (result.data.model.teacherVerify !== "") {
                        $http({
                            url: "/verify/getVerifyById",
                            data: {
                                id: result.data.model.adminVerify
                            },
                            method: "POST"
                        }).then(function s(result) {
                            if (result.data.status === 0) {
                                $scope.verifyStatus = result.data.model.status.toString();
                                teacherExplanation.setContent(result.data.model.explanation);
                            }
                        }, function e() {
                            alert("网络错误");
                        })
                    }
                    $('#topicDetail').modal();
                } else {
                    alert(result.data.message);
                }
            }, function e(result) {
                alert("网络错误");
            })
        };
    });
