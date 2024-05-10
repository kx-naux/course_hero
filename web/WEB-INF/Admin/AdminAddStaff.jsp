<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Staff</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link href="../admin_css/adminAddStaff.css" rel="stylesheet">
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
                        <h1>Add New Staff</h1>
                    </div>                    
                    <div class="top">
                        <!-- include dark theme toggler -->
                        <%@ include file="./Components/dark-theme-toggler.jsp" %>
                        <%@ include file="./Components/global-profile.jsp" %>
                    </div>
                </div>
                <div class="horizontal-line"></div>
                <!----------  START OF ADDING TO CREATE NEW ITEMS ------------------->
                <form method="post">
                    <div class="add-items-container">
                        <div class="add-items">
                            <div class="update-container">
                                <label>Staff ID</label>
                                <div class="input-container">
                                    <%
                                        String username = (String) request.getAttribute("username");
                                        if (username == null) {
                                            username = "";
                                        }
                                    %>
                                    <input type="text" name="username" id="username" maxlength="25" required placeholder="Staff ID" value="${username}" />
                                </div>
                            </div>
                            <div class="update-container">
                                <label>Staff Name</label>
                                <div class="input-container">
                                    <%
                                        String name = (String) request.getAttribute("name");
                                        if (name == null) {
                                            name = "";
                                        }
                                    %>
                                    <input type="text" name="name" id="name" maxlength="30" required placeholder="Staff Name" value="${name}">
                                </div>
                            </div>
                            <!--                        <div class="update-container">
                                                        <label>Role</label>
                                                        <div class="input-container">
                                                            <input type="text" name="name" placeholder="Role">
                                                        </div>
                                                    </div>-->
                            <div class="update-container">
                                <label>Email</label>
                                <div class="input-container">
                                    <%
                                        String email = (String) request.getAttribute("email");
                                        if (email == null) {
                                            email = "";
                                        }
                                    %>
                                    <input type="email" name="email" maxLength="50" required placeholder="Email" value="${email}">
                                </div>
                            </div>
                            <!--                        <div class="update-container">
                                                        <label>Password</label>
                                                        <div class="input-container">
                                                            <input type="password" name="name" placeholder="Password">
                                                        </div>
                                                    </div>-->
                            <!--                        <div class="title">
                                                        <h2>Address</h2>
                                                        <div class="horizontal-line"></div>
                                                    </div>-->
                            <!--                        <div class="update-container">
                                                        <label>Address Line 1</label>
                                                        <div class="input-container">
                                                            <input type="text" name="name" placeholder="Address Line 1" >
                                                        </div>
                                                    </div>
                                                    <div class="update-container">
                                                        <label>Address Line 2</label>
                                                        <div class="input-container">
                                                            <input type="text" name="name" placeholder="Address Line 2" >
                                                        </div>
                                                    </div>
                                                    <div class="update-container">
                                                        <label>City</label>
                                                        <div class="input-container">
                                                            <input type="text" name="name" placeholder="City" >
                                                        </div>
                                                    </div>
                                                    <div class="update-container">
                                                        <label>State</label>
                                                        <div class="input-container">
                                                            <input type="text" name="name" placeholder="State" >
                                                        </div>
                                                    </div>
                                                    <div class="update-container">
                                                        <label>Country</label>
                                                        <div class="input-container">
                                                            <input type="text" name="name" placeholder="Country" >
                                                        </div>
                                                    </div>
                                                    <div class="update-container">
                                                        <label>Address Line 2</label>
                                                        <div class="input-container">
                                                            <input type="text" name="name" placeholder="Address Line 2" >
                                                        </div>
                                                    </div>-->
                            <div class="update-container submit">
                                <div class="submit-container update">
                                    <input type="submit" value="Create" class="submit-button">
                                </div>
                                <div class="submit-container cancel">
                                    <input type="reset" value="Reset" class="submit-button">
                                </div>
                            </div>
                        </div>
                        <!--                    <div class="add-items select"> 
                                                <div class="update-container image">
                                                    <label>Upload Image</label>
                                                    <div class="input-container">
                                                        <input type="file" name="image" id="image" accept="image/png, image/jpeg"/>
                                                    </div>
                                                </div>
                                                <div class="update-container select">
                                                    <label>Gender</label>
                                                    <div class="input-container">
                                                        <select>
                                                            <option value="-1">Gender</option>
                                                            <option value="edit">Male</option>
                                                            <option value="delete">Female</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="update-container select">
                                                    <label>Birth Date</label>
                                                    <div class="input-container">
                                                        <input type="date" name="name">
                                                    </div>
                                                </div>
                                                <div class="update-container submit">
                                                    <div class="submit-container update">
                                                        <input type="submit" value="Create" class="submit-button">
                                                    </div>
                                                    <div class="submit-container cancel">
                                                        <input type="reset" value="Reset" class="submit-button">
                                                    </div>
                                                </div>-->
                    </div>
                </form>
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
    <script type="text/javascript" src="../admin_js/adminAdd.js"></script>
</body>
</html>
