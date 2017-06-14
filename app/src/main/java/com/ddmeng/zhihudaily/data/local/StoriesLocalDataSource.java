package com.ddmeng.zhihudaily.data.local;

import com.ddmeng.zhihudaily.data.StoriesDataSource;
import com.ddmeng.zhihudaily.data.models.db.Story;
import com.ddmeng.zhihudaily.data.models.db.Story_Table;
import com.ddmeng.zhihudaily.data.models.display.DisplayStories;
import com.ddmeng.zhihudaily.utils.LogUtils;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class StoriesLocalDataSource implements StoriesDataSource {
    public static final String TAG = "LocalDataSource";

    @Inject
    public StoriesLocalDataSource() {
    }

    @Override
    public Observable<DisplayStories> getNews() {
        return Observable.fromCallable(new Callable<DisplayStories>() {
            @Override
            public DisplayStories call() throws Exception {
                DisplayStories displayStories = new DisplayStories();
                List<Story> topStories = SQLite.select().from(Story.class)
                        .where(Story_Table.isTopStory.eq(true))
                        .queryList();
                List<Story> listStories = SQLite.select().from(Story.class)
                        .where(Story_Table.isTopStory.eq(false))
                        .queryList();
                displayStories.setTopStories(topStories);
                displayStories.setListStories(listStories);
                LogUtils.i(TAG, "query from local: " + displayStories);
                return displayStories;
            }
        });
    }

    @Override
    public void saveNews(DisplayStories displayStories) {
        LogUtils.i(TAG, "save: " + displayStories);
        List<Story> listStories = displayStories.getListStories();
        List<Story> topStories = displayStories.getTopStories();
        FastStoreModelTransaction
                .saveBuilder(FlowManager.getModelAdapter(Story.class))
                .addAll(listStories)
                .addAll(topStories)
                .build()
                .execute(FlowManager.getDatabase(ZhihuDatabase.class).getWritableDatabase());
    }
}
