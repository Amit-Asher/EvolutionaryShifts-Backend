package com.evo.springboot.app.DTO.Outgoing;

public class NewEmployeeResponseDTO {

    private String message;
    private boolean success;
    private String employeeId;
    private String tmpPassword;

    public NewEmployeeResponseDTO(String message, boolean success, String employeeId, String tmpPassword) {
        this.message = message;
        this.success = success;
        this.employeeId = employeeId;
        this.tmpPassword = tmpPassword;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getTmpPassword() {
        return tmpPassword;
    }

    public void setTmpPassword(String tmpPassword) {
        this.tmpPassword = tmpPassword;
    }
}
