package android.dstyo.com.androidtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Representing Rental Model Class.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public class Order implements Serializable {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("start_date")
    @Expose
    public String start_date;

    @SerializedName("end_date")
    @Expose
    public String end_date;

    @SerializedName("car_id")
    @Expose
    public int car_id;

    @SerializedName("user_id")
    @Expose
    public int user_id;

    @SerializedName("car")
    @Expose
    public Car car;

    @SerializedName("user")
    @Expose
    public User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
