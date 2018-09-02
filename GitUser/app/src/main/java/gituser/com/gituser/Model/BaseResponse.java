package gituser.com.gituser.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse {

    @SerializedName("total_count")
    int total_response;

    @SerializedName("incomplete_results")
    Boolean status_response;

    @SerializedName("items")
    List<Items> items_response ;

    public int getTotal_response() {
        return total_response;
    }

    public Boolean getStatus_response() {
        return status_response;
    }


    public List<Items> getItems_response() {

        return items_response;
    }


}
