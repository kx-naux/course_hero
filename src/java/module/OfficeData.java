package module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfficeData {

    public static List<Map<String, String>> getOffices() {
        List<Map<String, String>> offices = new ArrayList<>();

        offices.add(createOffice("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3411.5068699376106!2d121.50525157414299!3d31.234388861218136!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35b270e5c2b1354d%3A0xa4d981fc8191bab!2sShanghai%20World%20Financial%20Center%2C%20%E6%95%B0%E6%B5%A6%E6%B8%AF%20Pu%20Dong%20Xin%20Qu%2C%20Shang%20Hai%20Shi%2C%20China%2C%20200120!5e0!3m2!1sen!2smy!4v1713633537317!5m2!1sen!2smy",
                "China, Shanghai, Pudong, 200120",
                "China - Shang Hai", "sh.png"));

        offices.add(createOffice("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3241.413000512551!2d139.7465789743807!3d35.666831430767274!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x60188b93a18a3a03%3A0x53be098c7d98c19b!2sToranomon%20Hills%20Mori%20Tower!5e0!3m2!1sen!2smy!4v1713633639771!5m2!1sen!2smy",
                "1 Chome-23-1 Toranomon, Minato City, Tokyo 105-6390, Japan",
                "Japan - Tokyo", "tk.png"));

        offices.add(createOffice("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3983.8212218137173!2d101.71623467340225!3d3.1418545532007176!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31cc363008476f63%3A0x97f4e0e71b480088!2sExchange%20106%20%40%20TRX!5e0!3m2!1sen!2smy!4v1713628959822!5m2!1sen!2smy",
                "Headquarter<br />Jln Tun Razak, Imbi, 55100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur",
                "Malaysia - Kuala Lumpur", "kl.png"));

        offices.add(createOffice("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d18974.142726662314!2d103.83449870625397!3d1.2803739706818629!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31da191325bc591d%3A0x4ea7065f1602c02b!2sGuoco%20Tower!5e0!3m2!1sen!2smy!4v1713633812265!5m2!1sen!2smy",
                "1 Wallich St, Singapore 078881",
                "Singapore", "sg.png"));

        offices.add(createOffice("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3164.807066983822!2d127.09996807448864!3d37.51246832722881!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca50a915f665b%3A0xabeb10cd5efdfce2!2sLOTTE%20WORLD%20TOWER!5e0!3m2!1sen!2smy!4v1713633894461!5m2!1sen!2smy",
                "300 Olympic-ro, Songpa-gu, Seoul, South Korea",
                "Korea - Seoul", "kr.png"));

        return offices;
    }

    private static Map<String, String> createOffice(String map, String address, String city, String imagePath) {
        Map<String, String> office = new HashMap<>();
        office.put("map", map);
        office.put("address", address);
        office.put("image", "./img/about_us/office/" + imagePath);
        office.put("city", city);
        return office;
    }

    public static Map<String, String> getHeadquartersOffice() {
        // Retrieve the list of offices
        List<Map<String, String>> offices = getOffices();

        // Iterate through the offices to find the headquarters office in Kuala Lumpur
        for (Map<String, String> office : offices) {
            String city = office.get("city");
            if (city != null && city.equals("Malaysia - Kuala Lumpur")) {
                return office;
            }
        }

        // Return null if no headquarters office is found
        return null;
    }

}
