ytRemotePlayerApp.controller('PlayerCtrl', ['$scope', '$http', '$rootScope', 'remotePlayerService',
  function ($scope, $http, $rootScope, remotePlayerService) {

      var client = remotePlayerService.getClientProxy();
      var playingVideoId;

      client.receivePlayCommand = function (videoId) {
          console.log('receivePlayCommand ' + videoId);

          if (window.JBridge) {
              if (videoId == playingVideoId) {
                  window.JBridge.play();
              } else {
                  playingVideoId = videoId;
                  window.JBridge.start(videoId);
              }
          }
      };

      client.receivePauseCommand = function (videoId) {
          console.log('receivePauseCommand ' + videoId);

          if (window.JBridge) {
              window.JBridge.pause();
          }
      };
      
  }]);
