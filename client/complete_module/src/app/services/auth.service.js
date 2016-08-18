/**
 * Created by puja on 8/16/16.
 */
(function() {
    'use strict';
 angular.module('movieflix')
        .service('authService', authService);

    authService.$inject = ['$http', '$q','localStorageService', '$window'];

    function authService($http, $q,localStorageService, $window) {

        var self = this;
        self.checkUser=checkUser;
        self.setHeader=setHeader;


        function checkUser(loginUser){
            return $http.post('http://localhost:8080/apimodule/api/login',loginUser)
                .success(function (response) {
                    // login successful if there's a token in the response
                    var result=response.data;
                   console.log(response.token);

                    if (response.token) {
                        console.log("inside token");
                         localStorageService.set("uId", response.userId);
                        console.log(localStorageService.get("uId"));
                        localStorageService.set("auth-token",response.token);
                        $http.defaults.headers.common.Authorization = 'Bearer ' +  localStorageService.get("auth-token") ;
                        $http.defaults.headers.common['Content-Type']="application/json";
                        $http.defaults.headers.common.userId = response.userId;
                       return true
                    }
                });
        }

        function setHeader(loginUser){
            $http.defaults.headers.common.Authorization = 'Bearer ' +  localStorageService.get("auth-token") ;
        }

        function errorFn(response) {
            return $q.reject('ERROR: ' + response.statusText);
        }

        function logout(){
            $http.defaults.headers.common.Authorization = '';
        }
    }

})();