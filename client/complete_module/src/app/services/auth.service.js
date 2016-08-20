/**
 * Created by puja on 8/16/16.
 */
(function() {
    'use strict';
 angular.module('movieflix')
        .service('authService', authService);

    authService.$inject = ['$http', '$q','localStorageService', '$location'];

    function authService($http, $q,localStorageService, $location) {

        var self = this;
        self.checkUser=checkUser;
        self.authorize=authorize;
        self.logout=logout;

        function checkUser(loginUser){
            return $http.post('http://localhost:8080/apimodule/api/login',loginUser)
                .success(function (response) {
                    // login successful if there's a token in the response
                    var result=response.data;
                   console.log(response.token);

                    if (response.token) {
                        console.log("inside token");
                         localStorageService.set("uId", response.userId);
                        localStorageService.set("role",response.role);
                        console.log(localStorageService.get("uId"));
                        localStorageService.set("auth-token",response.token);
                        $http.defaults.headers.common.Authorization = 'Bearer ' +  localStorageService.get("auth-token") ;
                        $http.defaults.headers.common['Content-Type']="application/json";
                        $http.defaults.headers.common.userId = response.userId;
                       return true
                    }
                });
        }

        function authorize(){
            redirectLogin();
            $http.defaults.headers.common.Authorization = 'Bearer ' +  localStorageService.get("auth-token") ;
        }

        function errorFn(response) {
            return $q.reject('ERROR: ' + response.statusText);
        }

        function logout(){
            $http.defaults.headers.common.Authorization = '';
            localStorageService.set("uId",'');
            localStorageService.set("auth-token",'');
            localStorageService.set("role",'');
            redirectLogin();
        }
        function redirectLogin(){
            if(localStorageService.get("uId").length<=0) {
                $location.path('/login');
            }
        }
    }

})();