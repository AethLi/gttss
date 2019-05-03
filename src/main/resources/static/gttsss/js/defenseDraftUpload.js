angular.module('studentApp', [])
    .controller("defenseDraftUploadCtrl", function ($scope, $http) {

        $scope.files = [];

        $scope.defenseDraftUpload = function () {
            let form = new FormData();
            form.append("file", $('#file')[0].files[0]);
            $http({
                method: 'POST',
                url: '/file/defenseDraftUpload',
                data: form,
                headers: {'Content-Type': undefined},
                transformRequest: angular.identity
            }).then(function s(result) {
                alert(result.data.message);
            }, function e() {
                alert("保存失败，网络错误");
            })
        };
        $http({
            url: "/file/getMyDefenseDraft",
            method: "POST",
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.files = [];
                $scope.files.push(result.data.model.file);
            }
        }, function e(result) {

        });
        $scope.download = function (id) {
            $http({
                url: "/file/downloadById",
                data: {
                    id: id
                },
                method: "POST"
            }).then(function s(result) {

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
