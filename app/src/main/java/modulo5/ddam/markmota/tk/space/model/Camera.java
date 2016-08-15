
package modulo5.ddam.markmota.tk.space.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Camera {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("rover_id")
    private Integer roverId;
    @SerializedName("full_name")
    private String fullName;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The roverId
     */
    public Integer getRoverId() {
        return roverId;
    }

    /**
     * 
     * @param roverId
     *     The rover_id
     */
    public void setRoverId(Integer roverId) {
        this.roverId = roverId;
    }

    /**
     * 
     * @return
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName
     *     The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
