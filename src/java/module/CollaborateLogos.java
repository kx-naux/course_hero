package module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollaborateLogos {

    public static List<Map<String, String>> getCompanies() {
        List<Map<String, String>> companies = new ArrayList<>();

        // Populate companies list
        companies.add(createCompany("Amazon", "https://www.amazon.com/", "./img/home/collaborate/companies/amazon.svg"));
        companies.add(createCompany("Apple", "https://www.apple.com/", "./img/home/collaborate/companies/apple.svg"));
        companies.add(createCompany("Mooiko", "https://mooiko.com/", "./img/home/collaborate/companies/mooiko.png"));
        companies.add(createCompany("MySoftware", "https://www.sqlacc.com/", "./img/home/collaborate/companies/mysoftware.png"));
        companies.add(createCompany("Pentamaster", "https://pentamaster.com.my/", "./img/home/collaborate/companies/penta.png"));
        companies.add(createCompany("SIP Technology", "https://sip-technology.com/en/", "./img/home/collaborate/companies/sip.png"));
        companies.add(createCompany("Quantum Metal", "https://www.quantummetal.com.my/", "./img/home/collaborate/companies/qm.webp"));
        companies.add(createCompany("Cisco", "https://www.cisco.com/", "./img/home/collaborate/companies/cisco.svg"));
        companies.add(createCompany("Google", "https://about.google/intl/ALL_my/", "./img/home/collaborate/companies/google.svg"));
        companies.add(createCompany("IBM", "https://www.ibm.com/us-en", "./img/home/collaborate/companies/ibm.svg"));
        companies.add(createCompany("Meta", "https://about.meta.com/metaverse/", "./img/home/collaborate/companies/meta.svg"));
        companies.add(createCompany("Microsoft", "https://www.microsoft.com/en-my", "./img/home/collaborate/companies/microsoft.svg"));
        companies.add(createCompany("Tik Tok", "https://www.tiktok.com/en/", "./img/home/collaborate/companies/tiktok.svg"));
        companies.add(createCompany("Riot Games", "https://www.riotgames.com/", "./img/home/collaborate/companies/riot.svg"));

        return companies;
    }

    public static List<Map<String, String>> getUniversities() {
        List<Map<String, String>> universities = new ArrayList<>();

        // Populate universities list
        universities.add(createUniversity("University of Cambridge", "https://www.cam.ac.uk/", "./img/home/collaborate/universities/Cambridge.svg"));
        universities.add(createUniversity("Massachusetts Institute of Technology", "https://www.mit.edu/", "./img/home/collaborate/universities/MIT.svg"));
        universities.add(createUniversity("University of Oxford", "https://www.ox.ac.uk/", "./img/home/collaborate/universities/Oxford.svg"));
        universities.add(createUniversity("Peking University", "https://english.pku.edu.cn/", "./img/home/collaborate/universities/Peking.svg"));
        universities.add(createUniversity("Princeton University", "https://www.princeton.edu/", "./img/home/collaborate/universities/Princeton.svg"));
        universities.add(createUniversity("Tunku Abdul Rahman University of Management & Technology", "https://tarc.edu.my/", "./img/home/collaborate/universities/tarumt.png"));
        universities.add(createUniversity("Stanford University", "https://www.stanford.edu/", "./img/home/collaborate/universities/Stanford.svg"));
        universities.add(createUniversity("Tsing Hua University", "https://www.tsinghua.edu.cn/en/", "./img/home/collaborate/universities/Tsinghua.png"));
        universities.add(createUniversity("Harvard University", "https://www.harvard.edu/", "./img/home/collaborate/universities/harvard.png"));

        return universities;
    }

    private static Map<String, String> createCompany(String name, String website, String imagePath) {
        Map<String, String> company = new HashMap<>();
        company.put("title", name);
        company.put("link", website);
        company.put("image", imagePath);
        return company;
    }

    private static Map<String, String> createUniversity(String name, String website, String imagePath) {
        Map<String, String> university = new HashMap<>();
        university.put("title", name);
        university.put("link", website);
        university.put("image", imagePath);
        return university;
    }
}
