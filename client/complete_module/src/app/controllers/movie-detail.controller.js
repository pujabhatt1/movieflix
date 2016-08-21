/**
 * Created by puja on 8/17/16.
 */
(function () {
    'use strict';

    angular
        .module('movieflix')
        .controller('MovieDetailController', MovieDetailController);

    MovieDetailController.$inject = ['movieService', '$routeParams'];

    function MovieDetailController(movieService, $routeParams) {

        var movieVm = this;
        movieVm.avgRating = "NA";
        init();

        function init() {

            movieService
                .getMovieDetail($routeParams.id)
                .then(function (data) {
                    movieVm.movie = data;
                }, function (error) {
                    console.log(error);
                });
            movieService
                .getAvgMovieRating($routeParams.id)
                .then(function (data) {
                    movieVm.avgRating = data;
                }, function (error) {
                    console.log(error);
                });

        }
    }
})();