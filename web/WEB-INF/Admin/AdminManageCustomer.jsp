<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Customers</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link rel="stylesheet" href="../admin_css/adminCustomer.css">
        <link rel="stylesheet" href="../admin_css/adminTable.css">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body> 
        <div class="flex-container">
            <!-------------------- START OF GLOBAL SIDEBAR ------------------->
            <%@ include file="./Components/global-sidebar.jsp" %>
            <!-------------------- END OF GLOBAL SIDEBAR ------------------->
            <div class="main-content">
                <div class="main-top">
                    <div class="main-top-left">
                        <h1>Manage Customers</h1>
                    </div>                    
                    <div class="top">
                        <!-- include dark theme toggler -->
                        <%@ include file="./Components/dark-theme-toggler.jsp" %>
                        <!<!-- include profile  -->
                        <%@ include file="./Components/global-profile.jsp" %>
                    </div>
                </div>
                <div class="horizontal-line"></div>
                <!----------  START OF TABLE ------------------->
                <div class="table-container">
                    <div class="tablenav-pages"> 
                        <div class="display-total-item-container">
                        <span class="display-total-item">Showing 7 items</span>
                        </div>
                    </div>
                    <table class="client-table custom-table">
                           <thead>
                               <tr>
                                   <th scope="col">
                                       Account ID
                                   </th>
                                   <th scope="col">
                                       User ID
                                   </th>
                                   <th scope="col">
                                       Username
                                   </th>
                                   <th scope="col">
                                       Email
                                   </th>
                                   <th scope="col">
                                       Country
                                   </th>
                                   <th scope="col">
                                       Validity
                                   </th>
                                   <th scope="col">
                                       Date Joined
                                   </th>
                               </tr>
                           </thead>
                           <tbody>
                               <tr scope="row">
                                   <td>
                                       1392
                                   </td>
                                   <td>
                                       James Yates
                                       <div class="row-actions">
                                           <a class="row-actions-edit">Edit</a>
                                           |
                                           <a class="row-actions-delete">Delete</a>
                                       </div>
                                   </td>
                                   <td>
                                       Web Designer
                                       <small class="d-block">Far far away, behind the word mountains</small>
                                   </td>
                                   <td>+63 983 0962 971</td>
                                   <td>NY University</td>
                               </tr>
                           </tbody>
                       </table>
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
            <script type="text/javascript" src="../admin_js/adminTable.js"></script>
    </body>
</html>
