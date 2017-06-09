package frame.project.ray.projectframe.retrofit;

import java.io.Serializable;

/**
 * Created by ray on 17-5-10
 * Description:
 */
public class BaseModel<T> implements Serializable {
    public boolean isSuccess;
    public String msg;
    public T data;


    public boolean success(){
        return isSuccess;
    }
}
