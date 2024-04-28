package com.cachecats.meituan.utils.glide;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;

import java.nio.ByteBuffer;

import androidx.annotation.NonNull;

class Base64DataFetcher implements DataFetcher<ByteBuffer> {

   @Override
   public void loadData(Priority priority, DataCallback<? super ByteBuffer> callback) {}

   @Override
   public void cleanup() {}

   @Override
   public void cancel() {}

   @NonNull
   @Override
   public Class<ByteBuffer> getDataClass() {
      return ByteBuffer.class;
   }

   @NonNull
   @Override
   public DataSource getDataSource() {
      return DataSource.REMOTE;
   }
}