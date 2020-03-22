
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
        vm.getUserOrders = getUserOrders;
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

        function getUserOrders(){
            var url = "api/orders/orderbyuser/" + iduser;
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
                vm.orders = response.data;
            });
        }
    }
})();
