/**
 * Created by puja on 8/16/16.
 */
(function() {
    'use strict';

    angular.module('movieflix')
        .controller('MovieController', MovieController);

    MovieController.$inject = ['movieService','$location'];

    function MovieController(movieService,$location) {
        var movieVm = this;
        movieVm.addMovie=addMovie;

        init();

        function init() {
            console.log('MovieController');

                movieService
                    .getAllMovies()
                    .then(function(data) {
                        movieVm.movies=data;

                    }, function(error) {
                        console.log(error);
                    })

        }
        function addMovie(){
            movieService
                .createMovie(movieVm.newMovie)
                .then(function(data) {
                    $location.path('/movie-list');
                }, function(error) {
                    console.log(error);
                })
        }



    }

})();