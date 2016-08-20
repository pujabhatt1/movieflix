/**
 * Created by puja on 8/17/16.
 */
(function() {
    'use strict';

    angular.module('movieflix')
        .controller('CommentController', CommentController);

    CommentController.$inject = ['commentService','$http','$routeParams','$location','localStorageService'];

    function CommentController(commentService,$http,$routeParams,$location,localStorageService) {
        var commentVm = this;
        commentVm.addComment=addComment;

       init();

        function init() {
            console.log('commentService');
            commentService
                .getComments($routeParams.movieId)
                .then(function(data) {
                    console.log(data);
                    commentVm.comments = data;
                }, function(error) {
                    console.log(error);
                });
        }

        function addComment(){
            console.log("add comment");
           console.log(localStorageService.get("uId"));
          var cObj={"commentText":commentVm.newComment.commentText,"user":{"id":localStorageService.get("uId")},"movie":{"movieId":$routeParams.id}}
          console.log(cObj);
            commentService
                .creteComment(cObj)
                .then(function(data) {
                    $location.path('/movie-detail/'+$routeParams.id);
                }, function(error) {
                    console.log(error);
                })
        }
    }

})();