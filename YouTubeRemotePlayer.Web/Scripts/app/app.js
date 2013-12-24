var ytRemotePlayerApp = angular.module('ytRemotePlayerApp', [
  'ngRoute',
  'searchControllers',
  'favoritesControllers',
  'playerControllers',
  'youtube'
]);

ytRemotePlayerApp.config(['$routeProvider', '$locationProvider',
  function ($routeProvider, $locationProvider) {
      $routeProvider.
        when('/search', {
            templateUrl: '../Scripts/app/search/videos-list.html',
            controller: 'SearchCtrl' 
        }).
        when('/favorites', {
            templateUrl: '../Scripts/app/favorites/favorites-list.html',
            controller: 'FavoritesCtrl'
        }).
        when('/player', {
            templateUrl: '../Scripts/app/player/yt-player.html',
            controller: 'PlayerCtrl'
        }).
        otherwise({
            redirectTo: '/search'
        });
  }])
    .run(['$window', function ($win) {
        //load youtube API
        var wf = document.createElement('script');
        wf.src = ('https:' == document.location.protocol ? 'https' : 'http') +
            '://apis.google.com/js/client.js?onload=handleClientLoad';
        wf.type = 'text/javascript';
        wf.async = 'true';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(wf, s);

        // This callback is invoked by the Google APIs JS client automatically when it is loaded.
        $win.handleClientLoad = function () {
            var clientId = '837050751313',
                apiKey = 'AIzaSyCeodT561Z52t7afUf7bQXkm2Yi1idJUIw';

            gapi.client.setApiKey(apiKey);
            gapi.client.load('youtube', 'v3', function () {
                $('#ytRemotePlayerApp').scope().$broadcast("ytApiLoaded", { state: 'loaded' });
                $('#ytRemotePlayerApp').scope().isYtOn = true;
            });
        }
    }]);

