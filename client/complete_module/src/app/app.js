(function() {
  'use strict';

  angular
    .module('movieflix', ['ngMessages','ngRoute'])
    .config(moduleConfig)
    .run(moduleRun);

  function moduleConfig($routeProvider) {

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