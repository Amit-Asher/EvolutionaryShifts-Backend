package com.evo.springboot.app.DTO.Outgoing;

public class NewPasswordDTO {
    private String message;
    private boolean success;
    private String newPassword;

    public NewPasswordDTO(String message, boolean success, String newPassword){
        this.message = message;
        this.success = success;
        this.newPassword = newPassword;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
