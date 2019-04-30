angular.module('teacherApp', [])
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
                $scope.teacherName = result.data.model.teacherName;
            } else {
                alert(result.data.message);
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
