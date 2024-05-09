<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Generation</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link rel="stylesheet" href="../admin_css/adminReport.css">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body> 
        <script type="text/javascript" src="../admin_js/adminChart.js"></script>
        <div class="flex-container">
            <!-------------------- START OF GLOBAL SIDEBAR ------------------->
            <%@ include file="./Components/global-sidebar.jsp" %>
            <!-------------------- END OF GLOBAL SIDEBAR ------------------->
            <div class="main-content">
                <div class="main-top">
                    <div class="main-top-left">
                        <h1>Report Generation</h1>
                    </div>                    
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
                </div>
                <div class="horizontal-line"></div>
                <div class="filter-flex-container">
                <div class="filter-container">
                    <div class="input-container" title="Start of Date">
                        <input type="date">
                    </div>
                    <div class="input-container" title="End of Date">
                        <input type="date">
                    </div>
                    <div class="submit-container">
                        <input type="submit" value="Filter" class="submit-button">
                    </div>
                </div>
                </div>
                <div class="insights report-container number">
                    <div class="report sales">
                        <i class="ri-bar-chart-grouped-line"></i>
                        <div class="expenses">`
                            <div class="middle">
                                <div class="left">
                                    <h3>Sales</h3>
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
                    </div>
                    <div class="report sales">
                        <i class="ri-bar-chart-grouped-line"></i>
                        <div class="expenses">`
                            <div class="middle">
                                <div class="left">
                                    <h3>Sales</h3>
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
                    </div>
                    <div class="report sales">
                        <i class="ri-bar-chart-grouped-line"></i>
                        <div class="expenses">`
                            <div class="middle">
                                <div class="left">
                                    <h3>Sales</h3>
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
                    </div>
                    <div class="report sales">
                        <i class="ri-bar-chart-grouped-line"></i>
                        <div class="expenses">`
                            <div class="middle">
                                <div class="left">
                                    <h3>Sales</h3>
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
                    </div>
                </div>
                <div class="insights report-container graph">
                    <div class="graph sales">
                    <h2>Monthly Sales Figure</h2>
                    <canvas id="myChart" ></canvas>
                    <script>
                      const ctx = document.getElementById('myChart');

                      new Chart(ctx, {
                        type: 'line',
                        data: {
                          labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                          datasets: [{
                            label: '# of Votes',
                            data: [12, 19, 3, 5, 2, 3],
                            borderWidth: 1
                          }]
                        },
                        options: {
                          scales: {
                            y: {
                              beginAtZero: true
                            }
                          }
                        }
                      });
                    </script>
                    </div>
                    <div id="salesBar" class="graph sales">
                    <h2>Sales Report</h2>
                    <canvas id="myChart2"></canvas>

                    <script>
                      const ctx2 = document.getElementById('myChart2');

                      new Chart(ctx2, {
                        type: 'bar',
                        data: {
                          labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                          datasets: [{
                            label: '# of Votes',
                            data: [12, 19, 3, 5, 2, 3],
                            borderWidth: 1
                          },{
                            label: '# of Votes',
                            data: [12, 19, 3, 5, 2, 3],
                            borderWidth: 1
                          }]
                        },
                        options: {
                          scales: {
                            y: {
                              beginAtZero: true
                            }
                          }
                        }
                      });
                    </script>
                </div>
                </div>
                <div class="insights report-container ranking">
                    <div class="top-report">
                        <h2>Top Selling Merchandise</h2>
                        <div class="ranking">
                        <h3>#1</h3>
                        <img src="../img/merchandise/mug.png" alt="alt"/>
                        <div>
                            <h3>Mug</h3>
                            <small>Sales: 15000</small>
                        </div>
                        </div>
                        <div class="ranking">
                        <h3>#2</h3>
                        <img src="../img/merchandise/mug.png" alt="alt"/>
                        <div>
                            <h3>Mug</h3>
                            <small>Sales: 15000</small>
                        </div>
                        </div>
                        <div class="ranking">
                        <h3>#3</h3>
                        <img src="../img/merchandise/mug.png" alt="alt"/>
                        <div>
                            <h3>Mug</h3>
                            <small>Sales: 15000</small>
                        </div>
                        </div>
                        <div class="ranking">
                        <h3>#4</h3>
                        <img src="../img/merchandise/mug.png" alt="alt"/>
                        <div>
                            <h3>Mug</h3>
                            <small>Sales: 15000</small>
                        </div>
                        </div>
                        <div class="ranking">
                        <h3>#5</h3>
                        <img src="../img/merchandise/mug.png" alt="alt"/>
                        <div>
                            <h3>Mug</h3>
                            <small>Sales: 15000</small>
                        </div>
                        </div>
                    </div>
                    <div class="top-report">
                        <h2>Top Selling Course</h2>
                        <div class="ranking">
                            <h3>#1</h3>
                            <img src="../img/course/beginner_excel.jpg" alt="alt"/>
                            <div>
                                <h3>Mug</h3>
                                <small>Sales: 15000</small>
                            </div>
                        </div>
                        <div class="ranking">
                            <h3>#2</h3>
                            <img src="../img/course/beginner_excel.jpg" alt="alt"/>
                            <div>
                                <h3>Mug</h3>
                                <small>Sales: 15000</small>
                            </div>
                        </div>
                        <div class="ranking">
                            <h3>#3</h3>
                            <img src="../img/course/beginner_excel.jpg" alt="alt"/>
                            <div>
                                <h3>Mug</h3>
                                <small>Sales: 15000</small>
                            </div>
                        </div>
                        <div class="ranking">
                            <h3>#4</h3>
                            <img src="../img/course/beginner_excel.jpg" alt="alt"/>
                            <div>
                                <h3>Mug</h3>
                                <small>Sales: 15000</small>
                            </div>
                        </div>
                        <div class="ranking">
                            <h3>#5</h3>
                            <img src="../img/course/beginner_excel.jpg" alt="alt"/>
                            <div>
                                <h3>Mug</h3>
                                <small>Sales: 15000</small>
                            </div>
                        </div>
                    </div>
                    <div class="graph sales geo">
                        <h2>Top sales Location Report</h2>
                        <canvas id="myChart3"></canvas>

                        <script>
                          const ctx3 = document.getElementById('myChart3');

                          new Chart(ctx3, {
                            type: 'doughnut',
                            data: {
                              labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                              datasets: [{
                                label: '# of Votes',
                                data: [12, 19, 3, 5, 2, 3],
                                borderWidth: 1
                              }]
                            },
                            options: {
                              scales: {
                                y: {
                                  beginAtZero: true
                                }
                              }
                            }
                          });
                        </script>
                    </div>
                <div class="graph sales pie">
                <h2>Total Sales Report</h2>
                <canvas id="myChart4"></canvas>

                    <script>
                      const ctx4 = document.getElementById('myChart4');

                      new Chart(ctx4, {
                        type: 'pie',
                        data: {
                          labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                          datasets: [{
                            label: '# of Votes',
                            data: [12, 19, 3, 5, 2, 3],
                            borderWidth: 1
                          }]
                        },
                        options: {
                          scales: {
                            y: {
                              beginAtZero: true
                            }
                          }
                        }
                      });
                    </script>
                </div>
                </div>
            </div>
            <!----------  END OF MAIN ------------------->
            </div>
            <!-- Right after the browser renders the content -->
            <script type="text/javascript">
                // If localStorage is supported by the browser
                if (typeof(Storage) !== "undefined") {
                    // If we need to open the bar
                    if(localStorage.getItem("theme") === "dark"){

                        // check the dark theme button
                        let inputContainers = document.querySelectorAll(".input-container");
                        for (var i = 0; i < inputContainers.length; ++i) {
                        inputContainers[i].classList.add("dark");
                        }
                    }
                }
            </script>
            <script type="text/javascript" src="../admin_js/sweetalert2.all.js"></script>
    </body>
</html>
