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
      if ($window.localStorage.currentUser) {
          console.log("atcntrl");
          console.log($http.defaults.headers.common.Authorization);
      }
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