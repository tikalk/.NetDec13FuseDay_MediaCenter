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

        private void MongoABTesting()
        {
            _mongoRepository.AddFavorite(new Model.Favorite() { Id = "Test" });
            var aaa = _mongoRepository.GetFavoriteById("Test");
            _mongoRepository.RemoveFavoriteById("Test");
            _mongoRepository.RemoveFavoriteById("1");

            var bbb = _mongoRepository.GetFavoriteById("Test");
        }

        public ActionResult Index()
        {
            ViewBag.Title = "Home Page";

            return View();
        }
    }
}
