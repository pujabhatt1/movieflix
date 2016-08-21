/**
 * Created by puja on 8/17/16.
 */
(function () {
    'use strict';

    angular.module('movieflix')
        .service('commentService', commentService);

    commentService.$inject = ['$http', '$q', 'CONFIG', 'authService'];

    function commentService($http, $q, CONFIG, authService) {

        var self = this;

        self.creteComment = creteComment;
        self.getComments = getComments;

        init();
        function init() {
            authService.authorize();
        }


        function getComments(movieId) {
            return $http.get(CONFIG.API_HOST + '/comments/movie/' + movieId)
                .then(successFn, errorFn);
        }

        function creteComment(comment) {
            console.log(comment);
            return $http.post(CONFIG.API_HOST + '/comments', comment)
                .then(successFn, errorFn);
        }

        function successFn(response) {
            return response.data;
        }

        function errorFn(response) {
            return $q.reject('ERROR: ' + response.statusText);
        }
    }

})();