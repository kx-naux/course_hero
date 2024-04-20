/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class SocialMediaLink {
    private String socialMediaLinkID;
    private String authorID;
    private String socialMediaName;
    private String destLink;

    // Constructor - empty
    public SocialMediaLink() {
    }

    // Constructor - fully parameterized
    public SocialMediaLink(String socialMediaLinkID, Author author, String socialMediaName, String destLink) {
        this.socialMediaLinkID = socialMediaLinkID;
        this.authorID = author.getAuthorID();
        this.socialMediaName = socialMediaName;
        this.destLink = destLink;
    }

    // Getters
    public String getSocialMediaLinkID() {
        return socialMediaLinkID;
    }

    public String getAuthorID() {
        return authorID;
    }
    
    public String getSocialMediaName(){
        return socialMediaName;
    }

    public String getDestLink() {
        return destLink;
    }

    // Setters
    public void setSocialMediaLinkID(String socialMediaLinkID) {
        this.socialMediaLinkID = socialMediaLinkID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }
    
    public void setSocialMediaName(String socialMediaName){
        this.socialMediaName = socialMediaName;
    }

    public void setDestLink(String destLink) {
        this.destLink = destLink;
    }
}