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
                            files: ['./js/userOverview.js', './css/userOverview.css']
                        })
                    }]
                }
            })
            .state("selectTopic", {
                controller: "selectTopicCtrl",
                url: "/selectTopic",
                templateUrl: './selectTopic.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/selectTopic.js', './css/selectTopic.css']
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
            .state("openingReport", {
                controller: "openingReportCtrl",
                url: "/openingReport",
                templateUrl: './openingReport.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/openingReport.js', './css/openingReport.css']
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
            .state("teachPlaceAndDatetime", {
                controller: "teachPlaceAndDatetimeCtrl",
                url: "/teachPlaceAndDatetime",
                templateUrl: './teachPlaceAndDatetime.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/teachPlaceAndDatetime.js', './css/teachPlaceAndDatetime.css']
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
            .state("defenseDraftUpload", {
                controller: "defenseDraftUploadCtrl",
                url: "/defenseDraftUpload",
                templateUrl: './defenseDraftUpload.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/defenseDraftUpload.js', './css/defenseDraftUpload.css']
                        })
                    }]
                }
            })
            .state("defenseApply", {
                controller: "defenseApplyCtrl",
                url: "/defenseApply",
                templateUrl: './defenseApply.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/defenseApply.js', './css/defenseApply.css']
                        })
                    }]
                }
            })
            .state("resultSubmit", {
                controller: "resultSubmitCtrl",
                url: "/resultSubmit",
                templateUrl: './resultSubmit.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['./js/resultSubmit.js', './css/resultSubmit.css']
                        })
                    }]
                }
            })
    });