
angular.module('youtube', ['ng']).run(function () {
    var tag = document.createElement('script');

    // This is a protocol-relative URL as described here:
    //     http://paulirish.com/2010/the-protocol-relative-url/
    // If you're testing a local page accessed via a file:/// URL, please set tag.src to
    //     "https://www.youtube.com/iframe_api" instead.
    tag.src = "//www.youtube.com/iframe_api";
    var firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
})
    .service('youtubePlayerApi', ['$window', '$rootScope', '$log', 'remotePlayerService',
        function ($window, $rootScope, $log, remotePlayerService) {
        var service = $rootScope.$new(true);
        var serverProxy = remotePlayerService.getServerProxy();

        // Youtube callback when API is ready
        $window.onYouTubeIframeAPIReady = function () {
            $log.info('Youtube API is ready');
            service.ready = true;
        };

        service.players = {};
        service.ready = false;
        service.playerId = null;
        service.player = null;
        service.videoId = null;
        service.playerHeight = '390';
        service.playerWidth = '640';

        service.bindVideoPlayer = function (elementId, video) {
            $log.info('Binding to player ' + elementId);
            service.playerId = elementId;
            service.videoId = video;
            service.loadPlayer();
        };

        service.createPlayer = function () {
            $log.info('Creating a new Youtube player for DOM id ' + this.playerId + ' and video ' + this.videoId);
            return new YT.Player(this.playerId, {
                height: this.playerHeight,
                width: this.playerWidth,
                videoId: this.videoId,
                events: {
                    'onStateChange': this.onPlayerStateChange
                }
            });
        };

        service.onPlayerStateChange = function (event) {
            switch (event.data) {
                case YT.PlayerState.ENDED:
                    break;
                case YT.PlayerState.PLAYING:
                    serverProxy.sendPlayCommand(event.target.k.videoData.video_id, null);
                    break;
                case YT.PlayerState.PAUSED:
                    break;
                case YT.PlayerState.BUFFERING:
                    break;
                case YT.PlayerState.CUED:
                    break;
                default:

            }
            serverProxy.SendPlayCommand
        }

        service.loadPlayer = function () {
            // API ready?
            if (this.ready && this.playerId && this.videoId) {
                this.player = this.players[this.playerId];
                if (this.player) {
                    this.player.destroy();
                }

                this.player = this.createPlayer();
                this.players[this.playerId] = this.player;
            }
        };

        return service;
    }])
    .directive('youtubePlayer', ['youtubePlayerApi', function (youtubePlayerApi) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                element.attr('id', attrs.id);
                youtubePlayerApi.bindVideoPlayer(attrs.id, attrs.videoId);
            }
        };
    }]);