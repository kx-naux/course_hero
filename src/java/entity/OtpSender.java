/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Date;

/**
 *
 * @author User
 */
public class OtpSender {
    private String otp;
    private Date expiryDate;
    
    public OtpSender(){
        otp = generateNumericOTP(6);
        expiryDate = new Date();
    }
    
    public OtpSender(String otp, Date expiryDate){
        this.otp = otp;
        this.expiryDate = expiryDate;
    }
    
    public boolean send(String email) throws IOException, InterruptedException{
        // Get the directory of the current class file
        String classFilePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File classFile = new File(classFilePath);
        String classDirectory = classFile.getParent();
        classFile = new File(classDirectory);
        String classTwoLevelUpDirectory = classFile.getParent();

        // Concatenate the script file name to the class directory
        String scriptPath = classTwoLevelUpDirectory + File.separator + "pythonSendOTP\\otp.py";
        scriptPath = scriptPath.replace("%20", " ");
        
        ProcessBuilder pb = new ProcessBuilder("python ", scriptPath, otp , email);
        pb.redirectErrorStream(true);
        Process process = pb.start();
            
        // Capture the script output
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        reader.close();
        // Check if the email was sent successfully
        String testOutput = output.toString();
        if(output.toString().contains("successfully")){
            Date currentDate = new Date();
            expiryDate.setTime(currentDate.getTime() + (5*60*1000));
            return true;
        }else{
            return false;
        }
        // Check if the email was sent successfully
        //int exitCode = process.waitFor();
        //if (exitCode == 0) {
            //means success
        //    return true;
    }
    
    private String generateNumericOTP(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
    
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
   
