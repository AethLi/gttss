angular.module('studentApp', [])
    .controller("selectTopicCtrl", function ($scope, $http) {
        let contentEditor = UM.getEditor("contentEditor");
        let resultEditor = UM.getEditor('resultEditor');
        let referenceEditor = UM.getEditor('referenceEditor');
        let planEditor = UM.getEditor('planEditor');
        $scope.allTopic;
        $scope.topics;
        $scope.oneTopic;
        $scope.hasSelect = false;
        $scope.selectedTopic;
        $http({
            url: "/topic/querySelectAbleTopic",
            method: "GET"
        }).then(function s(result) {
            if (result.data.status == 0) {
                $scope.allTopic = result.data.model;
                $scope.topics = $scope.allTopic;
            } else {
                alert(result.data.message);
            }
        }, function e(result) {

        });
        $scope.topicDetail = function (id) {
            $scope.oneTopic = null;
            $http({
                url: "/topic/getTopicById",
                method: "POST",
                data: {
                    "id": id
                }
            }).then(function s(result) {
                if (result.data.status == 0) {
                    $scope.oneTopic = result.data.model;
                    contentEditor.setContent(result.data.model.content);
                    resultEditor.setContent(result.data.model.result);
                    referenceEditor.setContent(result.data.model.reference);
                    planEditor.setContent(result.data.model.plan);
                } else {
                    alert(result.data.message);
                    return
                }
            }, function e(result) {
                alert("网络错误");
                return
            });
            $('#topicDetail').modal();
        };

        $scope.giveMeThisTopic = function () {
            if ($scope.oneTopic == undefined || $scope.oneTopic.id == undefined) {
                alert("参数错误");
                return;
            } else {
                $http({
                    url: "/topic/selectTopic",
                    method: "POST",
                    data: {
                        id: $scope.oneTopic.id
                    }
                }).then(function s(result) {
                    if (result.data.status === 0) {
                        alert(result.data.message);
                        location.reload();
                    } else {
                        alert(result.data.message);
                    }
                }, function e(result) {
                    alert("网络错误");
                });
            }
        };

        $scope.cancelTopic = function () {
            $http({
                url: "/topic/cancelTopic",
                method: "POST",
            }).then(function s(result) {
                if (result.data.status === 0) {
                    alert(result.data.message);
                    location.reload();
                } else {
                    alert(result.data.message);
                }
            }, function e(result) {
                alert("网络错误");
            });
        };

        $http({
            url: "/topic/getMyTopic",
            method: "POST",
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.hasSelect = result.data.model.hasSelect;
                $scope.selectedTopic = result.data.model.topic.name;
            }
        }, function e(result) {

        })
    });
