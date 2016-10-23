package android.dstyo.com.androidtest.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * Representing Factory class to generate Model Object.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class ModelFactory {

    /**
     * Return an model object.
     *
     * @param clazz     Model class
     * @param apiObject Api String Object
     * @param <T>       Generic object that implement Serializable
     * @return Model Object
     */
    public static <T extends Serializable> T create(Class<T> clazz, String apiObject) {
        if (null == apiObject) {
            try {
                return clazz.newInstance();
            }
            catch (IllegalAccessException ex) {
                return null;
            }
            catch (InstantiationException ex) {
                return null;
            }
        }

        Gson sGsonExpose = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation().create();
        return sGsonExpose.fromJson(apiObject, clazz);
    }
}
