/**
 * Created by puja on 8/20/16.
 */
(function() {
    'use strict';

    angular.module('movieflix')
        .controller('HeaderController', HeaderController);

    HeaderController.$inject = ['$location','authService','localStorageService'];

    function HeaderController($location,authService,localStorageService) {
        var headerVm = this;
        headerVm.isLoggedIn=isLoggedIn;
        headerVm.isAdmin= isAdmin;
        init();

        function init() {
            console.log('Inside header controller');

        }
       function isLoggedIn(){
            if (localStorageService.get("uId").length>0) {
                return true;
            }
            else {
                 return false;
            }
        }
        function isAdmin(){
            if (localStorageService.get("uId").length>0 &&localStorageService.get("role")=="Admin") {
                return true;
        }
        return false;
        }
    }

})();