<jsp:useBean id="userData" class="JPAEntity.Users" scope="session" /> 
<%@page import="java.util.Base64"%>                
<div class="profile">
                        <div class="info">
                            <p>Hey, <b>${userData.accountId.username}</b></p>
                            <small>${userData.displayName   }</small>
                        </div>
                        <div class="profile-photo">
                            <% String base64Image = "";
                                if (userData.getImgData() != null) {
                                    base64Image = Base64.getEncoder().encodeToString((byte[]) userData.getImgData());
                                                        } %> 
                            <img src="data:image/jpeg;base64,<%= base64Image %>" onerror="this.src='../img/user/default.png';" alt="alt"/>
                        </div> 
                    </div>