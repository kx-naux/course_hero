package module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestimonialData {

    public static List<Map<String, String>> getTestimonials() {
        List<Map<String, String>> testimonials = new ArrayList<>();

        testimonials.add(createTestimonial("Michael Johnson", "United States", "Software Engineer at Riot Games", "This course is a game-changer! Highly recommended for anyone serious about leveling up their skills.", "avatar-1.png"));
        testimonials.add(createTestimonial("David Smith", "United Kingdom", "Project Manager at Google", "If you invest in your education and build your skills, you will see major growth in your career. Just a few months after I began investing in myself, my life has changed in the best way.", "avatar-2.png"));
        testimonials.add(createTestimonial("Jessica Lee", "Singapore", "Data Scientist at Walmart", "I can't recommend this course enough! It provided me with the tools and knowledge I needed to excel in my role at Walmart. Truly transformative.", "avatar-3.png"));
        testimonials.add(createTestimonial("Sarah Johnson", "United States", "Accountant at Accenture", "The instructors didn't just teach; they inspired. Their passion for accounting ignited a fire within me to pursue excellence in my profession.", "avatar-4.png"));
        testimonials.add(createTestimonial("Jennifer Park", "Canada", "Software Engineer at Apple", "As a software engineer at Apple, staying ahead in the ever-evolving tech landscape is essential. The course content was not only relevant but also delivered in a clear and engaging manner.", "avatar-5.png"));

        return testimonials;
    }

    private static Map<String, String> createTestimonial(String name, String country, String position, String comment, String imagePath) {
        Map<String, String> testimonial = new HashMap<>();
        testimonial.put("name", name);
        testimonial.put("country", country);
        testimonial.put("position", position);
        testimonial.put("comment", comment);
        testimonial.put("image", "./img/home/testimonial/" + imagePath);
        return testimonial;
    }
}
