angular.module('studentApp', [])
    .controller("selectTopicCtrl", function ($scope, $http) {
        $http({
            url: "/topic/queryCurrentTopic",
            method: "GET"
        }).then(function s() {

        }, function e() {

        });
        $scope.hasSelect = false;
        $scope.selectedTopic;
        $scope.topics = [
            {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }, {
                teacherName: "蔡超",
                connectionNum: "18980287516",
                title: "基于VTK的医学影像面绘制设计与实现",
                statusName: "已被选"
            }
        ]
    });
