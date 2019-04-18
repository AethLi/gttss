angular.module('teacherApp', ['ui.router', 'oc.lazyLoad'])
    .controller('mainPageCtrl', function ($scope) {
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

        }
    })
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when("", "userOverview");//用作默认跳转
        $stateProvider
            .state("securitySetting", {
                controller: "securitySettingCtrl",
                url: "/securitySetting",
                templateUrl: '/gttsst/securitySetting.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/securitySetting.js', '/gttsst/css/securitySetting.css']
                        })
                    }]
                }
            })
            .state("userOverview", {
                controller: "userOverviewCtrl",
                url: "/userOverview",
                templateUrl: '/gttsst/userOverview.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/userOverview.js', '/gttsst/css/historyTopic.css']
                        })
                    }]
                }
            })
            .state("historyTopic", {
                controller: "historyTopicCtrl",
                url: "/historyTopic",
                templateUrl: '/gttsst/historyTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/historyTopic.js', '/gttsst/css/historyTopic.css']
                        })
                    }]
                }
            })
            .state("customizeTopic", {
                controller: "customizeTopicCtrl",
                url: "/customizeTopic",
                templateUrl: '/gttsst/customizeTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/customizeTopic.js', '/gttsst/css/customizeTopic.css']
                        })
                    }]
                }
            })
            .state("customizeTopicVerify", {
                controller: "customizeTopicVerifyCtrl",
                url: "/customizeTopicVerify",
                templateUrl: '/gttsst/customizeTopicVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/customizeTopicVerify.js', '/gttsst/css/customizeTopicVerify.css']
                        })
                    }]
                }
            })
            .state("customizeTopicCheck", {
                controller: "customizeTopicCheckCtrl",
                url: "/userOverview",
                templateUrl: '/gttsst/customizeTopicCheck.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/customizeTopicCheck.js', '/gttsst/css/customizeTopicCheck.css']
                        })
                    }]
                }
            })
            .state("topicBook", {
                controller: "topicBookCtrl",
                url: "/topicBook",
                templateUrl: '/gttsst/topicBook.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/topicBook.js', '/gttsst/css/topicBook.css']
                        })
                    }]
                }
            })
            .state("openingReportVerify", {
                controller: "openingReportVerifyCtrl",
                url: "/openingReportVerify",
                templateUrl: '/gttsst/openingReportVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/openingReportVerify.js', '/gttsst/css/openingReportVerify.css']
                        })
                    }]
                }
            })
            .state("openingReportGroup", {
                controller: "openingReportGroupCtrl",
                url: "/openingReportGroup",
                templateUrl: '/gttsst/openingReportGroup.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/openingReportGroup.js', '/gttsst/css/openingReportGroup.css']
                        })
                    }]
                }
            })
            .state("guideRecord", {
                controller: "guideRecordCtrl",
                url: "/guideRecord",
                templateUrl: '/gttsst/guideRecord.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/guideRecord.js', '/gttsst/css/guideRecord.css']
                        })
                    }]
                }
            })
            .state("midTermCheck", {
                controller: "midTermCheckCtrl",
                url: "/midTermCheck",
                templateUrl: '/gttsst/midTermCheck.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/midTermCheck.js', '/gttsst/css/midTermCheck.css']
                        })
                    }]
                }
            })
            .state("defenseDraftVerify", {
                controller: "defenseDraftVerifyCtrl",
                url: "/defenseDraftVerify",
                templateUrl: '/gttsst/defenseDraftVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsst/js/defenseDraftVerify.js', '/gttsst/css/defenseDraftVerify.css']
                        })
                    }]
                }
            })
        .state("defenseVerify", {
            controller: "defenseVerifyCtrl",
            url: "/defenseVerify",
            templateUrl: '/gttsst/defenseVerify.html',
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['/gttsst/js/defenseVerify.js', '/gttsst/css/defenseVerify.css']
                    })
                }]
            }
        })
        // .state("guideRecord", {
        //     controller: "guideRecordCtrl",
        //     url: "/guideRecord",
        //     templateUrl: '/gttsst/guideRecord.html',
        //     resolve: {
        //         loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
        //             return $ocLazyLoad.load({
        //                 files: ['/gttsst/js/guideRecord.js', '/gttsst/css/guideRecord.css']
        //             })
        //         }]
        //     }
        // })
    });