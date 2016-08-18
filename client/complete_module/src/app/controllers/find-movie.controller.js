/**
 * Created by puja on 8/17/16.
 */
/**
 * Created by puja on 8/17/16.
 */
(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('findMovieController', findMovieController);

    findMovieController.$inject = ['movieService', '$routeParams'];

    function findMovieController(movieService, $routeParams) {
        console.log("mdetail");
        var movieVm = this;
        init();

        function init() {
            console.log("in mcnt");
            movieService
                .getMovieByField($routeParams.field,$routeParams.txt)
                .then(function(data) {
                    movieVm.movies = data;
                }, function(error) {
                    console.log(error);
                });
        }
    }

    angular
        .module('movieflix')
        .controller('MovieTypeController', MovieTypeController);

    MovieTypeController.$inject = ['movieService', '$routeParams'];

    function MovieTypeController(movieService, $routeParams) {
        console.log("mdetail");
        var movieVm = this;
        init();

        function init() {
            console.log("in mcnt");
            movieService
                .getMoviesByTopRating($routeParams.type)
                .then(function(data) {
                    movieVm.movies = data;
                }, function(error) {
                    console.log(error);
                });
        }
    }


})();