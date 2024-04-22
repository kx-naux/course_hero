package module;

import java.util.HashMap;
import java.util.Map;

public class WebPath {

    private Map<String, String> pageUrls;

    public WebPath() {
        pageUrls = new HashMap<>();
        pageUrls.put("login", "/login");
        pageUrls.put("sign up","/sign-up");
        pageUrls.put("forget password","/forget-pw");
        pageUrls.put("home", "/home");
        pageUrls.put("about us", "/about-us");
        pageUrls.put("products", "/products");
        pageUrls.put("learning", "/learning");
        pageUrls.put("cart","/cart");
        pageUrls.put("wishlist","/wishlist");
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
