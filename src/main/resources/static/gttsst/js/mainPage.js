angular.module('teacherApp', ['ui.router', 'oc.lazyLoad'])
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
        // $urlRouterProvider.when("", "");//用作默认跳转
        $stateProvider
            .state("userOverview", {
                url: "/userOverview",
                templateUrl: './userOverview.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['../js/userOverview.js']
                        })
                    }]
                }
            })
    });