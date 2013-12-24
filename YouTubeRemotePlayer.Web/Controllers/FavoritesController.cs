using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace YouTubeRemotePlayer.Web.Controllers
{
    public class FavoritesController : Controller
    {

        private readonly MongoDBRepository _mongoRepository;

        public FavoritesController(MongoDBRepository mongoRepositiry)
        {
            _mongoRepository = mongoRepositiry;
        }

        [HttpPost]
        public ActionResult Add(string videoId, string title)
        {
            _mongoRepository.AddFavorite(new Model.Favorite() { Id = videoId, Title = title });
            return Json(new { success = true });
        }

        [HttpPost]
        public ActionResult Remove(string videoId)
        {
            _mongoRepository.RemoveFavoriteById(videoId);
            var lst = _mongoRepository.FavoritesGetAll().Select(f => new { videoId = f.Id, title = f.Title }).ToList();
            return Json(lst);
        }

        [HttpGet]
        public ActionResult getList()
        {
            var lst = _mongoRepository.FavoritesGetAll().Select(f => new { videoId = f.Id, title = f.Title }).ToList();
            return Json(lst, JsonRequestBehavior.AllowGet);
        }
    }
}
