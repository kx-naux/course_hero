package controller;

import JPAEntity.Accounts;
import JPAEntity.BillingAddress;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import entity.LoginFormData;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet(name = "Sign Up Page", urlPatterns = {"/sign-up"})
public class SignUpPage extends HttpServlet {
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Forward the request to login.jsp
        request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        HttpSession session = request.getSession();
        String formType = request.getParameter("formType");
        String userEdit = request.getParameter("userWantToEdit");
        String otp = null;
        
        //form 1 submission proccess
        if(formType.equals("validateData")){
            //only validate data and save data to request
            validateData(request,response);
            return;
        //form 2 submission process    
        }else if(formType.equals("confirmSubmit")){
            if(userEdit != null && userEdit.equals("I want edit")){
                request.setAttribute("loginFormData",getFormData(request, response));
                request.setAttribute("pageNumber", "1");
                request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
            }else{
                //send OTP
                LoginFormData formData = getFormData(request, response);
                otp = generateNumericOTP(6);
                Date otpExpiryDate = new Date();
                processSendOTP(otp,formData,otpExpiryDate,request,response);
                
                session.setAttribute("signUpFormDetails",formData);
                request.setAttribute("pageNumber", "5");
                request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
                
                
            }
        //form 3 submision process
        }else if(formType.equals("OTPForm")){
            Date otpExpiryDate = (Date) session.getAttribute("otpExpiryDate");
            otp = (String) session.getAttribute("LoginOtp");
            
            //check OTP expiry onot
            boolean isCurrentOTPExpiry = false;
            if(otpExpiryDate.compareTo(new Date())<0){
                isCurrentOTPExpiry = true;
            }
            
            if(checkIsOTPMatched(otp,request)){
                if(isCurrentOTPExpiry){
                    request.setAttribute("loginFormData",getFormData(request,response));
                    request.setAttribute("pageNumber","5");
                    request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
                }else{
                    processDataForDatabaseAndSaveIt(request,response);   
                }
            }else{
                request.setAttribute("pageNumber","5");
                request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
            }
        }
                          
