package com.otocar.otocar.model;


public class Mail {

    private String mail;
    private String title;
    private String content;
    private String mailSender;
    private boolean isHtmlContent;

    public Mail(String mail, String title, String content, String mailSender) {
        this.mail = mail;
        this.title = title;
        this.content = content;
        this.mailSender = mailSender;
        this.isHtmlContent = false;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getMailSender() {
        return mailSender;
    }

    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }

    public void setContent(String content) {

        this.content =  content;
    }

    public boolean isHtmlContent() {
        return isHtmlContent;
    }
}
