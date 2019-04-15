angular.module('studentApp', ['ui.router', 'oc.lazyLoad'])
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
        $scope.whoIsActive = 0;
        $scope.iAmActive = function (who) {
            $scope.whoIsActive = who;
        };
        $scope.logout = function () {

        }
    })
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when("", "userOverview");//用作默认跳转
        $stateProvider
            .state("securitySetting", {
                controller: "securitySettingCtrl",
                url: "/securitySetting",
                templateUrl: '/gttsss/securitySetting.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/securitySetting.js', '/gttsss/css/securitySetting.css']
                        })
                    }]
                }
            })
            .state("userOverview", {
                controller: "userOverviewCtrl",
                url: "/userOverview",
                templateUrl: '/gttsss/userOverview.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/userOverview.js', '/gttsss/css/userOverview.css']
                        })
                    }]
                }
            })
            .state("selectTopic", {
                controller: "selectTopicCtrl",
                url: "/selectTopic",
                templateUrl: '/gttsss/selectTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/selectTopic.js', '/gttsss/css/selectTopic.css']
                        })
                    }]
                }
            })
            .state("customizeTopic", {
                controller: "customizeTopicCtrl",
                url: "/customizeTopic",
                templateUrl: '/gttsss/customizeTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/customizeTopic.js', '/gttsss/css/customizeTopic.css']
                        })
                    }]
                }
            })
            // .state("selectTopic", {
            //     controller: "selectTopicCtrl",
            //     url: "/selectTopic",
            //     templateUrl: '/gttsss/selectTopic.html',
            //     resolve: {
            //         loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
            //             return $ocLazyLoad.load({
            //                 files: ['/gttsss/js/selectTopic.js', '/gttsss/css/selectTopic.css']
            //             })
            //         }]
            //     }
            // })
            // .state("selectTopic", {
            //     controller: "selectTopicCtrl",
            //     url: "/selectTopic",
            //     templateUrl: '/gttsss/selectTopic.html',
            //     resolve: {
            //         loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
            //             return $ocLazyLoad.load({
            //                 files: ['/gttsss/js/selectTopic.js', '/gttsss/css/selectTopic.css']
            //             })
            //         }]
            //     }
            // })
    });