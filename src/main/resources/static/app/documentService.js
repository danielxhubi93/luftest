'use strict';
var App = angular.module('app',[]);

App.factory('docService', ['$http', '$q', 'urls', function ($http, $q, urls) {

    var factory = {
        saveDoc: saveDoc,
    };
    return factory;

    function saveDoc(file) {
        var deferred = $q.defer();
        var formData = new FormData();
        formData.append('file', file);

        $http.post(urls.DOC_URL+'save', formData,{
            transformRequest : angular.identity,
            headers : {
                'Content-Type' : undefined
            }})
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    alert(errResponse.data.errorMessage);
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    };
}
]);
