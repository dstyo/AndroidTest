package android.dstyo.com.androidtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Representing Car Model Class.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public class Car implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("brand")
    @Expose
    private String brand;

    @SerializedName("model")
    @Expose
    private String model;

    @SerializedName("license_plat")
    @Expose
    private String license_plat;

    @SerializedName("fare")
    @Expose
    private double farePerDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicense_plat() {
        return license_plat;
    }

    public void setLicense_plat(String license_plat) {
        this.license_plat = license_plat;
    }

    public double getFarePerDay() {
        return farePerDay;
    }

    public void setFarePerDay(double farePerDay) {
        this.farePerDay = farePerDay;
    }
}
