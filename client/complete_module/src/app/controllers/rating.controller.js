/**
 * Created by puja on 8/17/16.
 */

(function() {
    'use strict';

    angular.module('movieflix')
        .controller('RatingController', RatingController);

    RatingController.$inject = ['ratingService','$http','$routeParams','$window','$location'];

    function RatingController(ratingService,$http,$routeParams,$window,$location) {
        var RatingVm = this;
        RatingVm.addRating=addRating;
       init();
     function init() {
            console.log('RatingService');
         ratingService
                .getRatings($routeParams.id)
                .then(function(Ratings) {
                    RatingVm.Ratings = Ratings;
                }, function(error) {
                    console.log(error);
                });
        }

        function addRating(){
            console.log("add Rating");
            var cObj={"ratings":5,"user":{"id":"d4fa6b34-1ce0-46c6-8052-44af489d285d"},"movie":{"movieId":$routeParams.id}}
            console.log(cObj);
            ratingService
                .creteRating(cObj)
                .then(function(data) {
                    $location.path('/movie-detail/'+$routeParams.id);
                }, function(error) {
                    console.log(error);
                })
        }
    }

})();