        // forward the request to SignUp.jsp complete section
        //request.setAttribute("pageNumber", "4");
        response.sendRedirect("home");
    }
    
    private void processSendOTP(String otp, LoginFormData formData,Date otpExpiryDate, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            if(sendOTP(otp,formData.getEmail())){
                Date otpSendDate = new Date();
                otpExpiryDate.setTime(otpSendDate.getTime() + 5 * 60 * 1000);
            }else{
                ErrorPage.forwardToServerErrorPage(request,response,"Server fails to send OTP. Please Try Again Later");
            }
            HttpSession session = request.getSession();
            session.setAttribute("LoginOtp",otp);
            session.setAttribute("otpExpiryDate",otpExpiryDate);
        }catch(Exception ex){
            ErrorPage.forwardToServerErrorPage(request,response,"Server fails to send OTP. Please Try Again Later");
        }
        
    }
    
    private void processDataForDatabaseAndSaveIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                //create new accounts objct into databases
        HttpSession session = request.getSession();
        LoginFormData formData = (LoginFormData) session.getAttribute("signUpFormDetails");
        String password = (String) session.getAttribute("SignUpPassword");
        session.removeAttribute("SignUpPassword");
        Accounts newAcc = new Accounts();
        try{
            newAcc = new Accounts(formData.getUsername(),formData.getEmail());
            newAcc.setHashedPassword(password);
            
        }catch(NoSuchAlgorithmException ex){
            ErrorPage.forwardToServerErrorPage(request,response,"Server Passoword Hashing Error. Please Try Again Later");
        }
        
        //create new billing address obj
        BillingAddress newBillAddress = new BillingAddress(formData.getAddressLine1(),formData.getCity(),formData.getState(),formData.getPostalCode(),formData.getCountry());
        newBillAddress.setLine2(formData.getAddressLine2());
        
        // create new user object
        Users newUser = new Users(formData.getDisplayName(),formData.getDob(),"Customer",new Date(),formData.getGender(),newAcc,newBillAddress);
        
        //set id for each of the entity so that they can be stored in databases
            //get all of the current counts
        TablesRecordCounter accountCounter = em.find(TablesRecordCounter.class,"ACCOUNTS");
        TablesRecordCounter billAddCounter = em.find(TablesRecordCounter.class,"BILLING_ADDRESS");
        TablesRecordCounter userCounter = em.find(TablesRecordCounter.class,"USERS");
            //set id
        newAcc.setAccountId(accountCounter.getCounter() + 1);
        newBillAddress.setAddressId(billAddCounter.getCounter()+1);
        newUser.setUserId(userCounter.getCounter()+1);
        
            //add one to each counter
        accountCounter.counterIncrementByOne();
        billAddCounter.counterIncrementByOne();
        userCounter.counterIncrementByOne();
        
        List<TablesRecordCounter> recordsCounterList = new ArrayList<TablesRecordCounter>();
        recordsCounterList.add(accountCounter);
        recordsCounterList.add(billAddCounter);
        recordsCounterList.add(userCounter);
        
        //persist the records
        saveDataToDatabases(request, response, newAcc,newBillAddress, newUser,recordsCounterList);
    }
    
    private boolean checkIsOTPMatched(String otp,HttpServletRequest request ){
        String otp1 = request.getParameter("otp1");
        String otp2 = request.getParameter("otp2");
        String otp3 = request.getParameter("otp3");
        String otp4 = request.getParameter("otp4");
        String otp5 = request.getParameter("otp5");
        String otp6 = request.getParameter("otp6");

        // Combine the values into one string
        String otpInput = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;
        
        if(otp.equals(otpInput)){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean sendOTP(String otp,String email) throws IOException, InterruptedException{
        // Get the directory of the current class file
        String classFilePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File classFile = new File(classFilePath);
        String classDirectory = classFile.getParent();
        classFile = new File(classDirectory);
        String classTwoLevelUpDirectory = classFile.getParent();

        // Concatenate the script file name to the class directory
        String scriptPath = classTwoLevelUpDirectory + File.separator + "pythonSendOTP\\otp.py";
        scriptPath = scriptPath.replace("%20", " ");
        
        for(int i = 0; i<3;i++){
            ProcessBuilder pb = new ProcessBuilder("python ", scriptPath, otp , email);
            pb.redirectErrorStream(true);
            Process process = pb.start();
        
        // Check if the email was sent successfully
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                //means success
                return true;
            }
        }
        return false;
    }
    
    public static String generateNumericOTP(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
    
    private LoginFormData getFormData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

        // get all of the data first
        LoginFormData formData = new LoginFormData();
        
        formData.setEmail(request.getParameter("email"));
        formData.setUsername(request.getParameter("username"));
        formData.setDisplayName(request.getParameter("name"));
        formData.setGender(request.getParameter("gender"));
        formData.setAddressLine1(request.getParameter("address1"));
        formData.setAddressLine2(request.getParameter("address2"));
        formData.setCity(request.getParameter("city"));
        formData.setPostalCode(request.getParameter("postalCode"));
        formData.setState(request.getParameter("state"));
        formData.setCountry(request.getParameter("country"));
        String password = request.getParameter("password");
        
        if(password!=null){
            HttpSession session = request.getSession();
            session.setAttribute("SignUpPassword",password);
        }
        
        
        String dobStr = request.getParameter("dob");
        formData.setDobStr(dobStr);
        
        //get Date
        Date dob = null;
        try {
            dob = dateFormat.parse(dobStr);
        } catch (java.text.ParseException e) {
            ErrorPage.forwardToServerErrorPage(request,response,"Server Error - Pass Date data type error. Please Try Again Later");           
        }
        
        formData.setDob(dob);
        
        //set empty value for address to prevent store null value in databases
        if(formData.getAddressLine1() == null){
            formData.setAddressLine1("");
        }
        if(formData.getAddressLine2() == null){
            formData.setAddressLine2("");
        }
        if(formData.getCity() == null){
            formData.setCity("");
        }
        if(formData.getPostalCode() == null){
            formData.setPostalCode("");
        }
        if(formData.getState() == null){
            formData.setState("");
        }
        if(formData.getCountry() == null){
             formData.setCountry("");
        }
        
        return formData;
    }
    
    private void validateData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        

        // get all of the data first
        LoginFormData formData = getFormData( request,  response);
        
       
        
        request.setAttribute("loginFormData",formData);
        
        boolean duplicatedUsername = checkForDuplicatedUsername(formData.getUsername());
        boolean duplicatedEmail = checkForDuplicatedEmail(formData.getEmail());
        if(duplicatedUsername && duplicatedEmail){
            request.setAttribute("loginFormData",formData);
            forwardAndShowErrorOnSignUpPage(request,response,"1","email&username","Email and Username already exists, please use another.");
        }else if(duplicatedEmail){
            request.setAttribute("loginFormData",formData);
            forwardAndShowErrorOnSignUpPage(request,response,"1","email","Email already exists, please use another.");
        }else if(duplicatedUsername){
            request.setAttribute("loginFormData",formData);
            forwardAndShowErrorOnSignUpPage(request,response,"1","username","Username already exist, please use another username");
        }
        
        
        request.setAttribute("pageNumber", "4");
        request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
    }
    
    private boolean checkForDuplicatedEmail(String email){
        Query query = em.createNamedQuery("Accounts.findByEmail").setParameter("email", email);
        List<Accounts> record = (List<Accounts>)query.getResultList();
        if(record.isEmpty()){ //means is not duplicated
            return false;
        }else{
            return true;
        }
    }
    
    private boolean checkForDuplicatedUsername(String username){
        Query query = em.createNamedQuery("Accounts.findByUsername").setParameter("username", username);
        List<Accounts> record = (List<Accounts>)query.getResultList();
        if(record.isEmpty()){ //means is not duplicated
            return false;
        }else{
            return true;
        }
    }
        
    
    private void forwardAndShowErrorOnSignUpPage(HttpServletRequest request, HttpServletResponse response,String pageNumber,String errField, String errMsg)
        throws ServletException, IOException{
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("errField",errField);
        request.setAttribute("errMsg", errMsg);
        
        request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
    }
    
    private void saveDataToDatabases(HttpServletRequest request, HttpServletResponse response, Accounts newAcc, BillingAddress newBillAddress, Users newUser, List<TablesRecordCounter> tablesRecordCounterList) throws ServletException, IOException{      
        try{
            utx.begin();
            em.persist(newAcc);
            em.persist(newBillAddress);
            em.persist(newUser);
            for(TablesRecordCounter data: tablesRecordCounterList){
                em.merge(data);
            }
            utx.commit();
        }catch(Exception ex){
            try{
            if (utx.getStatus() == Status.STATUS_ACTIVE) {
                try {
                    utx.rollback();
                }catch (SystemException ex2) {
                    //server error page
                    ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
                }
            }
            }catch (SystemException ex2){
                ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
            }
            ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
        }
    }
    
}
