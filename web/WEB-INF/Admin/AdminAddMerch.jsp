<%@page import="JPAEntity.MerchCategory"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Merchandise</title>
        <link rel="icon" type="image/ico" href="../ico/Logo.ico">
        <!--StyleSheet-->
        <link href="../admin_css/adminAddMerch.css" rel="stylesheet">
        <jsp:useBean id="webpath" class="module.WebPath" scope="application" />
        <% List<MerchCategory> merchandiseCatList = (List<MerchCategory>) request.getAttribute("merchandiseCatList"); %>
    </head>
    <body> 
        <div class="flex-container">
            <!-------------------- START OF GLOBAL SIDEBAR ------------------->
            <%@ include file="./Components/global-sidebar.jsp" %>
            <!-------------------- END OF GLOBAL SIDEBAR ------------------->
            <div class="main-content">
                <div class="main-top">
                    <div class="main-top-left">
                        <h1>Add New Merchandise</h1>
                    </div>                    
                    <div class="top">
                        <!-- include dark theme toggler -->
                        <%@ include file="./Components/dark-theme-toggler.jsp" %>
                        <!<!-- include profile  -->
                        <%@ include file="./Components/global-profile.jsp" %>
                    </div>
                </div>
                <div class="horizontal-line"></div>
                <!----------  START OF ADDING TO CREATE NEW ITEMS ------------------->
                <form method="post" action="add-merch" id="addMerchForm">
                <div class="add-items-container">
                    <input type="hidden" name="merchID" value=""/>
                    <div class="add-items"> 
                        <div class="update-container">
                            <label>Merch Name</label>
                             <div class="input-container">
                                 <input type="text" name="productName" id="productName" required maxlength="50" placeholder="Merch Name">
                             </div>
                        </div>
                        <div class="update-container">
                        <label>Description</label>
                        <div class="input-container description">
                            <textarea name="productDescription" id="productDescription" required rows="4" maxlength="1000" placeholder="Description"></textarea>
                        </div>
                        </div>
                        <div class="update-container-number">
                            <div class="update-container">
                            <label>Price</label>
                            <div class="input-container">
                                <input type="number" name="price" id="price" required placeholder="Price" >
                            </div>
                            </div>
                            <div class="update-container">
                                <label>Stock</label>
                                <div class="input-container">
                                    <input type="stockBalance"  name="stockBalance" required placeholder="Stock" >
                                </div>
                            </div>
                            <div class="update-container">
                                <label>Rate Weightage</label>
                                <div class="input-container">
                                    <input type="number"  name="rateWeightage" id="rateWeightage" placeholder="Rate Weightage" required >
                                </div>
                            </div>
                            <div class="update-container" title="Minimum = 0 , Maximum = 1">
                                <label>Discount (%)</label>
                                <div class="input-container">
                                    <input type="number" name="discount" id="discount" placeholder="Discount" min="0" max="1" step="0.1" required >
                                </div>
                            </div>
                        </div>
                        <div class="title">
                            <h2>Merchandise Size</h2>
                            <div class="horizontal-line"></div>
                        </div>
                        <div class="update-container-number">
                            <div class="update-container">
                                <label>Dimension Height (CM)</label>
                                <div class="input-container">
                                    <input type="number"  name="dimensionHeight" id="dimensionHeight" required placeholder="Dimension Height (CM)" >
                                </div>
                            </div>
                            <div class="update-container">
                                <label>Dimension Width (CM)</label>
                                <div class="input-container">
                                    <input type="number"  name="dimensionWidth" id="dimensionWidth" required placeholder="Dimension Width (CM)" >
                                </div>
                            </div>
                            <div class="update-container">
                                <label>Dimension Length (CM)</label>
                                <div class="input-container">
                                    <input type="number"  name="dimensionLength" id="dimensionLength" required placeholder="Dimension Length (CM))" >
                                </div>
                            </div>
                            <div class="update-container">
                                <label>Weight (KG)</label>
                                <div class="input-container">
                                    <input type="number"  name="weight" id="weight" required placeholder="Weight (KG)" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="add-items select"> 
                        <div class="update-container">  
                            <label>Publish Status</label>
                            <div class="input-container radio">
                                <div class="input-radio">
                                    <input type="radio" name="status" id="active" value="active" required checked/>
                                    <label for="active">Active</label>
                                </div>
                                <div class="input-radio">
                                    <input type="radio" name="status" id="inactive" value="inactive" required>
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
                            <label>Merch Category</label>
                            <div class="input-container">
                                <select id="merchCategory" name="merchCategory">
<!--                                    <option value="">Merch Category</option>-->
                                    <%
                                        // Iterate through each merchandise in the original list
                                        for (int i = 0; i < merchandiseCatList.size(); i++) {
                                            String merchCatName = merchandiseCatList.get(i).getCategoryName();
                                    %>
                                    <option required value="<%= merchandiseCatList.get(i).getMerchcatId() %>"><%= merchCatName %></option>
                                    <%}%>
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
