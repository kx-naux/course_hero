<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CourseHero Admin Dashboard</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--Remix Icon and google icon StyleSheet-->
        <link href="https://fonts.goog1eapis.ccm/icon?fami1y=Materia1+Icons+Sharp">
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <!--StyleSheet-->
        <link rel="stylesheet" href="../admin_css/adminStyle.css">
        
    </head>
    <body>
        <div class="flex-container">
            <div class="global-sidebar">
                <div class="sidebar-top">
                    <!-- Logo side -->
                    <div class="logo"> 
                        <img src = "../img/Logo.png">
                    </div>
                    <!-- close button for the sidebar -->
                    <div class="expand-btn">
                        <i class="ri-arrow-left-double-line hide"></i>
                        <i class="ri-arrow-right-double-line expand"></i>
                    </div>
                </div>
                <div class="sidebar-navigation">
                    <!-- sidebar content -->
                    <ul>
                        <li>
                            <a href="http://localhost:8080/course_hero/admin/dashboard">
                                <i class="ri-dashboard-line"></i>
                                <h3 class="nav-item">Dashboard</h3>
                            </a>
                            <span class="tooltip">Dashboard</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-user-line"></i>
                                <h3 class="nav-item">Customers</h3>
                            </a>
                            <span class="tooltip">Customers</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-file-list-3-line"></i>
                                <h3 class="nav-item">Orders</h3>
                            </a>
                            <span class="tooltip">Orders</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-donut-chart-line"></i>
                                <h3 class="nav-item">Analytics</h3>
                            </a>
                            <span class="tooltip">Analytics</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-chat-1-line"></i>
                                <h3 class="nav-item">Messages</h3>
                                <span class="message-count">26</span>
                            </a>
                            <span class="tooltip">Messages</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-box-3-line"></i>
                                <h3 class="nav-item">Products</h3>
                            </a>
                            <span class="tooltip">Products</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-folder-chart-line"></i>
                                <h3 class="nav-item">Reports</h3>
                            </a>
                            <span class="tooltip">Reports</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-settings-2-line"></i>
                                <h3 class="nav-item">Settings</h3>
                            </a>
                            <span class="tooltip">Settings</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-logout-circle-line"></i>
                                <h3 class="nav-item">Logout</h3>
                            </a>
                            <span class="tooltip">Logout</span>
                        </li>
                    </ul>
                </div>
            </div>
            <!-------------------- END OF GLOBAL SIDEBAR ------------------->
            <div class="main-content">
                <h1>Dashboard</h1>
                
                <div class="date">
                    <input type="date">
                </div>
                
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
                    <h2>Recent Orders</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Product Number</th>
                                <th>Payment</th>
                                <th>Status</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Foldable Mini Drone</td>
                                <td>85631</td>
                                <td class="danger">Due</td>
                                <td class="warning">Pending</td>
                                <td class="primary">Details</td>
                            </tr>
                            <tr>
                                <td>Foldable Mini Drone</td>
                                <td>85631</td>
                                <td class="danger">Due</td>
                                <td class="warning">Pending</td>
                                <td class="primary">Details</td>
                            </tr>
                            <tr>
                                <td>Foldable Mini Drone</td>
                                <td>85631</td>
                                <td class="danger">Due</td>
                                <td class="warning">Pending</td>
                                <td class="primary">Details</td>
                            </tr>
                            <tr>
                                <td>Foldable Mini Drone</td>
                                <td>85631</td>
                                <td class="danger">Due</td>
                                <td class="warning">Pending</td>
                                <td class="primary">Details</td>
                            </tr>
                            <tr>
                                <td>Foldable Mini Drone</td>
                                <td>85631</td>
                                <td class="danger">Due</td>
                                <td class="warning">Pending</td>
                                <td class="primary">Details</td>
                            </tr>
                        </tbody>
                    </table>
                    <a href="#">Show All</a>
                </div>
            </div>
            <!----------  END OF MAIN ------------------->
            
            <div class="right">
                <div class="top">
                    <!-- include dark theme toggler -->
                    <%@ include file="./Components/dark-theme-toggler.jsp" %>
                    <div class="profile">
                        <div class="info">
                            <p>Hey, <b>Snijders</b></p>
                            <small>Admin</small>
                        </div>
                        <div class="profile-photo">
                            <img src="../img/admin/default_profile.png" alt="alt"/>
                        </div>
                    </div>
                </div>
                <!----------  END OF TOP ------------------->
                <div class="recent-updates">
                    <h2>Recent Updates</h2>
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
                    <h2>Sales Analytics</h2>
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
        
        <script src="../admin_js/adminHome.js"></script>
        <script src="../admin_js/global_sidebar.js"></script>
    </body>
</html>
