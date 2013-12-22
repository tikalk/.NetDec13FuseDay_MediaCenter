var favoritesControllers = angular.module('favoritesControllers', []);

favoritesControllers.controller('FavoritesCtrl', ['$scope', '$http', '$rootScope',
  function ($scope, $http, $rootScope) {
      $scope.currentPage = 0;
      $scope.videos = [];
      $scope.totalCount = 0;

      $scope.getPage = function (curPage) {
          
          $scope.videos = [];
          $scope.totalCount = $scope.totalCount || 0;
          $scope.currentPage = curPage || 0;

          //TODO get data from mongo
      };
  }]);

