
(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegistrationController', RegistrationController);

    RegistrationController.$inject = ['$http'];

    function RegistrationController($http) {
        var vm = this;
        vm.user= [];
        vm.register = register;

        init();

        function init(){
        }

        function register(){
            var url = "/api/registration/add/" + vm.user.username + "/" + vm.user.password;
            $http.post(url).then(function(response){
                alert(response.data);
            });
        }
    }
})();
