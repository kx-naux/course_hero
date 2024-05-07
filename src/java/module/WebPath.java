package module;

import java.util.HashMap;
import java.util.Map;

public class WebPath {

    private Map<String, String> pageUrls;

    public WebPath() {
        pageUrls = new HashMap<>();

        // learners
        pageUrls.put("login", "/login");
        pageUrls.put("logout", "/Logout");
        pageUrls.put("sign up", "/sign-up");
        pageUrls.put("forget password", "/forget-pw");
        pageUrls.put("home", "/home");
        pageUrls.put("about us", "/about-us");
        pageUrls.put("products", "/courses");
        pageUrls.put("author", "/author");
        pageUrls.put("learning", "/learning");
        pageUrls.put("cart", "/cart");
        pageUrls.put("wishlist", "/wishlist");
        pageUrls.put("merchandises", "/merchandises");
        pageUrls.put("merchandise", "/merch");
        pageUrls.put("help", "/help");
        pageUrls.put("promotion", "/promotion");
        pageUrls.put("profile", "/UpUserProfileBasic");
        pageUrls.put("profile address", "/edit-user-address");
        pageUrls.put("profile photo", "/update-user-pfp");
        pageUrls.put("profile security", "/edit-security");
        pageUrls.put("profile close", "/user-delete-account");

        //admin
        pageUrls.put("dashboard", "/admin/dashboard");
        pageUrls.put("manage-client", "/admin/manage-client");
        pageUrls.put("manage-course", "/admin/manage-course");
        pageUrls.put("add-course", "/admin/add-course");
    }

    public String getPageUrl(String page) {
        String url = pageUrls.get(page);
        if (url != null) {
            return "http://localhost:8080/course_hero" + url;
        } else {
            return "#";
        }
    }
}
