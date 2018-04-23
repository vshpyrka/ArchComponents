package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultStatus {

    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_FAILURE = -1;

    public int status;

    public String msg;

    public static String getResultStatus(int status) {
        return getResultStatus(status, null);
    }

    public static String getResultStatus(int status, String msg) {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.status = status;
        resultStatus.msg = msg;
        try {
            return new ObjectMapper().writeValueAsString(resultStatus);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
