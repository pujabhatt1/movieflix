/**
 * Created by puja on 8/17/16.
 */
(function() {
    'use strict';

    angular.module('movieflix')
        .service('ratingService', ratingService);

    ratingService.$inject = ['$http', '$q', 'CONFIG','authService'];

    function ratingService($http, $q, CONFIG,authService) {

        var self = this;
        self.createRating = createRating;
        init();
        function init(){
            authService.setHeader();
        }
         function createRating(rating) {
            console.log("inside create");
            return $http.post(CONFIG.API_HOST+'/ratings', rating)
                .then(successFn,errorFn);
        }
        function successFn(response) {
            return response.data;
        }

        function errorFn(response) {
            return $q.reject('ERROR: ' + response.statusText);
        }
    }

})();