package go.app.newe.utils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider {

    public Scheduler io() {
        return Schedulers.io();
    }

    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
