
(function () {
    'use strict';

    angular
        .module('app')
        .controller('OrderController', OrderController);

    OrderController.$inject = ['$http'];

    function OrderController($http) {
        var iduser = 0;
        var vm = this;
        vm.orders = [];
        vm.getAll = getAll;
        vm.changeOrderStatus = changeOrderStatus;

        init();

        function init(){
            getAll();
            getUserId();
        }

        function getAll(){
            var url = "api/orders/all";
            var ordersPromise = $http.get(url);
            ordersPromise.then(function(response){
                vm.orders = response.data;
            });
        }

        function  getUserId(){
            var url = "/username";
            var ordersPromise = $http.get(url);
            ordersPromise.then(function(response){
                iduser = response.data;
            });
        }

        function changeOrderStatus(id,status){
            var url = "api/orders/orderstatus/" + id + "/" + status;
            $http.post(url).then(function(response){
                getAll();
                alert(response.data.response);
            }, function errorCallback(response) {
                alert("Error changing order status! Try Again!");
            });
        }
        function deleteOrder(idOrder){
            var url = "api/order/delete/" + idOrder;
            $http.post(url).then(function(response){
                alert("Order deleted successfully!");
                vm.books = response.data;
            }, function errorCallback(response) {
                alert("Error while deleting book! Try Again!");
            });
        }
    }
})();
