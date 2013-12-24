using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace YouTubeRemotePlayer.Web.Controllers
{
    public class HomeController : Controller
    {

        private readonly MongoDBRepository _mongoRepository;

        public HomeController(MongoDBRepository mongoRepositiry)
        {
            _mongoRepository = mongoRepositiry;
        }

        private void MongoInsertABTesting()
        {
            _mongoRepository.AddFavorite(new Model.Favorite() { Id = "Test", UserId="ofirYaron" });
            _mongoRepository.AddFavorite(new Model.Favorite() { Id = "Test2", UserId = "dandan" });
            _mongoRepository.AddFavorite(new Model.Favorite() { Id = "Test3", UserId = "ofek" });
        }

        private void MongoRemoveABTesting()
        {
            _mongoRepository.RemoveFavoriteById("Test");
            _mongoRepository.RemoveFavoriteById("Test2");
            _mongoRepository.RemoveFavoriteById("Test3");
        }

        private long MongoCollectionCount()
        {
            return _mongoRepository.FavoriteCountByUserId("ofirYaron");
        }

        private void MongoAB()
        {
            MongoInsertABTesting();
            long a = MongoCollectionCount();
            MongoRemoveABTesting();
            long b = MongoCollectionCount();
        }

        public ActionResult Index()
        {
            ViewBag.Title = "Home Page";
            //MongoAB();

            return View();
        }
    }
}
