(function() {
    'use strict';

    angular.module('movieflix')
        .service('movieService', movieService);

    movieService.$inject = ['$http', '$q', 'CONFIG','authService'];

    function movieService($http, $q, CONFIG,authService) {

          var self = this;
         self.createMovie=createMovie;
        self.editMovie=editMovie;
        self.deleteMovie=deleteMovie;
         self.getAllMovies=getAllMovies;
        self.getMoviesBySearchText=getMoviesBySearchText;
        self.getMoviesByTopRating=getMoviesByTopRating;
        self.getMovieByField=getMovieByField;
        self.getMovieDetail=getMovieDetail;
        self.getMovieDetailByImdb=getMovieDetailByImdb;

        init();
        function init(){
            console.log("in movieservie");
            authService.authorize();
        }

        function getAllMovies() {
            return $http.get(CONFIG.API_HOST + '/movies')
                .then(successFn,errorFn);
        }

        function getMoviesBySearchText(searchtxt) {
            return $http.get(CONFIG.API_HOST + '/movies/'+searchtxt)
                .then(successFn,errorFn);
        }
        function getMoviesByTopRating(type) {
            return $http.get(CONFIG.API_HOST + '/movies/find/toprated'+type)
                .then(successFn,errorFn);
        }

        function getMovieByField(type,searchtxt) {
            console.log(type)
            console.log(searchtxt);
            return $http.get(CONFIG.API_HOST + '/movies/find/criteria/'+type+'/'+searchtxt)
                .then(successFn,errorFn);
        }
        function getMovieDetail(id) {
            return $http.get(CONFIG.API_HOST + '/movies/'+id)
                .then(successFn,errorFn);
        }
        function getMovieDetailByImdb(imdbId) {
            return $http.get(CONFIG.API_HOST + '/movies/detail/'+imdbId)
                .then(successFn,errorFn);
        }

        function createMovie(movie) {
            console.log(movie);
            return $http.post('http://localhost:8080/apimodule/api/admin', movie)
                .then(successFn,errorFn);
        }
        function deleteMovie(id) {
            console.log(id);
            return $http.delete('http://localhost:8080/apimodule/api/admin/'+id)
                .then(successFn,errorFn);
        }
        function editMovie(id,movie) {
            console.log(movie);
            return $http.put('http://localhost:8080/apimodule/api/admin/'+id, movie)
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