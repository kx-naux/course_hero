<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="JPAEntity.Users"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Staff</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link href="../admin_css/adminStaff.css" rel="stylesheet">
        <link href="../admin_css/adminTable.css" rel="stylesheet">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
    </head>
    <body> 

        <% String errMsg = (String) request.getAttribute("errorMsg");
            if (errMsg != null && errMsg.isEmpty()) { %>
        <span hidden id="errorMsg"></span>
        <%} else {%>
        <span hidden id="errorMsg"><%= errMsg == null ? "" : errMsg%></span>
        <%}%>

        <% String successMsg = (String) request.getAttribute("successMsg");
            if (successMsg != null && successMsg.isEmpty()) { %>
        <span id="successMsg" hidden></span>
        <%} else {%>
        <span id="successMsg" hidden><%= successMsg == null ? "" : successMsg%></span>
        <%}%>

        <div class="flex-container">
            <!-------------------- START OF GLOBAL SIDEBAR ------------------->
            <%@ include file="./Components/global-sidebar.jsp" %>
            <!-------------------- END OF GLOBAL SIDEBAR ------------------->
            <div class="main-content">
                <div class="main-top">
                    <div class="main-top-left">
                        <h1>Manage Staff</h1>
                        <a href="<%= webpath.getPageUrl("add-staff")%>">
                            <button type="button" class="add-button products">
                                <span class="add-button__text">Add Staff</span>
                                <span class="add-button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24" fill="none" class="svg"><line y2="19" y1="5" x2="12" x1="12"></line><line y2="12" y1="12" x2="19" x1="5"></line></svg></span>
                            </button>
                        </a>
                    </div>                    
                    <div class="top">
                        <!-- include dark theme toggler -->
                        <%@ include file="./Components/dark-theme-toggler.jsp" %>
                        <%@ include file="./Components/global-profile.jsp" %>
                    </div>
                </div>
                <div class="horizontal-line"></div>
                <form method="post" id="deleteRecordForm" action="/course_hero/admin/delete-staff" hidden>
                    <input type="text" id="id" name="id" />
                </form>
                <!--                <div class="filter-flex-container">
                                <div class="bulk-action-container">
                                    <div class="input-container">
                                        <select>
                                            <option value="-1">Bulk Actions</option>
                                            <option value="edit">Edit</option>
                                            <option value="delete">Delete</option>
                                        </select>
                                    </div>
                                    <div class="submit-container">
                                        <input type="submit" value="Apply" class="submit-button">
                                    </div>
                                </div>
                                <div class="filter-container">
                                    <div class="input-container">
                                        <input type="date">
                                    </div>
                                    <div class="input-container">
                                        <select>
                                            <option value="-1">Country</option>
                                            <option value="web">UK</option>
                                            <option value="graphic">Malaysia</option>
                                        </select>
                                    </div>
                                    <div class="input-container">
                                        <select>
                                            <option value="-1">Gender</option>
                                            <option value="edit">Male</option>
                                            <option value="delete">Female</option>
                                        </select>
                                    </div>
                                    <div class="submit-container">
                                        <input type="submit" value="Filter" class="submit-button">
                                    </div>
                                </div>
                                <div class="search-container">
                                    <div class="input-container">
                                        <input type="search" placeholder="Search..." >
                                    </div>
                                    <div class="submit-container">
                                        <input type="submit" value="Search for Clients" class="submit-button">
                                    </div>
                                </div>
                                </div>-->
                <!----------  START OF TABLE ------------------->
                <div class="table-container">
                    <div class="tablenav-pages"> 
                        <div class="display-total-item-container">
                            <span class="display-total-item">Showing <%= request.getAttribute("staffSize")%> items</span>
                        </div>
                        <!--                        <div class="pagination-links-container">
                                                    <span class="pagination-links">
                                                        <a class="first-page" href="#"><</a>
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
                                                </div>-->
                    </div>
                    <table class="product-table custom-table">
                        <thead>
                            <tr>
                                <th scope="col">
                                    Username
                                </th>
                                <th scope="col">
                                    Name
                                </th>
                                <th scope="col">
                                    Role
                                </th>
                                <th scope="col">
                                    Email
                                </th>
                                <th scope="col">
                                    Country
                                </th>
                                <th scope="col">
                                    Birth Date
                                </th>
                            </tr>
                        </thead>
                        <tbody>

                            <%
                                List<Users> staffs = (List<Users>) request.getAttribute("staffs");
                                for (Users staff : staffs) {
                            %>
                            <tr scope="row">
                                <td>
                                    <%= staff.getAccountId().getUsername()%>
                                    <div class="row-actions">
                                        <a class="row-actions-edit">Edit</a>
                                        |
                                        <a class="row-actions-delete" id="<%= staff.getUserId() %>" >Delete</a>
                                    </div>
                                </td>
                                <td>
                                    <%= staff.getDisplayName()%>
                                </td>
                                <td><%= staff.getUsertype()%></td>
                                <td><%= staff.getAccountId().getEmail()%></td>
                                <td><%= staff.getAddressId().getCountry()%></td>
                                <td>
                                    <%
                                        Date date = staff.getDob();
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        String formattedDate = formatter.format(date);
                                    %>
                                    <%= formattedDate%>
                                </td>
                                <!---------------------- Edit Item ----------------------------------->
                                <td class="edit-items-container">
                                    <form method="post" action="course_hero/admin/update-staff" />
                                    <h2>Edit Item</h2>
                                    <div class="edit-items"> 
                                        <input type="text" name="userId" value="<%= staff.getUserId()%>" hidden />
                                        <div class="update-container">
                                            <label>Username</label>
                                            <div class="input-container">
                                                <input type="text" name="username" placeholder="username" value="<%= staff.getAccountId().getUsername()%>">
                                            </div>
                                        </div>
                                        <div class="update-container">
                                            <label>Name</label>
                                            <div class="input-container">
                                                <input type="text" name="name" placeholder="name" value="<%= staff.getDisplayName()%>" >
                                            </div>
                                        </div>
                                        <div class="update-container">
                                            <label>Role</label>
                                            <div class="input-container">
                                                <input type="text" name="role" placeholder="role" value="<%= staff.getUsertype()%>" >
                                            </div>
                                        </div>
                                        <div class="update-container">
                                            <label>Email</label>
                                            <div class="input-container">
                                                <input type="email" name="email" placeholder="email" value="<%= staff.getAccountId().getEmail()%>" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="update-container submit">
                                        <div class="submit-container update">
                                            <input type="submit" value="Update" class="submit-button">
                                        </div>
                                        <div class="submit-container cancel">
                                            <input type="button" value="Cancel" class="submit-button">
                                        </div>
                                    </div>
                                    </form>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
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
