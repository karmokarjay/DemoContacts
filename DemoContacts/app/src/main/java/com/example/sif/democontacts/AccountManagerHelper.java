package com.example.sif.democontacts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class AccountManagerHelper {

    private static AccountManager accountManager;

    public static AccountManager getAccountManager(Context context) {
        if (accountManager == null) {
            accountManager = AccountManager.get(context);
        }
        return accountManager;
    }

    public static void createAccount(Context context, String email) {
        deleteAllAccounts(context);
        AccountManager accountManager = getAccountManager(context);
        Account account = new Account(email, context.getResources().getString(R.string.account_type));
        accountManager.addAccountExplicitly(account, null, null);
    }

    private static void deleteAllAccounts(Context context) {
        AccountManager accountManager = getAccountManager(context);
        Account[] accounts = accountManager.getAccountsByType(context.getResources().getString(R.string.account_type));

        for (Account account : accounts) {
            accountManager.removeAccount(account, null, null, null);
        }
    }
}
