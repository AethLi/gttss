angular.module('teacherApp', [])
    .controller("defenseVerifyCtrl", function ($scope, $http) {
        $scope.topics = [];

        let teacherExplanation = UM.getEditor("teacherExplanation");
        let reasonEditor = UM.getEditor("reasonEditor");

        $scope.verifyStatus;
        $scope.files = [];

        $http({
            url: "/topic/getMyTopicStudentT",
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


        $scope.topicDetail = function (id) {
            $http({
                url: "/topic/getTopicById",
                method: "POST",
                data: {
                    id: id
                }
            }).then(function s(result) {
                if (result.data.status === 0) {
                    $scope.oneTopic = result.data.model;

                    $http({
                        url: "/defense/getDefenseApplyById",
                        data: {
                            id: $scope.oneTopic.id
                        },
                        method: "POST",
                    }).then(function s(result) {
                        if (result.data.status === 0) {
                            reasonEditor.setContent(result.data.model.reason);
                            $http({
                                url: "/verify/getVerifyById",
                                data: {
                                    id: result.data.model.teacherVerify
                                },
                                method: "POST"
                            }).then(function s(result) {
                                if (result.data.status === 0) {
                                    $scope.verifyStatus = result.data.model.status.toString();
                                    teacherExplanation.setContent(result.data.model.explanation);
                                }
                            }, function e() {
                                // alert("网络错误");
                            });
                        }
                    }, function e() {
                    });
                    $scope.files.length = 0;
                    $http({
                        url: "/file/getDefenseDraftById",
                        data: {
                            id: $scope.oneTopic.id
                        },
                        method: "POST"
                    }).then(function s(result) {
                        if (result.data.status === 0) {
                            $scope.files.push(result.data.model.file)
                        }
                    }, function e() {

                    });
                    $('#topicDetail').modal();
                } else {
                    alert(result.data.message);
                }
            }, function e(result) {
                alert("网络错误");
            })
        };
        $scope.saveVerify = function () {
            $http({
                url: '/verify/saveDefenseVerify',
                data: {
                    id: $scope.oneTopic.id,
                    status: $scope.verifyStatus,
                    explanation: teacherExplanation.getContent()
                },
                method: "POST"
            }).then(function s(result) {
                alert(result.data.message);
            }, function e() {
                alert("保存失败。网络错误");
            })
        }
    });
