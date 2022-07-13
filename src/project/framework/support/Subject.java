package project.framework.support;

import java.util.List;

public interface Subject<T> {
    default void attach(Observer observer) {
        getListOfObserversToNotify().add(observer);
    }

    List<Observer> getListOfObserversToNotify();

    void notifyObserver(T t);
}
