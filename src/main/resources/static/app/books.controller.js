
(function () {
    'use strict';

    angular
        .module('app')
        .controller('BookController', BookController);

    BookController.$inject = ['$http'];

    function BookController($http) {
        var iduser = 0;
        var vm = this;
        vm.books = [];
        vm.getAll = getAll;
        vm.getUserBooks = getUserBooks;
        vm.deleteBook = deleteBook;
        vm.orderBook = orderBook;

        init();

        function init(){
            getAll();
            getUserId();
        }

        function getAll(){
            var url = "api/books/all";
            var booksPromise = $http.get(url);
            booksPromise.then(function(response){
                vm.books = response.data;
            }, function errorCallback(response) {
                alert("Error!");
            });
        }

        function getUserBooks(){
            var url = "api/books/bookbyuser/" + iduser;
            var booksPromise = $http.get(url);
            booksPromise.then(function(response){
                vm.books = response.data;
            });
        }
        function  getUserId(){
            var url = "/username";
            var booksPromise = $http.get(url);
            booksPromise.then(function(response){
                iduser = response.data;
            });
        }
        function orderBook(idBook){
           var order = {
                "book_id":  idBook ,
                "user_id":  iduser,
                "status_id": 3
            };
            var url = "api/books/order/save";
            $http.post(url, order).then(function(response){
                alert(response.data.response);
            }, function errorCallback(response) {
            alert("Error while placing order! Try Again!");
        });
        }
        function deleteBook(idBook){
            var url = "api/books/delete/" + idBook;
            $http.post(url).then(function(response){
                alert(response.data.response);
                getAll();
            }, function errorCallback(response) {
                alert("Error while deleting book! Try Again!");
            });
        }
    }
})();
