(function () {
    'use strict';

    angular
        .module('movieflix', ['ui.bootstrap', 'ngMessages', 'ngRoute', 'LocalStorageModule'])
        .config(moduleConfig)
        .run(moduleRun);
    moduleConfig.$inject = ['$routeProvider', 'localStorageServiceProvider'];
    function moduleConfig($routeProvider, localStorageServiceProvider) {
        localStorageServiceProvider
            .setStorageType('localStorage');

        $routeProvider
            .when('/users', {
                templateUrl: 'app/views/users.tmpl.html',
                controller: 'UsersController',
                controllerAs: 'usersVm'
            })

            .when('/users/:id', {
                templateUrl: 'app/views/user-detail.tmpl.html',
                controller: 'UserDetailController',
                controllerAs: 'userDetailVm'
            })


            .when('/login', {
                templateUrl: 'app/views/login.tmpl.html',
                controller: 'AuthController',
                controllerAs: 'authVm'
            })

            .when('/add-user', {
                templateUrl: 'app/views/add-user.tmpl.html',
                controller: 'AuthController',
                controllerAs: 'authVm'
            })
            //to show and manage for admin only
            .when('/movie-list', {
                templateUrl: 'app/views/movie-list.tmpl.html',
                controller: 'MovieController',
                controllerAs: 'movieVm'
            })

            .when('/movie-list-all', {
                templateUrl: 'app/views/movie-list-all.tmpl.html',
                controller: 'MovieController',
                controllerAs: 'movieVm'
            })

            .when('/movie-list/:field/:txt', {
                templateUrl: 'app/views/movie-list-all.tmpl.html',
                controller: 'MovieController',
                controllerAs: 'movieVm'
            })

            .when('/movie-list/type/:type', {
                templateUrl: 'app/views/movie-list-all.tmpl.html',
                controller: 'MovieController',
                controllerAs: 'movieVm'
            })

            .when('/movie-detail/:id', {
                templateUrl: 'app/views/movie-detail.tmpl.html',
                controller: 'MovieDetailController',
                controllerAs: 'movieVm'
            })

            .when('/movie-detail/Imdb:id', {
                templateUrl: 'app/views/movie-detail.tmpl.html',
                controller: 'MovieDetailController',
                controllerAs: 'movieVm'
            })
            .when('/add-movie', {
                templateUrl: 'app/views/add-movie.tmpl.html',
                controller: 'MovieController',
                controllerAs: 'movieVm'
            })
            .when('/delete-movie/:id', {
                templateUrl: 'app/views/movie-list.tmpl.html',
                controller: 'MovieDeleteController',
                controllerAs: 'movieVm'
            })

            .when('/add-comment/:id', {
                templateUrl: 'app/views/add-comment.tmpl.html',
                controller: 'CommentController',
                controllerAs: 'commentVm'
            })
            .when('/get-comments/:movieId', {
                templateUrl: 'app/views/comment-list.tmpl.html',
                controller: 'CommentController',
                controllerAs: 'commentVm'
            })


            .when('/add-rating/:id/:rating', {
                template: " ",
                controller: 'RatingController',
                controllerAs: 'ratingVm'
            })
            .when('/add-movie/:id', {
                templateUrl: 'app/views/update-movie.tmpl.html',
                controller: 'MovieUpdateController',
                controllerAs: 'movieVm'
            })
            .when('/logout', {
                resolve: {
                    logout: ['authService', function (authService) {
                        authService
                            .logout();
                    }]
                }
            })

            .otherwise({
                redirectTo: '/movie-list-all'
            });
    }

    moduleRun.$inject = ['$rootScope', '$http', '$location', 'localStorageService'];

    function moduleRun($rootScope, $http, $location, localStorageService) {
        console.log("inside run of module");
        // keep user logged in after page refresh
        if (localStorageService.get("uId").length > 0) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + localStorageService.get("auth-token");

        }
        else {
            $location.path('/login');
        }


    }

})();