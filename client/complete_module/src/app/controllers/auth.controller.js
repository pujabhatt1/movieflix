(function() {
    'use strict';

    angular.module('movieflix')
        .controller('AuthController', AuthController);

    AuthController.$inject = ['userService','$location','authService'];

    function AuthController(userService,$location,authService) {
        var authVm = this;
        authVm.loginUser=loginUser;
        authVm.addUser=addUser;


        init();

        function init() {
            console.log('AuthController');
            userService.getRoles()
                .then(function(data){
                    authVm.roles=data;
                })
                .catch(function(error){
                    console.log(error);
                });
        }
        function loginUser(){
               authService
                .checkUser(authVm.logUser)
                      .then(function(result) {
                         console.log(result);
                          console.log("im here");


                    $location.path('/movie-list');
                }, function(error) {
                    console.log(error);
                })


        }

        function addUser(){
            userService
                .createUser(authVm.newUser)
                .then(function(data) {
                    $location.path('/movie-list');
                }, function(error) {
                    console.log(error);
                })


        }


    }

})();