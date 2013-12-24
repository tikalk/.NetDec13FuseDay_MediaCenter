ytRemotePlayerApp.controller('FavoritesCtrl', ['$scope', '$http', '$rootScope',
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

      $scope.removeFromFavorite = function (videoId) {
          $http.post('/favorites/remove', { videoId: videoId }).success(function (data)
          {
              //alert(data);
              $scope.videos = data;
          });
      };

      function loadFavorites()
      {
          $http.get('/favorites/getList').success(function (data) { $scope.videos = data; });
      }

      loadFavorites();

  }]);