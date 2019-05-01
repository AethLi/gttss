angular.module('teacherApp', [])
    .controller("topicBookCtrl", function ($scope, $http) {
        let topicBookEditor = UM.getEditor("topicBookEditor");

        let contentEditor = UM.getEditor("contentEditor");
        let resultEditor = UM.getEditor("resultEditor");
        let referenceEditor = UM.getEditor("referenceEditor");
        let planEditor = UM.getEditor("planEditor");
        let guideEditor = UM.getEditor("guideEditor");

        let topicBasisEditor = UM.getEditor("topicBasisEditor");
        let topicGoalEditor = UM.getEditor("topicGoalEditor");
        let externalConditionEditor = UM.getEditor("externalConditionEditor");
        let reference2Editor = UM.getEditor("reference2Editor");


        $scope.topicBasis = "";
        $scope.topicGoal = "";
        $scope.externalCondition = "";
        $scope.reference = "";

        $scope.topics = [];

        $scope.oneTopic;

        $http({
            url: "/topic/getMyTopicT",
            method: "POST"
        }).then(function s(result) {
            if (result.data.status === 0) {
                $scope.topics = result.data.model;
            }
        }, function e() {
            alert("网络错误");
        });
        $scope.topicDetail = function (id) {
            contentEditor.setContent("");
            resultEditor.setContent("");
            referenceEditor.setContent("");
            planEditor.setContent("");
            guideEditor.setContent("");
            $scope.oneTopic = "";
            $http({
                url: "/topic/getTopicById",
                data: {
                    id: id
                },
                method: "POST"
            }).then(function s(result) {
                if (result.data.status === 0) {
                    $scope.oneTopic = result.data.model;
                    contentEditor.setContent(result.data.model.content);
                    resultEditor.setContent(result.data.model.result);
                    referenceEditor.setContent(result.data.model.reference);
                    planEditor.setContent(result.data.model.plan);
                    guideEditor.setContent(result.data.model.guide);
                    $("#topicDetail").modal();
                } else {
                    alert(result.data.message);
                }
            }, function e() {
                alert("网络不通");
            });
            $http({
                url: "/topicBook/getTopicBookById",
                method: "POST",
                data: {
                    id: id
                }
            }).then(function s(result) {
                if (result.data.status === 0) {
                    topicBasisEditor.setContent(result.data.model.topicBasis);
                    topicGoalEditor.setContent(result.data.model.topicGoal);
                    externalConditionEditor.setContent(result.data.model.externalCondition);
                    reference2Editor.setContent(result.data.model.reference);
                }
            }, function e() {
                alert("获取任务书填写情况失败");
            })
        };
        $scope.saveTopicBook = function () {
            $http({
                url: "/topicBook/saveTopicBook",
                data: {
                    id: $scope.oneTopic.id,
                    topicBasis: topicBasisEditor.getContent(),
                    topicGoal: topicGoalEditor.getContent(),
                    externalCondition: externalConditionEditor.getContent(),
                    reference: reference2Editor.getContent()
                },
                method: "POST"
            }).then(function s(result) {
                alert(result.data.message);
            }, function e() {
                alert("保存失败，网络不通");
            })
        }
    });
