angular.module('studentApp', [])
    .controller("defenseApplyCtrl", function ($scope, $http) {
        let applyReason = UM.getEditor('applyReason');
        let teacherVerifyEditor = UM.getEditor("teacherVerifyEditor");
        let adminVerifyEditor = UM.getEditor("adminVerifyEditor");

        $scope.teacherVerifyStatusName;
        $scope.adminVerifyStatusName;

        $http({
            url: "/defense/getMyDefenseApply",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status === 0) {
                applyReason.setContent(result.data.model.defenseApply.reason);
                teacherVerifyEditor.setContent(result.data.model.teacherVerify.explanation);
                adminVerifyEditor.setContent(result.data.model.adminVerify.explanation);
                if (result.data.model.teacherVerify.status === 0) {
                    $scope.teacherVerifyStatusName = "已通过";
                } else {
                    $scope.teacherVerifyStatusName = "未通过";
                }
                if (result.data.model.adminVerify.status === 0) {
                    $scope.adminVerifyStatusName = "已通过";
                } else {
                    $scope.adminVerifyStatusName = "未通过";
                }
            }
        }, function e() {

        });

        $scope.name = "";
        $scope.studentNum = "";
        $scope.className = "";
        $scope.grade = "";
        $scope.faculty = "";
        $scope.major = "";
        $http({
            url: "/user/getMyOverview",
            method: "POST"
        }).then(function s(result) {
            $scope.name = result.data.model.name;
            $scope.studentNum = result.data.model.studentNum;
            $scope.className = result.data.model.className;
            $scope.grade = result.data.model.grade;
            $scope.faculty = result.data.model.faculty;
            $scope.major = result.data.model.major;
        });

        $scope.topicName = "";
        $http({
            url: "/topic/getMyTopic",
            method: "GET"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topicName = result.data.model.topic.name;
            } else {
                alert(result.data.message);
            }
        }, function e(result) {
            // alert("網絡錯誤");
        });
        $scope.saveDefenseApply = function () {
            $http({
                url: "/defense/saveDefenseApply",
                method: "POST",
                data: {
                    reason: applyReason.getContent()
                }
            }).then(function s(result) {
                alert(result.data.message);
            }, function e(result) {
                alert("保存失败，网络错误");
            })
        }
    });
