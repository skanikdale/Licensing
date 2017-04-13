
package com.ptc.licensing.core;

@SuppressWarnings("serial")
public class LicensingException extends Exception {

    private int _errorCode;

    public LicensingException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this._errorCode = errorCode;
    }

    public LicensingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LicensingException(String message) {
        super(message);
    }

    public int getErrorCode() {
        return _errorCode;
    }
}
