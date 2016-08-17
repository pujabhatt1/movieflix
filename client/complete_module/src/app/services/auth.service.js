/**
 * Created by puja on 8/16/16.
 */
(function() {
    'use strict';
 angular.module('movieflix')
        .service('authService', authService);

    authService.$inject = ['$http', '$q', '$window'];

    function authService($http, $q, $window) {

        var self = this;
        self.checkUser=checkUser;



        function checkUser(loginUser){
            return $http.post('http://localhost:8080/apimodule/api/login',loginUser)
                .success(function (response) {
                    // login successful if there's a token in the response
                    var result=response.data;
                   console.log(response.token);

                    if (response.token) {
                        console.log("inside token");
                        $window.localStorage.currentUser = { loginUser: loginUser, authtoken: response.token };
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.token ;
                        console.log($http.defaults.headers.common.Authorization);
                        return true
                    }
                });
        }



        function errorFn(response) {
            return $q.reject('ERROR: ' + response.statusText);
        }

        function logout(){
            $http.defaults.headers.common.Authorization = '';
        }
    }

})();