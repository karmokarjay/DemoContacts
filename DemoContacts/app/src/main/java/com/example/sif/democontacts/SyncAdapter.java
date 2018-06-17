package com.example.sif.democontacts;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private Context mContext;
    private Handler handler=new Handler();

    SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContext = context;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.d("Dewils_Account","onPerformSync called");
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, "onPerformSync Called", Toast.LENGTH_SHORT).show();
                AccountManagerHelper.getContactDataBefore(mContext);
            }
        });
    }
}
