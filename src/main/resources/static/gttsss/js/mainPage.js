angular.module('studentApp', ['ui.router', 'oc.lazyLoad'])
    .controller('mainPageCtrl', function ($scope) {
        $scope.userName = "";
        $scope.userPermission = "";
        $scope.whoIsActive = 0;
        $scope.iAmActive = function (who) {
            $scope.whoIsActive = who;
        };
        $scope.logout=function () {
            
        }
    })
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when("", "userOverview");//用作默认跳转
        $stateProvider
            .state("userOverview", {
                controller:"userOverviewCtrl",
                url: "/userOverview",
                templateUrl: '/gttsss/userOverview.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['/gttsss/js/userOverview.js','/gttsss/css/userOverview.css']
                        })
                    }]
                }
            })
    });