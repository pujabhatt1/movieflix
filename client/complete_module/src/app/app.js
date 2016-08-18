(function() {
  'use strict';

  angular
    .module('movieflix', ['ngMessages','ngRoute','LocalStorageModule'])
    .config(moduleConfig)
    .run(moduleRun);
    moduleConfig.$inject = ['$routeProvider','localStorageServiceProvider'];
  function moduleConfig($routeProvider,localStorageServiceProvider) {
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

      .when('/resources', {
        templateUrl: 'app/views/resources.tmpl.html',
        controller: 'ResourcesController',
        controllerAs: 'resourcesVm'
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
            templateUrl: 'app/views/movie-list.tmpl.html',
            controller: 'findMovieController',
            controllerAs: 'movieVm'
        })

        .when('/movie-list/type/:type', {
            templateUrl: 'app/views/movie-list.tmpl.html',
            controller: 'MovieTypeController',
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

        .when('/add-rating/:id', {
            controller: 'RatingController',
            controllerAs: 'ratingVm'
        })
        .when('/add-movie/:id', {
            templateUrl: 'app/views/update-movie.tmpl.html',
            controller: 'MovieUpdateController',
            controllerAs: 'movieVm'
        })

      .otherwise({
        redirectTo: '/login'
      });
  }
    moduleRun.$inject = ['$rootScope', '$http', '$location', '$window'];

    function moduleRun($rootScope, $http, $location, $window) {
        console.log("inside run of module");
        // keep user logged in after page refresh
        if ($window.localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' +  $window.localStorage.currentUser.authtoken;

        }

        // redirect to login page if not logged in and trying to access a restricted page
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var publicPages = ['/login'];
            var restrictedPage = publicPages.indexOf($location.path()) === -1;
            if (restrictedPage && !$window.localStorage.currentUser) {
                $location.path('/login');
            }
        });
    }

})();