
(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope','$http'];

    function UserController($scope,$http) {
        $scope.submit = true;
        $scope.update = false;
        $scope.cancel = false;
        $scope.username = true;
        $scope.password = true;

        init();

        function init(){
            getAll();
        }

        function getAll(){
            var url = "/api/users/all";
            var usersPromise = $http.get(url);
            usersPromise.then(function(response){
                $scope.users = response.data;
            }, function errorCallback(response) {
                alert("Error. Try Again!");
            });
        }
        $scope.createUser = function () {
            var url = "/api/users/save/";
            var usersPromise = $http.post(url, $scope.user);
            usersPromise.then(function(response) {
                $scope.users = response.data;
                alert("User has created Successfully");
            }, function(response) {
                alert("Error while creating user! Try Again!");
            });

        }
        $scope.updateUser = function () {
            var url = "/api/users/update/";
            var usersPromise = $http.post(url + $scope.user.username, $scope.user);
            usersPromise.then(function (response) {
                $scope.users = response.data;
                alert("User has updated successfully");
            }, function (response) {
                alert("Error while updating user! Try Again!");
            });
        }
        $scope.deleteUser = function(user){
            var url = "/api/users/delete/" + user.username;
            $http.post(url).then(function(response){
                $scope.users = response.data;
                alert("User deleted successfully!");
            }, function errorCallback(response) {
                alert("Error while deleting user! Try Again!");
            });
        }
        //Set $scope on Edit button click
        $scope.editUser = function(user) {
            $scope.user = user;
            $scope.submit = false;
            $scope.update = true;
            $scope.cancel = true;
            $scope.username = true;
            $scope.password = false;

        };

        //cancel Update
        $scope.cancelUpdate = function() {
            $scope.user = null;
            $scope.submit = true;
            $scope.update = false;
            $scope.cancel = false;
            $scope.username = true;
            $scope.password = true;
        };

    }
})();
