/**
 * Created by puja on 8/17/16.
 */
(function() {
    'use strict';

    angular.module('movieflix')
        .service('ratingService', ratingService);

    ratingService.$inject = ['$http', '$q', 'CONFIG'];

    function ratingService($http, $q, CONFIG) {

        var self = this;
        self.creterating = creterating;

         function creterating(rating) {
            console.log(rating);
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