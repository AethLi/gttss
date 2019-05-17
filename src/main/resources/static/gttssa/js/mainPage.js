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
                templateUrl: './securitySetting.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/securitySetting.js', './css/securitySetting.css']
                        })
                    }]
                }
            })
            .state("allTopic", {
                controller: "allTopicCtrl",
                url: "/allTopic",
                templateUrl: './allTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/allTopic.js', './css/allTopic.css']
                        })
                    }]
                }
            })
            .state("currentBatchTopic", {
                controller: "currentBatchTopicCtrl",
                url: "/currentBatchTopic",
                templateUrl: './currentBatchTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/currentBatchTopic.js', './css/currentBatchTopic.css']
                        })
                    }]
                }
            })
            .state("customizeTopicVerify", {
                controller: "customizeTopicVerifyCtrl",
                url: "/customizeTopicVerify",
                templateUrl: './customizeTopicVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/customizeTopicVerify.js', './css/customizeTopicVerify.css']
                        })
                    }]
                }
            })
            .state("topicBookVerify", {
                controller: "topicBookVerifyCtrl",
                url: "/topicBookVerify",
                templateUrl: './topicBookVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/topicBookVerify.js', './css/topicBookVerify.css']
                        })
                    }]
                }
            })
            .state("openingReportVerify", {
                controller: "openingReportVerifyCtrl",
                url: "/openingReportVerify",
                templateUrl: './openingReportVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/openingReportVerify.js', './css/openingReportVerify.css']
                        })
                    }]
                }
            })
            .state("defenseVerify", {
                controller: "defenseVerifyCtrl",
                url: "/defenseVerify",
                templateUrl: './defenseVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/defenseVerify.js', './css/defenseVerify.css']
                        })
                    }]
                }
            })
            .state("resultCheck", {
                controller: "resultCheckCtrl",
                url: "/resultCheck",
                templateUrl: './resultCheck.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/resultCheck.js', './css/resultCheck.css']
                        })
                    }]
                }
            })
    });