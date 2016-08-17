(function() {
  'use strict';

  angular.module('movieflix')
    .controller('ResourcesController', ResourcesController);

  ResourcesController.$inject = [];

  function ResourcesController() {
    var resourcesVm = this;

    init();

    function init() {
      console.log('ResourcesController');
    }
  }

})();