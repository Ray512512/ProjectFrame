package frame.project.ray.projectframe.rxjava;


import frame.project.ray.projectframe.retrofit.BaseModel;
import frame.project.ray.projectframe.retrofit.ServerException;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * Created by Jam on 16-6-12
 * Description: Rx 一些巧妙的处理
 */
public class RxHelper {
    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */

    public static <T> FlowableTransformer<BaseModel<T>, T> handleResult() {
        return upstream -> upstream.flatMap(new Function<BaseModel<T>, Flowable<T>>() {
            @Override
            public Flowable<T> apply(@NonNull BaseModel<T> result) throws Exception {
                if (result.success()) {
                    return createData(result.data);
                } else {
                    return Flowable.error(new ServerException(result.msg));
                }
            }
        });

    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Flowable<T> createData(final T data) {

        return Flowable.create(subscriber -> {
            try {
                subscriber.onNext(data);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }, BackpressureStrategy.BUFFER);
    }
}
