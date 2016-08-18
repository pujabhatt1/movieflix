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
        movieVm.searchMovie=searchMovie;
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
7
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
        function searchMovie(){
            console.log("in search");
          console.log(movieVm.searchtxt)
            movieService
                .getMoviesBySearchText(movieVm.searchtxt)
                .then(function(data) {
                    movieVm.movies=data;
                }, function(error) {
                    console.log(error);
                })
        }


    }



})();