package evahsu.simplemovieapi.web;

/**
 * Created by EvaHsu on 2017/12/22.
 */

public interface ObserverOnNextListener<T> {
    void onNext(T t);
    void onError();
}
