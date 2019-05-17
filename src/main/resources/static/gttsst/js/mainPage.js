angular.module('teacherApp', ['ui.router', 'oc.lazyLoad'])
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
        $scope.userPermission = "";
        $scope.logout = function () {
            $http({
                url: "/user/logout",
                method: "GET"
            }).then(function success(result) {
                if (result.data.status === 0) {
                    window.location.href = "/t";
                } else {
                    alert(result.data.message);
                    window.location.href = "/t";
                }
            }, function error(result) {
                alert(result.data.message);
                window.location.href = "/t";
            });
        };
        $http({
            url: "/user/getMyself",
            method: "POST"
        }).then(function success(result) {
            if (result.data.status === 0) {
                $scope.userName = result.data.model.userName;
                $scope.unitName = result.data.model.unitName;
            } else {
                alert(result.data.message);
                $scope.logout();
            }
        }, function error(result) {
            alert(result.data.message);
            $scope.logout();
        });
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
            .state("userOverview", {
                controller: "userOverviewCtrl",
                url: "/userOverview",
                templateUrl: './userOverview.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/userOverview.js', './css/historyTopic.css']
                        })
                    }]
                }
            })
            .state("historyTopic", {
                controller: "historyTopicCtrl",
                url: "/historyTopic",
                templateUrl: './historyTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/historyTopic.js', './css/historyTopic.css']
                        })
                    }]
                }
            })
            .state("customizeTopic", {
                controller: "customizeTopicCtrl",
                url: "/customizeTopic",
                templateUrl: './customizeTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/customizeTopic.js', './css/customizeTopic.css']
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
            .state("customizeTopicCheck", {
                controller: "customizeTopicCheckCtrl",
                url: "/customizeTopicCheck",
                templateUrl: './customizeTopicCheck.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/customizeTopicCheck.js', './css/customizeTopicCheck.css']
                        })
                    }]
                }
            })
            .state("topicBook", {
                controller: "topicBookCtrl",
                url: "/topicBook",
                templateUrl: './topicBook.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/topicBook.js', './css/topicBook.css']
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
            .state("openingReportGroup", {
                controller: "openingReportGroupCtrl",
                url: "/openingReportGroup",
                templateUrl: './openingReportGroup.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/openingReportGroup.js', './css/openingReportGroup.css']
                        })
                    }]
                }
            })
            .state("guideRecord", {
                controller: "guideRecordCtrl",
                url: "/guideRecord",
                templateUrl: './guideRecord.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/guideRecord.js', './css/guideRecord.css']
                        })
                    }]
                }
            })
            .state("midTermCheck", {
                controller: "midTermCheckCtrl",
                url: "/midTermCheck",
                templateUrl: './midTermCheck.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/midTermCheck.js', './css/midTermCheck.css']
                        })
                    }]
                }
            })
            .state("defenseDraftVerify", {
                controller: "defenseDraftVerifyCtrl",
                url: "/defenseDraftVerify",
                templateUrl: './defenseDraftVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/defenseDraftVerify.js', './css/defenseDraftVerify.css']
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