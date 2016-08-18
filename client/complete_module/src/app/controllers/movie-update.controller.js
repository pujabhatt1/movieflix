/**
 * Created by puja on 8/16/16.
 */
(function() {
    'use strict';

    angular.module('movieflix')
        .controller('MovieUpdateController', MovieUpdateController);

    MovieUpdateController.$inject = ['movieService','$location','$routeParams'];
    function MovieUpdateController(movieService,$location,$routeParams) {
        var movieVm = this;
        movieVm.updateMovie=updateMovie;
        init();
        function init() {
            console.log('Movieupdate');

            movieService
                .getMovieDetail($routeParams.id)
                .then(function(data) {
                    movieVm.newMovie = data;
                }, function(error) {
                    console.log(error);
                });
        }
        function updateMovie(){
            console.log("at update");;
            movieService
                .editMovie($routeParams.id,movieVm.newMovie)
                .then(function(data) {
                    $location.path('/movie-list');
                }, function(error) {
                    console.log(error);
                })
        }


    }

    angular.module('movieflix')
        .controller('MovieDeleteController', MovieDeleteController);

    MovieDeleteController.$inject = ['movieService','$location','$routeParams'];
    function MovieDeleteController(movieService,$location,$routeParams) {
        var movieVm = this;

        init();
        function init() {
            console.log('delete');
            console.log($routeParams.id);
            console.log("end");
            movieService
                .deleteMovie($routeParams.id)
                .then(function(data) {
                    $location.path('/movie-list');
                }, function(error) {
                    console.log(error);
                });
        }



    }

})();