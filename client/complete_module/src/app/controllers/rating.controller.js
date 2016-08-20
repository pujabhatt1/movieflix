/**
 * Created by puja on 8/17/16.
 */

(function () {
    'use strict';

    angular.module('movieflix')
        .controller('RatingController', RatingController);

    RatingController.$inject = ['ratingService', '$http', '$routeParams', 'localStorageService', '$location'];

    function RatingController(ratingService, $http, $routeParams, localStorageService, $location) {
        var RatingVm = this;

        init();
        function init() {
            console.log('rcntrl');
            var rObj = {
                "ratings": $routeParams.rating,
                "user": {"id": localStorageService.get("uId")},
                "movie": {"movieId": $routeParams.id}
            };
            console.log(rObj);
            ratingService
                .createRating(rObj)
                .then(function (data) {
                    $location.path('/movie-detail/' + $routeParams.id);
                }, function (error) {
                    console.log(error);
                })
        }


    }

})();