package anhtong8x.com.orderapp.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName("status")
    @Expose
    Boolean status;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("errorCode")
    @Expose
    int errorCode;
    @SerializedName("data")
    @Expose
    T data;
    @SerializedName("totalRecord")
    @Expose
    int totalRecord;
    @SerializedName("limit")
    @Expose
    int limit;
    @SerializedName("page")
    @Expose
    int page;
    
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
