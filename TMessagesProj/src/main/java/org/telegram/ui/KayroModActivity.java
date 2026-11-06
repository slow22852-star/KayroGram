package org.telegram.ui;

import android.content.Context;
import android.content.SharedPreferences;

public final class KayroModActivity {

private static final String PREFS = "kayrogram_mod";
private static final String OWNER = "owner_id";

private KayroModActivity() {}

public static void initialize(Context context, long ownerId) {
    SharedPreferences prefs = context.getSharedPreferences(
            PREFS, Context.MODE_PRIVATE);

    if (!prefs.contains(OWNER)) {
        prefs.edit().putLong(OWNER, ownerId).apply();
    }
}

public static boolean isOwner(Context context, long userId) {
    return context.getSharedPreferences(
            PREFS, Context.MODE_PRIVATE)
            .getLong(OWNER, 0) == userId;
}

public static boolean setTrusted(Context context,
                                long ownerId,
                                long userId,
                                boolean enabled) {

    if (!isOwner(context, ownerId)) {
        return false;
    }

    context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putBoolean("trusted_" + userId, enabled)
            .apply();

    return true;
}

public static boolean setTester(Context context,
                               long ownerId,
                               long userId,
                               boolean enabled) {

    if (!isOwner(context, ownerId)) {
        return false;
    }

    context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putBoolean("tester_" + userId, enabled)
            .apply();

    return true;
}

public static boolean isTrusted(Context context, long userId) {
    return context.getSharedPreferences(
            PREFS, Context.MODE_PRIVATE)
            .getBoolean("trusted_" + userId, false);
}

public static boolean isTester(Context context, long userId) {
    return context.getSharedPreferences(
            PREFS, Context.MODE_PRIVATE)
            .getBoolean("tester_" + userId, false);
}

      }
