package modulo5.ddam.markmota.tk.space.model;

/**
 * Created by markmota on 8/18/16.
 */
public class ModelImg {
    public int id=0;
    public String image;
    public String description;
    public String date;
    public String title;
    public String creation;


    public ModelImg(int id, String image, String description, String date, String title, String creation) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.date = date;
        this.title = title;
        this.creation = creation;

    }
}
