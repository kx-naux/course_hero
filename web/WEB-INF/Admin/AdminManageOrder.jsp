<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="JPAEntity.Product"%>
<%@page import="JPAEntity.Transactions"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Orders</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link rel="stylesheet" href="../admin_css/adminOrder.css">
        <link rel="stylesheet" href="../admin_css/adminTable.css">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
        <jsp:useBean id="allTransactions" class="List<JPAEntity.Transactions>" scope="request" />
        <jsp:useBean id="relatedTransProd" class="Map<String, List<JPAEntity.Product>>" scope="request" />
        <jsp:useBean id="relatedTransQty" class="Map<String, List<Integer>>" scope="request" />
    </head>
    <body> 
        <div class="flex-container">
            <!-------------------- START OF GLOBAL SIDEBAR ------------------->
            <%@ include file="./Components/global-sidebar.jsp" %>
            <!-------------------- END OF GLOBAL SIDEBAR ------------------->
            <div class="main-content">
                <div class="main-top">
                    <div class="main-top-left">
                        <h1>Manage Orders</h1>
                    </div>                    
                    <div class="top">
                        <!-- include dark theme toggler -->
                        <%@ include file="./Components/dark-theme-toggler.jsp" %>

                        <!-- include profile  -->
                        <%@ include file="./Components/global-profile.jsp" %>
                    </div>
                </div>
                <div class="horizontal-line"></div>
                <!----------  START OF TABLE ------------------->
                <div class="table-container">
                    <table class="client-table custom-table">
                        <thead>
                            <tr>
                                <th scope="col">
                                    Transaction 
                                </th>
                                <th scope="col">
                                    User ID
                                </th>
                                <th scope="col">
                                    Product
                                </th>
                                <th scope="col">
                                    Status
                                </th>
                                <th scope="col">
                                    Total
                                </th>
                                <th scope="col">
                                    Transaction Type
                                </th>
                                <th scope="col">
                                    Order Date
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Transactions eachTransactions : allTransactions) {%>
                            <tr scope="row">
                                <td>
                                    <%=eachTransactions.getTransactionId()%>
                                </td>
                                <td>
                                    <%=eachTransactions.getUserId().getUserId()%>
                                </td>
                                <td>
                                    <%
                                        List<Product> currentProduct = relatedTransProd.get(eachTransactions.getTransactionId());
                                        List<Integer> currentQty = relatedTransQty.get(eachTransactions.getTransactionId());
                                        for (int i = 0; i < currentProduct.size(); i++) {
                                    %>
                                    <%=currentProduct.get(i).getProdName() %>
                                    <%
                                        double originalPrice = currentProduct.get(i).getPrice();
                                        double discountedPrice = (100 - currentProduct.get(i).getDiscount()) / 100 * (currentProduct.get(i).getPrice());
                                        double rightPrice = (originalPrice==discountedPrice)?originalPrice:discountedPrice;
                                        
                                    %>
                                    <small class="d-block">x <%=currentQty.get(i) %> = RM <%=String.format("%.2f", rightPrice*currentQty.get(i)) %></small>
                                    <%}%>
                                </td>
                                <td><%=eachTransactions.getStatus()%></td>
                                <td><%=String.format("RM %.2f", eachTransactions.getTotal())%></td>
                                <td><%=eachTransactions.getTransactionType()%></td>
                                <td><%
                                    // Get the date object from eachTransactions.getTimeAdded()
                                    Date date = eachTransactions.getTimeAdded();

                                    // Create a SimpleDateFormat object with the desired date format
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

                                    // Format the date to a string in the desired format
                                    String formattedDate = sdf.format(date);
                                    %><%= formattedDate%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <div class="tablenav-pages"> 
                        <div class="display-total-item-container">
                            <span class="display-total-item">Showing 7 items of <%=allTransactions.size()%> items</span>
                        </div>
                        <div class="pagination-links-container">
                            <span class="pagination-links">
                                <a class="first-page" href="#"><<</a>
                                <a class="prev-page" href="#"><</a>
                            </span>
                            <span class="pagination-input">
                                <div class="input-container">
                                    <input class="current-page" type="text" value="1" size="1"/>
                                </div>
                                <span class="tablenav-paging-text">
                                    of
                                    <span class="total-pages">2</span>
                                </span>
                            </span>
                            <span class="pagination-links">
                                <a class="next-page" href="#">></a>
                                <a class="last-page" href="#">>></a>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <!----------  END OF MAIN ------------------->
        </div>
        <!-- Right after the browser renders the content -->
        <script type="text/javascript">
            // If localStorage is supported by the browser
            if (typeof (Storage) !== "undefined") {
                // If we need to open the bar
                if (localStorage.getItem("theme") === "dark") {

                    // check the dark theme button
                    let inputContainers = document.querySelectorAll(".input-container");
                    for (var i = 0; i < inputContainers.length; ++i) {
                        inputContainers[i].classList.add("dark");
                    }
                }
            }
        </script>
        <script type="text/javascript" src="../admin_js/sweetalert2.all.js"></script>
        <script type="text/javascript" src="../admin_js/adminTable.js"></script>
    </body>
</html>
