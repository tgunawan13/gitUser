package gituser.com.gituser.Model;

import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("login")
    String item_name;

    @SerializedName("id")
    String item_ID;

    @SerializedName("node_id")
    String item_node_ID;

    @SerializedName("avatar_url")
    String item_avatar;

    @SerializedName("gravatar_id")
    String item_gravatar;

    @SerializedName("url")
    String item_base_url;

    @SerializedName("html_url")
    String item_html_url;

    @SerializedName("followers_url")
    String item_follower_url;

    @SerializedName("following_url")
    String user_following_url;

    @SerializedName("starred url")
    String item_starred_url;

    @SerializedName("subscriptions_url")
    String item_subs_url;

    @SerializedName("organizations_url")
    String item_org_url;

    @SerializedName("repos_url")
    String item_repo_url;

    @SerializedName("events_url")
    String item_events_url;

    @SerializedName("received_events_url")
    String item_recieve_events_url;

    @SerializedName("type")
    String item_type;

    @SerializedName("site_admin")
    Boolean item_site_admin;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_ID() {
        return item_ID;
    }

    public void setItem_ID(String item_ID) {
        this.item_ID = item_ID;
    }

    public String getItem_node_ID() {
        return item_node_ID;
    }

    public void setItem_node_ID(String item_node_ID) {
        this.item_node_ID = item_node_ID;
    }

    public String getItem_avatar() {
        return item_avatar;
    }

    public void setItem_avatar(String item_avatar) {
        this.item_avatar = item_avatar;
    }

    public String getItem_gravatar() {
        return item_gravatar;
    }

    public void setItem_gravatar(String item_gravatar) {
        this.item_gravatar = item_gravatar;
    }

    public String getItem_base_url() {
        return item_base_url;
    }

    public void setItem_base_url(String item_base_url) {
        this.item_base_url = item_base_url;
    }

    public String getItem_html_url() {
        return item_html_url;
    }

    public void setItem_html_url(String item_html_url) {
        this.item_html_url = item_html_url;
    }

    public String getItem_follower_url() {
        return item_follower_url;
    }

    public void setItem_follower_url(String item_follower_url) {
        this.item_follower_url = item_follower_url;
    }

    public String getUser_following_url() {
        return user_following_url;
    }

    public void setUser_following_url(String user_following_url) {
        this.user_following_url = user_following_url;
    }

    public String getItem_starred_url() {
        return item_starred_url;
    }

    public void setItem_starred_url(String item_starred_url) {
        this.item_starred_url = item_starred_url;
    }

    public String getItem_subs_url() {
        return item_subs_url;
    }

    public void setItem_subs_url(String item_subs_url) {
        this.item_subs_url = item_subs_url;
    }

    public String getItem_org_url() {
        return item_org_url;
    }

    public void setItem_org_url(String item_org_url) {
        this.item_org_url = item_org_url;
    }

    public String getItem_repo_url() {
        return item_repo_url;
    }

    public void setItem_repo_url(String item_repo_url) {
        this.item_repo_url = item_repo_url;
    }

    public String getItem_events_url() {
        return item_events_url;
    }

    public void setItem_events_url(String item_events_url) {
        this.item_events_url = item_events_url;
    }

    public String getItem_recieve_events_url() {
        return item_recieve_events_url;
    }

    public void setItem_recieve_events_url(String item_recieve_events_url) {
        this.item_recieve_events_url = item_recieve_events_url;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public Boolean getItem_site_admin() {
        return item_site_admin;
    }

    public void setItem_site_admin(Boolean item_site_admin) {
        this.item_site_admin = item_site_admin;
    }

    public float getItem_score() {
        return item_score;
    }

    public void setItem_score(float item_score) {
        this.item_score = item_score;
    }

    @SerializedName("score")
    float item_score;

}
