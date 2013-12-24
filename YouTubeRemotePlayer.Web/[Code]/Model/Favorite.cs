using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace YouTubeRemotePlayer.Web.Model
{
    public class Favorite
    {
        [BsonId]
        [BsonRepresentation(BsonType.String)]
        [JsonProperty(PropertyName = "id")]
        public string Id { get; set; }

        [BsonElement("userId")]
        [JsonProperty(PropertyName = "userId")]
        public string UserId { get; set; }

        [BsonElement("title")]
        [JsonProperty(PropertyName = "title")]
        public string Title { get; set; }

        [BsonElement("creationDate")]
        [JsonProperty(PropertyName = "creationDate")]
        public DateTime CreationDate { get; set; }
    }
}