<!--StyleSheet-->
<link rel="stylesheet" href="../admin_css/adminGlobalSidebar.css">            
<div class="global-sidebar">
                <div class="sidebar-top">
                    <!-- Logo side -->
                    <div class="logo"> 
                        <a href="<%= webpath.getPageUrl("home") %>">
                            <img src = "../img/Logo.png">
                        </a>
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
                            <a href="<%= webpath.getPageUrl("dashboard") %>">
                                <i class="ri-home-line"></i>
                                <h3 class="nav-item">Dashboard</h3>
                            </a>
                            <span class="tooltip">Dashboard</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-file-list-3-line"></i>
                                <h3 class="nav-item">Orders</h3>
                            </a>

                            <span class="tooltip">Manage Orders</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-file-line"></i>
                                <h3 class="nav-item">Contract Entries</h3>
                            </a>

                            <span class="tooltip">Contract Entries</span>
                        </li>
                        <li>
                            <a class="sub-menu-parent" href="#">
                                <i class="ri-user-line"></i>
                                <h3 class="nav-item">Client</h3>
                                <i class="arrow ri-arrow-down-s-line"></i>
                            </a>
                            <ul class="sub-menu">
                                <li>
                                    <a href="<%= webpath.getPageUrl("manage-client") %>">
                                        <h3>Manage Client</h3>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <h3>Add New Client</h3>
                                    </a>
                                </li>
                            </ul>
                            <span class="tooltip">Manage Client</span>
                        </li>
                        <li>
                            <a class="sub-menu-parent" href="#">
                                <i class="ri-box-3-line"></i>
                                <h3 class="nav-item">Product</h3>
                                <i class="arrow ri-arrow-down-s-line"></i>
                            </a>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#">
                                        <h3>Manage Product</h3>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <h3>Add New Product</h3>
                                    </a>
                                </li>
                            </ul>
                            <span class="tooltip">Manage Product</span>
                        </li>
                        <li>
                            <a class="sub-menu-parent" href="#">
                                <i class="ri-team-line"></i>
                                <h3 class="nav-item">Staff</h3>
                                <i class="arrow ri-arrow-down-s-line"></i>
                            </a>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#">
                                        <h3>Manage Staff</h3>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <h3>Add New Staff</h3>
                                    </a>
                                </li>
                            </ul>
                            <span class="tooltip">Manage Staff</span>
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
                                <i class="ri-folder-chart-line"></i>
                                <h3 class="nav-item">Report</h3>
                            </a>
                            <span class="tooltip">Report</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ri-settings-2-line"></i>
                                <h3 class="nav-item">Settings</h3>
                            </a>
                            <span class="tooltip">Settings</span>
                        </li>
                        <li class="logout">
                            <a href="#">
                                <i class="ri-logout-circle-line"></i>
                                <h3 class="nav-item">Logout</h3>
                            </a>
                            <span class="tooltip">Logout</span>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- Right after the browser renders the sidebar -->
            <script type="text/javascript">
                // If localStorage is supported by the browser
                if (typeof(Storage) !== "undefined") {
                    // If we need to open the bar
                    if(localStorage.getItem("sidebar") === "opened"){
                        // Open the bar
                        document.querySelector('.global-sidebar').classList.add('active');
                    }
                    
                    if(localStorage.getItem("submenuStatus") === "opened"){
                        let mainMenu = document.querySelectorAll(".sub-menu-parent");
                        let indexOfSubmenu = Number(localStorage.getItem("indexOfSubmenu"));
                        let subMenu = mainMenu[indexOfSubmenu].nextElementSibling;

                        subMenu.classList.add("active");
                    }
                }
            </script>
            <script type="text/javascript" src="../admin_js/global_sidebar.js"></script>