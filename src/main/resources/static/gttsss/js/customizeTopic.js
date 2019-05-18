angular.module('studentApp', [])
    .controller("customizeTopicCtrl", function ($scope, $http) {
        let customizeTopicEditor = UM.getEditor('customizeTopicEditor');
        let adminExplanation = UM.getEditor('adminExplanation');
        let explanation = UM.getEditor('explanation');
        $scope.topicTypes;
        $scope.topicOrigins;
        $scope.topicPropertis;
        $scope.content = "";
        $scope.result = "";
        $scope.reference = "";
        $scope.plan = "";
        $scope.guide = "";
        $scope.whoIsActive = 0;

        $scope.statusName = "";
        $scope.adminStatusName = "";
        $scope.explanation = "";
        $scope.adminExplanation = "";
        $scope.iAmActive = function (who) {
            if ($scope.whoIsActive === 0) {
                $scope.content = customizeTopicEditor.getContent();
            } else if ($scope.whoIsActive === 1) {
                $scope.result = customizeTopicEditor.getContent();
            } else if ($scope.whoIsActive === 2) {
                $scope.reference = customizeTopicEditor.getContent();
            } else if ($scope.whoIsActive === 3) {
                $scope.plan = customizeTopicEditor.getContent();
            } else if ($scope.whoIsActive === 4) {
                $scope.guide = customizeTopicEditor.getContent();
            }
            customizeTopicEditor.setContent("");
            if (who === 0) {
                customizeTopicEditor.setContent($scope.content);
            } else if (who === 1) {
                customizeTopicEditor.setContent($scope.result);
            } else if (who === 2) {
                customizeTopicEditor.setContent($scope.reference);
            } else if (who === 3) {
                customizeTopicEditor.setContent($scope.plan);
            } else if (who === 4) {
                customizeTopicEditor.setContent($scope.guide);
            }
            $scope.whoIsActive = who;
        };
        $http({
            url: "/topic/getAllSelectForCustomize",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topicTypes = result.data.model.topicTypes;
                $scope.topicOrigins = result.data.model.topicOrigins;
                $scope.topicPropertis = result.data.model.topicPropertis;
                $http({
                    url: '/topic/getMyTopic',
                    method: "POST"
                }).then(function s(result) {
                    if (result.data.status === 0) {
                        if (!result.data.model.hasSelect) {
                            $scope.newTopic = 0;
                            return;
                        }
                        if (result.data.model.topic.status === 3) {
                            $scope.newTopic = 1;
                        } else {
                            alert("非自拟题目或已通过，无法更改");
                            $scope.newTopic = 3;
                        }
                        $scope.topicType = result.data.model.topic.typeId;
                        $scope.topicOrigin = result.data.model.topic.originId;
                        $scope.topicProperty = result.data.model.topic.propertyId;
                        $scope.topicName = result.data.model.topic.name;
                        $scope.compare = result.data.model.topic.compare.toString();
                        $scope.content = result.data.model.topic.content;
                        $scope.result = result.data.model.topic.result;
                        $scope.reference = result.data.model.topic.reference;
                        $scope.plan = result.data.model.topic.plan;
                        $scope.guide = result.data.model.topic.guide;
                        customizeTopicEditor.setContent($scope.content);
                        $scope.teacherName = result.data.model.teacher.name;
                    } else {
                        alert(result.data.message);
                    }
                }, function e(result) {
                    alert("网络错误");
                });
            } else {
                alert(result.data.message);
            }
        }, function e(result) {
            alert("网络错误");
        });
        $http({
            url: "/topic/getCustomizeTopicVerify",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status == 0) {
                if (result.data.model.teacher !== undefined) {
                    explanation.setContent(result.data.model.teacher.explanation);
                    if (result.data.model.teacher.status === 0) {
                        $scope.statusName = "通过";
                        $scope.adminStatusName = "";
                    } else {
                        $scope.statusName = "未通过";
                    }
                } else if (result.data.model.admin !== undefined) {
                    adminExplanation.setContent(result.data.model.admin.explanation);
                    if (result.data.model.admin.status === 0) {
                        $scope.adminStatusName = "通过";
                    } else {
                        $scope.adminStatusName = "未通过";
                    }
                }
            }
        }, function e(result) {
            alert("网络错误");
        });
        $scope.saveTopic = function () {
            if ($scope.whoIsActive === 0) {
                $scope.content = customizeTopicEditor.getContent();
            } else if ($scope.whoIsActive === 1) {
                $scope.result = customizeTopicEditor.getContent();
            } else if ($scope.whoIsActive === 2) {
                $scope.reference = customizeTopicEditor.getContent();
            } else if ($scope.whoIsActive === 3) {
                $scope.plan = customizeTopicEditor.getContent();
            } else if ($scope.whoIsActive === 4) {
                $scope.guide = customizeTopicEditor.getContent();
            }
            $http({
                url: "/topic/saveCustomizeTopic",
                method: "POST",
                data: {
                    typeId: $scope.topicType,
                    originId: $scope.topicOrigin,
                    propertyId: $scope.topicProperty,
                    name: $scope.topicName,
                    teacherName: $scope.teacherName,
                    compare: $scope.compare,
                    content: $scope.content,
                    result: $scope.result,
                    reference: $scope.reference,
                    plan: $scope.plan,
                    guide: $scope.guide
                }
            }).then(function s(result) {
                alert(result.data.message);
                location.reload();
            }, function e(result) {
                alert("网络错误");
            })
        }
    });
