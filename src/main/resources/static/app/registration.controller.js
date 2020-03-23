
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
            }, function(response) {
                alert(response.data)
                    //an error has occurred.  get details from data
                    //get http status from response.status
                    //get data from response.data
                });
        }
    }
})();
