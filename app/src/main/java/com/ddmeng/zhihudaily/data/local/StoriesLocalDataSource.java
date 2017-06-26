package com.ddmeng.zhihudaily.data.local;

import com.ddmeng.zhihudaily.data.StoriesDataSource;
import com.ddmeng.zhihudaily.data.models.db.Story;
import com.ddmeng.zhihudaily.data.models.db.StoryDetail;
import com.ddmeng.zhihudaily.data.models.db.StoryDetail_Table;
import com.ddmeng.zhihudaily.data.models.db.Story_Table;
import com.ddmeng.zhihudaily.data.models.display.DisplayStories;
import com.ddmeng.zhihudaily.utils.DateUtils;
import com.ddmeng.zhihudaily.utils.LogUtils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

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
    public Observable<DisplayStories> getLatestNews() {
        String currentDataString = DateUtils.getCurrentDataString();
        return getNewsForDate(currentDataString);
    }

    @Override
    public Observable<DisplayStories> getNewsForDate(final String date) {
        LogUtils.i(TAG, "getNewsForDate: " + date);
        return Observable.fromCallable(new Callable<DisplayStories>() {
            @Override
            public DisplayStories call() throws Exception {
                DisplayStories displayStories = new DisplayStories();
                displayStories.setDate(date);
                List<Story> topStories = SQLite.select().from(Story.class)
                        .where(Story_Table.date.eq(date), Story_Table.isTopStory.eq(true))
                        .queryList();
                List<Story> listStories = SQLite.select().from(Story.class)
                        .where(Story_Table.date.eq(date), Story_Table.isTopStory.eq(false))
                        .queryList();
                displayStories.setTopStories(topStories);
                displayStories.setListStories(listStories);
                LogUtils.i(TAG, "query from local for date: " + date + ", get: " + displayStories);
                return displayStories;
            }
        });
    }

    @Override
    public Observable<StoryDetail> getNewsDetail(final String id) {
        return Observable.fromCallable(new Callable<StoryDetail>() {
            @Override
            public StoryDetail call() throws Exception {
                StoryDetail storyDetail = SQLite.select().from(StoryDetail.class).where(StoryDetail_Table.id.eq(id)).querySingle();
                LogUtils.i(TAG, "query from local " + (storyDetail != null ? storyDetail.getId() : "but not found: " + id));
                return storyDetail;
            }
        });
    }

    @Override
    public void saveNews(DisplayStories displayStories) {
        LogUtils.i(TAG, "save to local: " + displayStories);
        List<Story> listStories = displayStories.getListStories();
        List<Story> topStories = displayStories.getTopStories();
        FastStoreModelTransaction
                .saveBuilder(FlowManager.getModelAdapter(Story.class))
                .addAll(listStories)
                .addAll(topStories)
                .build()
                .execute(FlowManager.getDatabase(ZhihuDatabase.class).getWritableDatabase());
    }

    @Override
    public void saveDetail(final StoryDetail detail) {
        DatabaseDefinition database = FlowManager.getDatabase(ZhihuDatabase.class);
        Transaction transaction = database
                .beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        LogUtils.d(TAG, "save detail to local: " + detail.getId());
                        detail.save();
                    }
                })
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        LogUtils.d(TAG, "onSuccess");
                    }
                })
                .error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        LogUtils.e(TAG, "onError: " + error);
                    }
                })
                .build();
        transaction.execute();
    }
}
