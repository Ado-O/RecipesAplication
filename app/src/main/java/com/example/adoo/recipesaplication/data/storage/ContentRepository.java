package com.example.adoo.recipesaplication.data.storage;

import com.example.adoo.recipesaplication.data.storage.remote.content.ContentRemoteDataSource;
import com.example.adoo.recipesaplication.data.storage.remote.response.BaseResponse;

public class ContentRepository {
    private static ContentRepository sContentRepository = null;

    private final ContentRemoteDataSource mContentRemoteDataSource;

    public ContentRepository(ContentRemoteDataSource contentRemoteDataSource) {
        mContentRemoteDataSource = contentRemoteDataSource;
    }

    public static ContentRepository getInstance(ContentRemoteDataSource contentRemoteDataSource) {
        if (sContentRepository == null) {
            sContentRepository = new ContentRepository(contentRemoteDataSource);
        }
        return sContentRepository;
    }

    public void getContent(final GetContentCallback callback) {
        mContentRemoteDataSource.getRecipe(
                new ContentRemoteDataSource.GetRecipeCallback() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        callback.onSuccess(baseResponse);
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    public interface GetContentCallback {
        void onSuccess(BaseResponse baseResponse);

        void onError();
    }

}
