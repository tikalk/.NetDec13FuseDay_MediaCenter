using System.Web;
using System.Web.Optimization;

namespace YouTubeRemotePlayer.Web
{
    public class BundleConfig
    {
        // For more information on bundling, visit http://go.microsoft.com/fwlink/?LinkId=301862
        public static void RegisterBundles(BundleCollection bundles)
        {
            #region JQuery

            bundles.Add(new ScriptBundle("~/bundles/jquery").Include(
                        "~/Scripts/libs/jquery/jquery-{version}.js"));

#           endregion

            #region Modernizer

            // Use the development version of Modernizr to develop with and learn from. Then, when you're
            // ready for production, use the build tool at http://modernizr.com to pick only the tests you need.
            bundles.Add(new ScriptBundle("~/bundles/modernizr").Include(
                        "~/Scripts/libs/modernizr-*"));

            #endregion

            #region Css

            bundles.Add(new StyleBundle("~/Content/Css").Include(
                      "~/Content/Css/*.css"));

            #endregion

            #region Angular

            bundles.Add(new ScriptBundle("~/bundles/angular").Include(
                        "~/Scripts/libs/angular/angular.js",
                        "~/Scripts/libs/angular/angular-route.js"));

            #endregion

            #region SignalR

            bundles.Add(new ScriptBundle("~/bundles/signalR").Include(
                         "~/Scripts/libs/signalR/jquery.signalR-{version}.js"));

            #endregion

            #region Foundation

            bundles.Add(new ScriptBundle("~/bundles/foundation").Include(
                      "~/Scripts/libs/foundation/foundation.js",
                      "~/Scripts/libs/foundation/foundation.*",
                      "~/Scripts/libs/foundation/app.js"));

            #endregion

            #region Application

            bundles.Add(new ScriptBundle("~/bundles/app").Include(
                         "~/Scripts/app/app.js",
                        "~/Scripts/app/services/*.js",
                        "~/Scripts/app/common/*.js",
                        "~/Scripts/app/favorites/*.js",
                         "~/Scripts/app/search/*.js"));

            #endregion
        }
    }
}
