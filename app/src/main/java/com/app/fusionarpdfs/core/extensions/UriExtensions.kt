package com.app.fusionarpdfs.core.extensions

import android.net.Uri

fun Uri?.isNullOrEmpty(): Boolean = this == null || this == Uri.EMPTY
