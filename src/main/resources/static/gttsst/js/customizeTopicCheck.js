angular.module('teacherApp', [])
    .controller("customizeTopicCheckCtrl", function ($scope, $http) {

        $scope.teacherCustomize = false;

        $scope.oneTopic = "";
        $scope.verifyStatus;

        let contentEditor = UM.getEditor('content');
        let resultEditor = UM.getEditor('result');
        let referenceEditor = UM.getEditor('reference');
        let planEditor = UM.getEditor('plan');
        let guideEditor = UM.getEditor('guide');
        let adminExplanation = UM.getEditor("adminExplanation");
        $scope.topics = [];
        $http({
            url: "/topic/teacherGetTopicVerifyCheck",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topics = result.data.model;
            }
        }, function e() {
            alert("网络错误");
        });
        $scope.topicDetail = function (id) {
            $http({
                url: "/topic/getTopicById",
                data: {
                    id: id
                },
                method: "POST"
            }).then(function s(result) {
                if (result.data.status === 0) {
                    $scope.oneTopic = result.data.model;
                    contentEditor.setContent($scope.oneTopic.content);
                    resultEditor.setContent($scope.oneTopic.result);
                    referenceEditor.setContent($scope.oneTopic.reference);
                    planEditor.setContent($scope.oneTopic.plan);
                    guideEditor.setContent($scope.oneTopic.guide);
                    $scope.teacherCustomize = result.data.model.status === 4;
                    if (result.data.model.adminVerify !== "") {
                        $http({
                            url: "/verify/getVerifyById",
                            data: {
                                id: result.data.model.adminVerify
                            },
                            method: "POST"
                        }).then(function s(result) {
                            if (result.data.status === 0) {
                                $scope.verifyStatus = result.data.model.status.toString();
                                adminExplanation.setContent(result.data.model.explanation);
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

            })
        }
    });