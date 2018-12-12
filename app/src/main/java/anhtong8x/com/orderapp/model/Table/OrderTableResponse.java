package anhtong8x.com.orderapp.model.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderTableResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("note")
    @Expose
    private String note;

    public OrderTableResponse(Integer id, String name, Integer status, String note) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
