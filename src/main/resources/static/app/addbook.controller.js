
(function () {
    'use strict';

    angular
        .module('app')
        .controller('BookController', BookController);

    BookController.$inject = ['$http'];

    function BookController($http) {
        var iduser = 0;
        var vm = this;
        vm.info = [];
        vm.save = save;

        init();

        function init(){
        }

        function save(){
            var book = {
                "title" : vm.info.title,
                "user_id" : iduser
            };
            var response = $http.post("api/books/save", book);
            response.success(function(data, status, headers, config) {
                $scope.responseData = data;
                vm.books = data;
            });
            response.error(function(data, status, headers, config) {
                alert( "Exception details: " + JSON.stringify({data: data}));
            });

        }

        function  getUserId(){
            var url = "/username";
            var booksPromise = $http.get(url);
            booksPromise.then(function(response){
                iduser = response.data;
            });
        }


    }
})();
