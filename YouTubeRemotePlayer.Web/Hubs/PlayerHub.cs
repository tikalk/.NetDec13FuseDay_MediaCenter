using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Microsoft.AspNet.SignalR;

namespace YouTubeRemotePlayer.Web.Hubs
{
    public class PlayerHub : Hub
    {
        #region Send

        public void SendPlayCommand(string videoId, string remotePlayerId)
        {
            //TODO send message only to client 'remotePlayerId' 
            Clients.All.receivePlayCommand(videoId, remotePlayerId);
        }

        public void SendPauseCommand(string videoId, string remotePlayerId)
        {
            //TODO send message only to client 'remotePlayerId' 
            Clients.All.receivePauseCommand(videoId, remotePlayerId);
        }

        public void SendForwardCommand(string videoId, string remotePlayerId)
        {
            //TODO send message only to client 'remotePlayerId' 
            Clients.All.receiveForwardCommand(videoId, remotePlayerId);
        }

        public void SendBackCommand(string videoId, string remotePlayerId)
        {
            //TODO send message only to client 'remotePlayerId' 
            Clients.All.receiveBackCommand(videoId, remotePlayerId);
        }

        #endregion
    }
}