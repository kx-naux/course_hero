package model;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportDA {

    private EntityManager em;

    public ReportDA(EntityManager em) {
        this.em = em;
    }

    public Map<String, Integer> getTop10CourseSales() {
        String jpql = "SELECT c.courseId, COUNT(cs.subscriptionsId) AS totalSales " +
                "FROM CourseSubscriptions cs " +
                "JOIN cs.course c " +
                "GROUP BY c.courseId " +
                "ORDER BY totalSales DESC";

        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setMaxResults(10);

        List<Object[]> results = query.getResultList();
        Map<String, Integer> top10Sales = new HashMap<>();
        for (Object[] result : results) {
            String courseId = (String) result[0];
            long totalSales = (long) result[1];
            top10Sales.put(courseId, (int) totalSales);
        }
        return top10Sales;
    }
}