package com.carmel.guestjini.Screens.Common.Controllers;

import android.content.Intent;

import androidx.annotation.Nullable;

public interface ActivityResultListener {
    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
}
