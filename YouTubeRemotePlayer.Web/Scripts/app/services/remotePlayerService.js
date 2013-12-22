ytRemotePlayerApp.service('remotePlayerService', function () {
    // Declare a proxy to reference the hub. 
    var playerHub = $.connection.playerHub;
    playerHub.logging = true;

    // Start the connection.
    $.connection.hub.start({ transport: 'longPolling' }) //for debug we use long polling
        .fail(function () {
            alert('could not connect to signalR player hub')
        });

    $.connection.hub.connectionSlow(function () {
        console.log('We are currently experiencing difficulties with the connection.')
    });

    return {
        getServerProxy: function () {
            return playerHub.server;

            //// Call the Send method on the hub. 
            //playerHub.server.send($('#displayname').val(), $('#message').val());
        },

        getClientProxy: function () {
            return playerHub.client;

            //// Create a function that the hub can call to broadcast messages.
            //playerHub.client.broadcastMessage = function (name, message) {
            //};
        }
    };
});