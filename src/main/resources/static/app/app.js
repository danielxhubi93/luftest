App.controller('docController',
    ['$scope', '$rootScope','docService','$http', function($scope, $rootScope, docService, $http) {

        $scope.file = '';

        $scope.upload = function(){
            var file = $scope.file;
            docService.saveDoc(file)
                .then(
                    function (response) {
                        alert(response.data.response);
                    },
                    function (errResponse) {

                    }
                );
        }
    }
    ]);

App.constant('urls', {
    DOC_URL: 'http://localhost:1212/api/books/'
});

App.directive('fileModel', [ '$parse', function($parse) {
    return {
        restrict : 'A',
        link : function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
} ]);

App.run(function($rootScope, $http) {
    $http.get("http://localhost:1212/api/books/all").then(
        function(response) {

        });
});
