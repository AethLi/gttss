angular.module('studentApp', [])
    .controller("midTermCheckCtrl", function ($scope, $http) {
        let preWordSummary = UM.getEditor('preWordSummary');

        $scope.comment = "12131";

        //获取我的中期检查
        $http({
            url: "/mediumTermRecord/getMyMediumTermRecord",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status === 0) {
                preWordSummary.setContent(result.data.model.summary);
                $scope.comment = result.data.model.comment.toString();
            }
        }, function e(result) {
            alert("網絡錯誤");
        });

        $scope.saveMediumTermRecord = function () {
            $http({
                url: "/mediumTermRecord/saveMediumTermRecord",
                data: {
                    comment: $scope.comment,
                    summary: preWordSummary.getContent()
                },
                method: "POST"
            }).then(function s(result) {
                alert(result.data.message);
            }, function e(result) {
                alert("網絡錯誤");
            })
        };

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
    });
