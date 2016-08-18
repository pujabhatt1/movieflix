(function() {
  'use strict';

  angular.module('movieflix')
    .controller('UsersController', UsersController);

  UsersController.$inject = ['userService','$window','$http'];

  function UsersController(userService,$window,$http) {
    var usersVm = this;



    init();

    function init() {
      console.log('UsersController');

      userService
        .getUsers()
        .then(function(users) {
          usersVm.users = users;
        }, function(error) {
          console.log(error);
        });
    }


  }

})();