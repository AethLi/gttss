angular.module('studentApp', ['ui.router', 'oc.lazyLoad'])
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

        $http({
            url: "/user/getMyself",
            method: "POST"
        }).then(function success(result) {
            if (result.data.status === 0) {
                $scope.userName = result.data.model.userName;
                $scope.classGrade = result.data.model.classGrade;
            } else {
                alert(result.data.message);
                $scope.logout();
            }
        }, function error(result) {
            alert(result.data.message);
            $scope.logout();
        });

        $scope.iAmActive = function (who) {
            $scope.whoIsActive = who;
        };

        $scope.logout = function () {
            $http({
                url: "/user/logout",
                method: "GET"
            }).then(function success(result) {
                if (result.data.status === 0) {
                    window.location.href = "/";
                } else {
                    alert(result.data.message);
                    window.location.href = "/";
                }
            }, function error(result) {
                alert(result.data.message);
                window.location.href = "/";
            });
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
            .state("openingReport", {
                controller: "openingReportCtrl",
                url: "/openingReport",
                templateUrl: '/gttsss/openingReport.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/openingReport.js', '/gttsss/css/openingReport.css']
                        })
                    }]
                }
            })
            .state("openingReportVerify", {
                controller: "openingReportVerifyCtrl",
                url: "/openingReportVerify",
                templateUrl: '/gttsss/openingReportVerify.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/openingReportVerify.js', '/gttsss/css/openingReportVerify.css']
                        })
                    }]
                }
            })
            .state("teachPlaceAndDatetime", {
                controller: "teachPlaceAndDatetimeCtrl",
                url: "/teachPlaceAndDatetime",
                templateUrl: '/gttsss/teachPlaceAndDatetime.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/teachPlaceAndDatetime.js', '/gttsss/css/teachPlaceAndDatetime.css']
                        })
                    }]
                }
            })
            .state("midTermCheck", {
                controller: "midTermCheckCtrl",
                url: "/midTermCheck",
                templateUrl: '/gttsss/midTermCheck.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/midTermCheck.js', '/gttsss/css/midTermCheck.css']
                        })
                    }]
                }
            })
            .state("defenseDraftUpload", {
                controller: "defenseDraftUploadCtrl",
                url: "/defenseDraftUpload",
                templateUrl: '/gttsss/defenseDraftUpload.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/defenseDraftUpload.js', '/gttsss/css/defenseDraftUpload.css']
                        })
                    }]
                }
            })
            .state("defenseApply", {
                controller: "defenseApplyCtrl",
                url: "/defenseApply",
                templateUrl: '/gttsss/defenseApply.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/defenseApply.js', '/gttsss/css/defenseApply.css']
                        })
                    }]
                }
            })
        // .state("openingReportVerify", {
        //     controller: "openingReportVerifyCtrl",
        //     url: "/openingReportVerify",
        //     templateUrl: '/gttsss/openingReportVerify.html',
        //     resolve: {
        //         loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
        //             return $ocLazyLoad.load({
        //                 files: ['/gttsss/js/openingReportVerify.js', '/gttsss/css/openingReportVerify.css']
        //             })
        //         }]
        //     }
        // })
    });