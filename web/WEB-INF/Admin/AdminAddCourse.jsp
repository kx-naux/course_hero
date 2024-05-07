<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Course</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link href="../admin_css/adminAddCourse.css" rel="stylesheet">
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
                        <h1>Add New Course</h1>
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
                <!----------  START OF ADDING TO CREATE NEW ITEMS ------------------->
                <form>
                <div class="add-items-container">
                    <div class="add-items"> 
                        <div class="update-container">
                            <label>Course Name</label>
                             <div class="input-container">
                                 <input type="text" name="name" placeholder="Course Name...">
                             </div>
                        </div>
                        <div class="update-container">
                            <label>Tools Used</label>
                            <div class="input-container">
                                <input type="text" name="name" placeholder="Education..." >
                            </div>
                        </div>
                        <div class="update-container">
                        <label>Description</label>
                        <div class="input-container description">
                            <textarea name="description" id="description" rows="4"></textarea>
                        </div>
                        </div>
                        <div class="update-container">
                            <label>Learning Objective</label>
                            <div class="input-container description">
                                <textarea name="description" id="description" rows="4"></textarea>
                            </div>
                        </div>
                        <div class="update-container-number">
                            <div class="update-container">
                            <label>Price</label>
                            <div class="input-container">
                                <input type="number" name="name" placeholder="Price..." >
                            </div>
                            </div>
                            <div class="update-container">
                                 <label>Course Length Hours</label>
                                 <div class="input-container">
                                     <input type="number"  name="name" value="1">
                                 </div>
                            </div>
                            <div class="update-container">
                                <label>Rate Weightage</label>
                                <div class="input-container">
                                    <input type="number"  name="name" placeholder="Rate Weightage" >
                                </div>
                            </div>
                            <div class="update-container" title="Minimum = 0 , Maximum = 1">
                                <label>Discount (%)</label>
                                <div class="input-container">
                                    <input type="number" name="name" placeholder="Discount" value="0" min="0" max="1" step="0.1" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="add-items select"> 
                        <div class="update-container">
                            <label>Publish Status</label>
                            <div class="input-container radio">
                                <div class="input-radio">
                                    <input type="radio" name="status" id="active" checked/>
                                    <label for="active">Active</label>
                                </div>
                                <div class="input-radio">
                                    <input type="radio" name="status" id="inactive">
                                    <label for="inactive">Inactive</label>
                                </div>
                            </div>
                        </div>
                        <div class="update-container image">
                            <label>Upload Image</label>
                            <div class="input-container">
                                <input type="file" name="image" id="image" accept="image/png, image/jpeg"/>
                            </div>
                        </div>
                        <div class="update-container select">
                            <label>Course Category</label>
                            <div class="input-container">
                                <select>
                                    <option value="-1">Course Category</option>
                                    <option value="edit">Programming</option>
                                    <option value="delete">Business</option>
                                </select>
                            </div>
                        </div>
                        <div class="update-container select">
                            <label>Course Level</label>
                            <div class="input-container">
                                <select>
                                    <option value="-1">Course Level</option>
                                    <option value="edit">Beginner</option>
                                    <option value="delete">Intermediate</option>
                                </select>
                            </div>
                        </div>
                        <div class="update-container submit">
                            <div class="submit-container update">
                                <input type="submit" value="Create" class="submit-button">
                            </div>
                            <div class="submit-container cancel">
                                <input type="reset" value="Reset" class="submit-button">
                            </div>
                        </div>
                    </div>
                </div>
                </form>
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
            <script type="text/javascript" src="../admin_js/adminAdd.js"></script>
    </body>
</html>
