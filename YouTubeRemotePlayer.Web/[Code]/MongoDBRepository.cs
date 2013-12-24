using MongoDB.Driver;
using MongoDB.Driver.Builders;
using MongoDB.Driver.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using YouTubeRemotePlayer.Web.Model;

namespace YouTubeRemotePlayer.Web
{
    public class MongoDBRepository
    {
        private readonly MongoDatabase _database;
        private readonly MongoCollection<Favorite> _favoriteCollection;
        private readonly IQueryable<Favorite> _favoriteQuery;

        public MongoDBRepository(MongoDatabase database)
        {
            _database = database;
            _favoriteCollection = _database.GetCollection<Favorite>("Favorites");
            _favoriteQuery = _favoriteCollection.AsQueryable();
        }

        public Favorite GetFavoriteById(string id)
        {
            var res = _favoriteQuery.FirstOrDefault(e => e.Id == id);

            return res;
        }

        public bool AddFavorite(Favorite favorite)
        {
            try
            {
                favorite.CreationDate = DateTime.Now;
                //_favoriteCollection.Insert(favorite);
                _favoriteCollection.Save(favorite); //_database);
                return true;
            }
            catch (Exception ex)
            {
                //Fail
            }
            return false;
        }

        public bool RemoveFavoriteById(string id)
        {
            try 
            {
                var fav = GetFavoriteById(id);
                if (fav!=null)
                {
                    _favoriteCollection.Remove(Query.EQ("_id",id));
                    //_favoriteCollection.Save(_database);
                    return true;
                }
                return false;

            }
            catch(Exception ex)
            {

            }
            return false;
        }

        public long FavoriteCountByUserId(string userId)
        {
            return _favoriteCollection.Count(Query.EQ("userId", userId));
        }

        public long FavoriteCount()
        {
            return _favoriteCollection.Count();
        }

        public bool Persist()
        {
            return false;
        }
    }
}