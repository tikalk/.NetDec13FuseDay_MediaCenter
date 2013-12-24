var playerControllers = angular.module('playerControllers', []);

playerControllers.controller('PlayerCtrl', ['$scope', '$http', '$rootScope', 'remotePlayerService',
  function ($scope, $http, $rootScope, remotePlayerService) {

      var client = remotePlayerService.getClientProxy();

      client.receivePlayCommand = function (videoId) {
          console.log('receivePlayCommand ' + videoId);

          if (window.JBridge) {
              window.JBridge.start(videoId);
          }
      };
      
  }]);
