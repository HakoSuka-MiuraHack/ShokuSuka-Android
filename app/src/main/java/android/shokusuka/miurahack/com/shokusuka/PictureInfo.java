package android.shokusuka.miurahack.com.shokusuka;

/**
 * Created by tourn on 2017/03/20.
 */

public class PictureInfo {
    private int id;
    private String UserName;
    private String PictureUrl;
    private String MainText;


    public PictureInfo(int id, String UserName, String PictureUrl, String MainText){
        this.id = id;
        this.UserName = UserName;
        this.PictureUrl = PictureUrl;
        this.MainText = MainText;
    }

/*-----------------getter---------------*/
    public int getId() {
        return id;
    }

    public String getMainText() {
        return MainText;
    }

    public String getPictureUrl() {
        return PictureUrl;
    }

    public String getUserName() {
        return UserName;
    }
/*---------------------setter---------------------*/
    public void setId(int id) {
        this.id = id;
    }

    public void setMainText(String mainText) {
        MainText = mainText;
    }

    public void setPictureUrl(String pictureUrl) {
        PictureUrl = pictureUrl;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
