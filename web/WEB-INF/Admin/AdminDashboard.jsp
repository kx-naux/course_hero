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
        <title>CourseHero Admin Dashboard</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link rel="stylesheet" href="../admin_css/adminTable.css">
        <link rel="stylesheet" href="../admin_css/adminStyle.css">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
        <jsp:useBean id="allTransactions" class="List<JPAEntity.Transactions>" scope="request" />
        <jsp:useBean id="relatedTransProd" class="Map<String, List<JPAEntity.Product>>" scope="request" />
        <jsp:useBean id="relatedTransQty" class="Map<String, List<Integer>>" scope="request" />
    </head>
    <body> 
        <div class="flex-container dashboard">
            <!-------------------- START OF GLOBAL SIDEBAR ------------------->
            <%@ include file="./Components/global-sidebar.jsp" %>
            <!-------------------- END OF GLOBAL SIDEBAR ------------------->
            <div class="main-content">
                <h1>Dashboard</h1>
                <div class="horizontal-line"></div>
                <div class="insights">
                    <div class="sales">
                        <i class="ri-bar-chart-grouped-line"></i>
                        <div class="middle">
                            <div class="left">
                                <h3>Total Sales</h3>
                                <h1>RM 25,024<h1>
                            </div>
                            <div class="progress">
                                <svg>
                                    <circle cx='38' cy='38' r='36'></circle>
                                </svg>
                                <div class="number">
                                    <p>81%</p>
                                </div>
                            </div>
                        </div>
                        <small class="text-muted">Last 24 Hours</small>
                    </div>
                    
                
                <!------------- END OF SALES --------->
                    <div class="expenses">
                        <i class="ri-line-chart-line"></i>
                        <div class="middle">
                            <div class="left">
                                <h3>Total Expenses</h3>
                                <h1>RM 14,160<h1>
                            </div>
                            <div class="progress">
                                <svg>
                                    <circle cx='38' cy='38' r='36'></circle>
                                </svg>
                                <div class="number">
                                    <p>62%</p>
                                </div>
                            </div>
                        </div>
                        <small class="text-muted">Last 24 Hours</small>
                    </div>
                <!------------- END OF EXPENSES --------->
                <div class="income">
                    <i class="ri-money-dollar-circle-line"></i>
                        <div class="middle">
                            <div class="left">
                                <h3>Total Income</h3>
                                <h1>RM 10,864<h1>
                            </div>
                            <div class="progress">
                                <svg>
                                    <circle cx='38' cy='38' r='36'></circle>
                                </svg>
                                <div class="number">
                                    <p>44%</p>
                                </div>
                            </div>
                        </div>
                    <small class="text-muted">Last 24 Hours</small>
                    </div>
                <!------------- END OF INCOME --------->
                </div>
                <!----------  END OF INSIGHTS ------------------->
                
                <div class="recent-orders">
                    <div class="title">
                        <h2>Recent Orders</h2>
                        <div class="horizontal-line"></div>
                    </div>
                    <table>
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
                            <%
                                int x = 0;
                                for (Transactions eachTransactions : allTransactions) {%>
                                       <% x++; %>
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
                                    <%=currentProduct.get(i).getProdName()%>
                                    <%
                                        double originalPrice = currentProduct.get(i).getPrice();
                                        double discountedPrice = (100 - currentProduct.get(i).getDiscount()) / 100 * (currentProduct.get(i).getPrice());
                                        double rightPrice = (originalPrice == discountedPrice) ? originalPrice : discountedPrice;

                                    %>
                                    <small class="d-block">x <%=currentQty.get(i)%> = RM <%=String.format("%.2f", rightPrice * currentQty.get(i))%></small>
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
                            <% if(x == 5){break;}}%>
                        </tbody>
                    </table>
                    <a class="show-all" href="<%= webpath.getPageUrl("manage-order") %>">Show All</a>
                </div>
            </div>
            <!----------  END OF MAIN ------------------->
            
            <div class="right">
                <div class="top">
                    <!-- include dark theme toggler -->
                    <%@ include file="./Components/dark-theme-toggler.jsp" %>
                    
                    <!<!-- include profile  -->
                    <%@ include file="./Components/global-profile.jsp" %>
                </div>
                <!----------  END OF TOP ------------------->
                <div class="recent-updates">
                    <div class="title">
                        <h2>Recent Updates</h2>
                        <div class="horizontal-line"></div>
                    </div>
                    <div class="updates">
                        <div class="update">
                            <div class="profile-photo">
                                <img src="../img/admin/default_profile.png" alt="alt"/>
                            </div>
                            <div class="message">
                                <p><b>Mike Tyson</b> received his order of Night lion tech GPS drone.</p>
                                <small class="text-muted">2 Minutes Ago</small>
                            </div>
                        </div>
                        <div class="update">
                            <div class="profile-photo">
                                <img src="../img/admin/default_profile.png" alt="alt"/>
                            </div>
                            <div class="message">
                                <p><b>Mike Tyson</b> received his order of Night lion tech GPS drone.</p>
                                <small class="text-muted">2 Minutes Ago</small>
                            </div>
                        </div>
                        <div class="update">
                            <div class="profile-photo">
                                <img src="../img/admin/default_profile.png" alt="alt"/>
                            </div>
                            <div class="message">
                                <p><b>Mike Tyson</b> received his order of Night lion tech GPS drone.</p>
                                <small class="text-muted">2 Minutes Ago</small>
                            </div>
                        </div>
                    </div>
                </div>
                <!----------  END OF RECENT UPDATES ------------------->
                <div class="sales-analytics">
                    <div class="title">
                        <h2>Sales Analytics</h2>
                        <div class="horizontal-line"></div>
                    </div>
                    <div class="item online">
                        <div class="icon">
                            <i class="ri-file-list-line"></i>
                        </div>
                        <div class="right">
                            <div class="info">
                                <h3>ONLINE ORDERS</h3>
                                <small class="text-muted">Last 24 Hours</small>
                            </div>
                            <h5 class="success">+39%</h5>
                            <h3>3849</h3>
                        </div>
                    </div>
                    <div class="item offline">
                        <div class="icon">
                            <i class="ri-file-list-line"></i>
                        </div>
                        <div class="right">
                            <div class="info">
                                <h3>OFFLINE ORDERS</h3>
                                <small class="text-muted">Last 24 Hours</small>
                            </div>
                            <h5 class="danger">+39%</h5>
                            <h3>1100</h3>
                        </div>
                    </div>
                    <div class="item customers">
                        <div class="icon">
                            <i class="ri-user-add-line"></i>
                        </div>
                        <div class="right">
                            <div class="info">
                                <h3>NEW CUSTOMERS</h3>
                                <small class="text-muted">Last 24 Hours</small>
                            </div>
                            <h5 class="success">+39%</h5>
                            <h3>849</h3>
                        </div>
                    </div>
                    <div class="item add-product">
                        <div>
                            <i class="ri-menu-line"></i>
                            <h3>Add Product</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
