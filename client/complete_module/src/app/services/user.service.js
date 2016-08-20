(function() {
  'use strict';

  angular.module('movieflix')
    .service('userService', userService);

  userService.$inject = ['$http', '$q', 'CONFIG','authService'];

  function userService($http, $q, CONFIG,authService) {

    var self = this;

    self.getUsers = getUsers;
    self.getUserById = getUserById;
    self.getRoles=getRoles;
    self.createUser=createUser;
    init();
    function init(){
      authService.authorize();
    }

    function getUsers() {
    return $http.get(CONFIG.API_HOST + '/users')
            .then(successFn, errorFn);

    }
      function getRoles(){
      return $http.get('http://localhost:8080/apimodule/api/roles')
          .then(successFn, errorFn);
    }

    function createUser(user) {
        console.log(user);
        return $http.post('http://localhost:8080/apimodule/api/signup', user)
          .then(successFn,errorFn);
    }
    function getUserById(id) {
      return $http.get(CONFIG.API_HOST + '/users/' + id)
        .then(successFn, errorFn);
    }

    function successFn(response) {
      return response.data;
    }

    function errorFn(response) {
      return $q.reject('ERROR: ' + response.statusText);
    }
  }

})();