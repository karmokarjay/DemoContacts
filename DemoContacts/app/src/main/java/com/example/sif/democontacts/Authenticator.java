package com.example.sif.democontacts;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Authenticator extends AbstractAccountAuthenticator {

    private Context mContext;
    private Handler handler = new Handler();

    public Authenticator(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        return launchCheckLoginAuthentication(response, options);
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }

    private Bundle launchCheckLoginAuthentication(AccountAuthenticatorResponse accountAuthenticatorResponse, Bundle bundle) {

        if (bundle == null) {
            bundle = new Bundle();
        }

        AccountManager accountManager = AccountManagerHelper.getAccountManager(mContext);
        Account[] accounts = accountManager.getAccountsByType(mContext.getResources().getString(R.string.account_type));

        if (accounts != null && accounts.length > 0) {
            final String message = "Itna accounts lekr kya gand mein dalega..??";

            bundle.putInt(AccountManager.KEY_ERROR_CODE, 1);
            bundle.putString(AccountManager.KEY_ERROR_MESSAGE, message);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Intent intent = new Intent(mContext, LoginScreen.class);
            intent.putExtra("Account_Type", mContext.getResources().getString(R.string.account_type));
            intent.putExtra("auth_type", "bearer");
            intent.putExtra("IS_ADDING_NEW_ACCOUNT", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, accountAuthenticatorResponse);
            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        }
        return bundle;
    }
}
