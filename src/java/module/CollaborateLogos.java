package module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollaborateLogos {

    public static List<Map<String, String>> getCompanies() {
        List<Map<String, String>> companies = new ArrayList<>();

        // Populate companies list
        companies.add(createCompany("Amazon", "https://www.amazon.com/", "amazon.svg"));
        companies.add(createCompany("Apple", "https://www.apple.com/", "apple.svg"));
        companies.add(createCompany("Mooiko", "https://mooiko.com/", "mooiko.png"));
        companies.add(createCompany("MySoftware", "https://www.sqlacc.com/", "mysoftware.png"));
        companies.add(createCompany("Pentamaster", "https://pentamaster.com.my/", "penta.png"));
        companies.add(createCompany("SIP Technology", "https://sip-technology.com/en/", "sip.png"));
        companies.add(createCompany("Quantum Metal", "https://www.quantummetal.com.my/", "qm.webp"));
        companies.add(createCompany("Cisco", "https://www.cisco.com/", "cisco.svg"));
        companies.add(createCompany("Google", "https://about.google/intl/ALL_my/", "google.svg"));
        companies.add(createCompany("IBM", "https://www.ibm.com/us-en", "ibm.svg"));
        companies.add(createCompany("Meta", "https://about.meta.com/metaverse/", "meta.svg"));
        companies.add(createCompany("Microsoft", "https://www.microsoft.com/en-my", "microsoft.svg"));
        companies.add(createCompany("Tik Tok", "https://www.tiktok.com/en/", "tiktok.svg"));
        companies.add(createCompany("Riot Games", "https://www.riotgames.com/", "riot.svg"));

        return companies;
    }

    public static List<Map<String, String>> getUniversities() {
        List<Map<String, String>> universities = new ArrayList<>();

        // Populate universities list
        universities.add(createUniversity("University of Cambridge", "https://www.cam.ac.uk/", "Cambridge.svg"));
        universities.add(createUniversity("Massachusetts Institute of Technology", "https://www.mit.edu/", "MIT.svg"));
        universities.add(createUniversity("University of Oxford", "https://www.ox.ac.uk/", "Oxford.svg"));
        universities.add(createUniversity("Peking University", "https://english.pku.edu.cn/", "Peking.svg"));
        universities.add(createUniversity("Princeton University", "https://www.princeton.edu/", "Princeton.svg"));
        universities.add(createUniversity("Tunku Abdul Rahman University of Management & Technology", "https://tarc.edu.my/", "tarumt.png"));
        universities.add(createUniversity("Stanford University", "https://www.stanford.edu/", "Stanford.svg"));
        universities.add(createUniversity("Tsing Hua University", "https://www.tsinghua.edu.cn/en/", "Tsinghua.png"));
        universities.add(createUniversity("Harvard University", "https://www.harvard.edu/", "harvard.png"));

        return universities;
    }

    private static Map<String, String> createCompany(String name, String website, String imagePath) {
        Map<String, String> company = new HashMap<>();
        company.put("title", name);
        company.put("link", website);
        company.put("image", "./img/home/collaborate/companies/" + imagePath);
        return company;
    }

    private static Map<String, String> createUniversity(String name, String website, String imagePath) {
        Map<String, String> university = new HashMap<>();
        university.put("title", name);
        university.put("link", website);
        university.put("image", "./img/home/collaborate/universities/" + imagePath);
        return university;
    }
}
