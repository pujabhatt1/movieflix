(function() {
  'use strict';

  angular.module('movieflix')
    .service('resourceService', resourceService);

  resourceService.$inject = ['$http', '$q'];

  function resourceService($http, $q) {

    var self = this;

  }

})();