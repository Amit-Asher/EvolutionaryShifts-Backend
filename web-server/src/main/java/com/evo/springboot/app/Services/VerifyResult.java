package com.evo.springboot.app.Services;

import com.auth0.jwt.interfaces.DecodedJWT;

public class VerifyResult {
    private boolean success;
    private DecodedJWT jwt;

    public VerifyResult(boolean success, DecodedJWT jwt) {
        this.success = success;
        this.jwt = jwt;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DecodedJWT getJwt() {
        return jwt;
    }

    public void setJwt(DecodedJWT jwt) {
        this.jwt = jwt;
    }
}
