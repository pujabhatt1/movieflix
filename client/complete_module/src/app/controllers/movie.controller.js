/**
 * Created by puja on 8/16/16.
 */
(function () {
    'use strict';

    angular.module('movieflix')
        .controller('MovieController', MovieController);

    MovieController.$inject = ['movieService', '$location', '$routeParams', 'authService'];
    function MovieController(movieService, $location, $routeParams, authService) {

        var movieVm = this;
        movieVm.addMovie = addMovie;
        init();

        function init() {
            authService.authorize();
            if ($routeParams.field && $routeParams.txt) {
                movieService
                    .getMovieByField($routeParams.field, $routeParams.txt)
                    .then(successFn,errorFn);
            }
            else if ($routeParams.type) {
                movieService
                    .getMoviesByTopRating($routeParams.type)
                    .then(successFn,errorFn);
            }
            else {

                movieService
                    .getAllMovies()
                    .then(successFn,errorFn);
            }

            return movieVm.movies;


        }

        function setPaginationItems() {
            movieVm.viewby = 4;
            movieVm.totalItems = movieVm.movies.length;
            movieVm.currentPage = 1;
            movieVm.itemsPerPage = movieVm.viewby;
            movieVm.maxSize = 5; //Number of pager buttons to show
        }

        function setPage(pageNo) {
            movieVm.currentPage = pageNo;
        };

        function pageChanged() {
            console.log('Page changed to: ' + movieVm.currentPage);
        };

        function setItemsPerPage(num) {
            movieVm.itemsPerPage = num;
            movieVm.currentPage = 1; //reset to first page
        }

        //function add new movie
        function addMovie() {
            movieService
                .createMovie(movieVm.newMovie)
                .then(function (data) {
                    $location.path('/movie-list');
                }, function (error) {
                    console.log(error);
                })

        }

        function successFn(data) {
            movieVm.movies = data;
            setPaginationItems();
        }
        function errorFn() {
            console.log(error);
        }


    }


})();