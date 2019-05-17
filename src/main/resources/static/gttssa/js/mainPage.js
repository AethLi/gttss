angular.module('adminApp', ['ui.router', 'oc.lazyLoad'])
    .controller('mainPageCtrl', function ($scope, $http) {
        layui.use(['element', 'layer'], function () {
            var element = layui.element;
            var layer = layui.layer;
            //监听折叠
            element.on('collapse(test)', function (data) {
                // layer.msg('展开状态：'+ data.show);
            });
        });
        $scope.userName = "";
        $scope.classGrade = "";
        $scope.whoIsActive = 0;


        $scope.iAmActive = function (who) {
            $scope.whoIsActive = who;
        };

        $scope.logout = function () {
            $http({
                url: "/user/logout",
                method: "GET"
            }).then(function success(result) {
                if (result.data.status === 0) {
                    window.location.href = "/a";
                } else {
                    alert(result.data.message);
                    window.location.href = "/a";
                }
            }, function error(result) {
                alert(result.data.message);
                window.location.href = "/a";
            });
        }
    })
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when("", "userOverview");//用作默认跳转
        $stateProvider
            .state("securitySetting", {
                controller: "securitySettingCtrl",
                url: "/securitySetting",
                templateUrl: '/gttssa/securitySetting.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttssa/js/securitySetting.js', '/gttssa/css/securitySetting.css']
                        })
                    }]
                }
            })
            .state("allTopic", {
                controller: "allTopicCtrl",
                url: "/allTopic",
                templateUrl: '/gttssa/allTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttssa/js/allTopic.js', '/gttssa/css/allTopic.css']
                        })
                    }]
                }
            })
            .state("currentBatchTopic", {
                controller: "currentBatchTopicCtrl",
                url: "/currentBatchTopic",
                templateUrl: '/gttssa/currentBatchTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttssa/js/currentBatchTopic.js', '/gttssa/css/currentBatchTopic.css']
                        })
                    }]
                }
            })
            .state("customizeTopicVerify", {
                controller: "customizeTopicVerifyCtrl",
                url: "/customizeTopicVerify",
                templateUrl: '/gttssa/customizeTopicVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttssa/js/customizeTopicVerify.js', '/gttssa/css/customizeTopicVerify.css']
                        })
                    }]
                }
            })
            .state("topicBookVerify", {
                controller: "topicBookVerifyCtrl",
                url: "/topicBookVerify",
                templateUrl: '/gttssa/topicBookVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttssa/js/topicBookVerify.js', '/gttssa/css/topicBookVerify.css']
                        })
                    }]
                }
            })
            .state("openingReportVerify", {
                controller: "openingReportVerifyCtrl",
                url: "/openingReportVerify",
                templateUrl: '/gttssa/openingReportVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttssa/js/openingReportVerify.js', '/gttssa/css/openingReportVerify.css']
                        })
                    }]
                }
            })
    });