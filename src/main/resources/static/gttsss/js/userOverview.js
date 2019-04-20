angular.module('studentApp', [])
    .controller("userOverviewCtrl", function ($scope, $http) {
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
        $scope.saveUserOverview = function () {
            $http({
                url: "/user/getMyOverview",
                method: "POST",
                params: {
                    name: $scope.name,
                }
            }).then(function s(result) {
                if (result.data.status == 0) {
                    alert("修改成功");
                } else {
                    alert(result.data.message);
                }
            }, function e(result) {
                alert(result.data.message);
            })
        }
    });
