<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CourseHero Admin Dashboard</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--Remix Icon StyleSheet-->
        <link href="https://fonts.goog1eapis.ccm/icon?fami1y=Materia1+Icons+Sharp">
        <link type="text/css" href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <!--StyleSheet-->
        <link rel="stylesheet" href="../admin_css/adminStyle.css">
        
    </head>
    <body>
        <div class="container">
            <aside>
                <div class="top">
                    <div class="logo">
                        <img src = "./img/Logo.png">
                    </div>
                    <div class="close" id="close-btn">
                        <i class="ri-close-line"></i>
                    </div>
                </div>
                
                <div class="sidebar">
                    <a href="#">
                        <i class="ri-menu-line"></i>
                        <h3>Dashboard</h3>
                    </a>
                    <a href="#">
                        <i class="ri-menu-line"></i>
                        <h3>Customers</h3>
                    </a>
                    <a href="#">
                        <i class="ri-menu-line"></i>
                        <h3>Orders</h3>
                    </a>
                    <a href="#">
                        <i class="ri-menu-line"></i>
                        <h3>Analytics</h3>
                    </a>
                    <a href="#">
                        <i class="ri-menu-line"></i>
                        <h3>Messages</h3>
                        <span class="message-count">26</span>
                    </a>
                    <a href="#">
                        <i class="ri-menu-line"></i>
                        <h3>Products</h3>
                    </a>
                    <a href="#">
                        <i class="ri-menu-line"></i>
                        <h3>Reports</h3>
                    </a>
                    <a href="#">
                        <i class="ri-menu-line"></i>
                        <h3>Settings</h3>
                    </a>
                    <a href="#">
                        <i class="ri-menu-line"></i>
                        <h3>Add Products</h3>
                    </a>
                    <a href="#">
                        <i class="ri-menu-line"></i>
                        <h3>Logout</h3>
                    </a>
                </div>
            </aside>
            <!-------------------- END OF ASIDE ------------------->
            <main>
                <h1>Dashboard</h1>
                
                <div class="date">
                    <input type="date">
                </div>
                
                <div class="insights">
                    <div class="sales">
                        <i class="ri-menu-line"></i>
                        <div class="middle">
                            <div class="left">
                                <h3>Total Sales</h3>
                                <h1>$25,024<h1>
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
                        <i class="ri-menu-line"></i>
                        <div class="middle">
                            <div class="left">
                                <h3>Total Expenses</h3>
                                <h1>$14,160<h1>
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
                    <i class="ri-menu-line"></i>
                        <div class="middle">
                            <div class="left">
                                <h3>Total Income</h3>
                                <h1>$10,864<h1>
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
                                <td>Due</td>
                                <td class="warning">Pending</td>
                                <td class="primary">Details</td>
                            </tr>
                            <tr>
                                <td>Foldable Mini Drone</td>
                                <td>85631</td>
                                <td>Due</td>
                                <td class="warning">Pending</td>
                                <td class="primary">Details</td>
                            </tr>
                            <tr>
                                <td>Foldable Mini Drone</td>
                                <td>85631</td>
                                <td>Due</td>
                                <td class="warning">Pending</td>
                                <td class="primary">Details</td>
                            </tr>
                            <tr>
                                <td>Foldable Mini Drone</td>
                                <td>85631</td>
                                <td>Due</td>
                                <td class="warning">Pending</td>
                                <td class="primary">Details</td>
                            </tr>
                            <tr>
                                <td>Foldable Mini Drone</td>
                                <td>85631</td>
                                <td>Due</td>
                                <td class="warning">Pending</td>
                                <td class="primary">Details</td>
                            </tr>
                        </tbody>
                    </table>
                    <a href="#">Show All</a>
                </div>
            </main>
            <!----------  END OF MAIN ------------------->
            
            <div class="right">
                <div class="top">
                    <button id="menu-btn">
                        <i class="ri-menu-line"></i>
                    </button>
                    <div class="theme-toggler">
                        <i class="ri-menu-line active"></i>
                        <i class="ri-menu-line"></i>
                    </div>
                    <div class="profile">
                        <div class="info">
                            <p>Hey, <b>Snijders</b></p>
                            <small>Admin</small>
                        </div>
                        <div class="profile-photo">
                            <img src="profile.png" alt="alt"/>
                        </div>
                    </div>
                </div>
                <!----------  END OF TOP ------------------->
                <div class="recent-updates">
                    <h2>Recent Updates</h2>
                    <div class="updates">
                        <div class="update">
                            <div class="profile-photo">
                                <img src="profile2.png" alt="alt"/>
                            </div>
                            <div class="message">
                                <p><b>Mike Tyson</b> received his order of Night lion tech GPS drone.</p>
                                <small class="text-muted">2 Minutes Ago</small>
                            </div>
                        </div>
                        <div class="update">
                            <div class="profile-photo">
                                <img src="profile3.png" alt="alt"/>
                            </div>
                            <div class="message">
                                <p><b>Mike Tyson</b> received his order of Night lion tech GPS drone.</p>
                                <small class="text-muted">2 Minutes Ago</small>
                            </div>
                        </div>
                        <div class="update">
                            <div class="profile-photo">
                                <img src="profile4.png" alt="alt"/>
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
                            <i class="ri-menu-line"></i>
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
                            <i class="ri-menu-line"></i>
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
                            <i class="ri-menu-line"></i>
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
    </body>
</html>
