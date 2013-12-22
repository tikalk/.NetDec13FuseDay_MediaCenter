var searchControllers = angular.module('searchControllers', []);

searchControllers.controller('SearchCtrl', ['$scope', '$http', '$rootScope', 'remotePlayerService',
  function ($scope, $http, $rootScope, remotePlayerService) {
      var serverProxy = remotePlayerService.getServerProxy();

      $scope.isEnabled = $rootScope.isYtOn || false;
      $scope.currentPage = 0;

      $scope.doSearch = function (pageToken, curPage) {
          var request = gapi.client.youtube.search.list({
              q: $scope.query,
              part: 'snippet',
              fields: 'pageInfo,nextPageToken,prevPageToken,tokenPagination,items(id,snippet)',
              pageToken: pageToken
          });

          $scope.videos = [];
          $scope.currentPage = curPage ? curPage : 0;

          request.execute(function (response) {
              $scope.$apply(function () {
                  $scope.videos = response.items;
                  $scope.nextPageToken = response.nextPageToken;
                  $scope.prevPageToken = response.prevPageToken;
                  $scope.pageInfo = response.pageInfo;
              });
          });
      };

      $rootScope.$on("ytApiLoaded", function (args) {
          $scope.$apply(function () {
              $scope.isEnabled = true;
          });
      });
  }]);
