package com.app.fusionarpdfs.core.extensions

import android.content.ContentResolver
import android.net.Uri

fun Uri?.isNullOrEmpty(): Boolean = this == null || this == Uri.EMPTY

fun Uri.toPersistableKey(): String = toString()

fun ContentResolver.findPersistedUri(uri: Uri): Uri? {
    return persistedUriPermissions
        .firstOrNull { it.uri == uri && it.isReadPermission }
        ?.uri
}
