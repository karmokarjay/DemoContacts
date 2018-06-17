package com.example.sif.democontacts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AccountManagerHelper {

    private static AccountManager accountManager;

    public static AccountManager getAccountManager(Context context) {
        if (accountManager == null) {
            accountManager = AccountManager.get(context);
        }
        return accountManager;
    }

    public static void createAccount(Context context, String email) {
        AccountManager accountManager = getAccountManager(context);
        Account account = new Account(email, context.getResources().getString(R.string.account_type));
        accountManager.addAccountExplicitly(account, null, null);
    }

    public static void deleteAllAccounts(Context context) {
        AccountManager accountManager = getAccountManager(context);
        Account[] accounts = accountManager.getAccountsByType(context.getResources().getString(R.string.account_type));

        for (Account account : accounts) {
            accountManager.removeAccount(account, null, null, null);
        }
    }

    public static void syncImmediately(Context context){
        Account account=new Account("jaykarmokar@cardinbox.com",context.getResources().getString(R.string.account_type));
        Bundle bundle=new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL,true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED,true);
        ContentResolver.requestSync(account,context.getResources().getString(R.string.Authority),bundle);
    }


    public static void getContactDataBefore(Context context){
        int i=0;

        ArrayList<String> mIDs = new ArrayList<>();
        ArrayList<String> mNumbers = new ArrayList<>();

        // query all contact id's from device
        Cursor c1 = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID},null,null,null);

        if((c1 != null) && c1.moveToFirst()){

            // add contact id's to the mIDs list
            do{
                mIDs.add(c1.getString(c1.getColumnIndexOrThrow(ContactsContract.Contacts._ID)));

                // query all contact numbers corresponding to current id
                Cursor c2 = context.getContentResolver()
                        .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                                new String[]{mIDs.get(i)},null);

                if(c2 != null && c2.moveToFirst()){
                    // add contact number's to the mNumbers list
                    do{
                        mNumbers.add(c2.getString(c2
                                .getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    }while (c2.moveToNext());
                    c2.close();
                }

                i++;
            }while (c1.moveToNext() && i<c1.getCount());

            c1.close();
        }

        Log.d("ArraymIDS",mIDs.toString());
        Log.d("ArrayNos",mNumbers.toString());

    }
}
