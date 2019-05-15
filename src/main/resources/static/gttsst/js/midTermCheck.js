angular.module('studentApp', [])
    .controller("midTermCheckCtrl", function ($scope, $http) {
        $scope.topics = [];
        let summaryEditor = UM.getEditor('summaryEditor');

        $scope.comment = "12131";
        $scope.oneTopic;

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
                        url: "/mediumTermRecord/getMediumTermRecordById",
                        data: {
                            id: $scope.oneTopic.id
                        },
                        method: "POST"
                    }).then(function s(result) {
                        if (result.data.status === 0) {
                            $scope.comment = result.data.model.comment.toString();
                            summaryEditor.setContent(result.data.model.summary);
                        }
                    }, function e() {
                        alert("网络错误");
                    });
                    $('#topicDetail').modal();
                } else {
                    alert(result.data.message);
                }
            }, function e(result) {
                alert("网络错误");
            })
        };
    });
