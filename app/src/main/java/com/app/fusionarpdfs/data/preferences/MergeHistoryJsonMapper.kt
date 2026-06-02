package com.app.fusionarpdfs.data.preferences

import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import org.json.JSONArray
import org.json.JSONObject

object MergeHistoryJsonMapper {

    fun encode(items: List<MergeHistoryItem>): String {
        val array = JSONArray()
        items.forEach { item ->
            array.put(
                JSONObject().apply {
                    put(FIELD_ID, item.id)
                    put(FIELD_FILE_NAME, item.fileName)
                    put(FIELD_FILE_URI, item.fileUri)
                    put(FIELD_FILE_SIZE_BYTES, item.fileSizeBytes)
                    put(FIELD_CREATED_AT, item.createdAt)
                },
            )
        }
        return array.toString()
    }

    fun decode(raw: String): List<MergeHistoryItem> {
        if (raw.isBlank()) return emptyList()

        return try {
            val array = JSONArray(raw)
            buildList {
                for (index in 0 until array.length()) {
                    val objectJson = array.getJSONObject(index)
                    add(
                        MergeHistoryItem(
                            id = objectJson.getString(FIELD_ID),
                            fileName = objectJson.getString(FIELD_FILE_NAME),
                            fileUri = objectJson.getString(FIELD_FILE_URI),
                            fileSizeBytes = objectJson.getLong(FIELD_FILE_SIZE_BYTES),
                            createdAt = objectJson.getLong(FIELD_CREATED_AT),
                        ),
                    )
                }
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

    private const val FIELD_ID = "id"
    private const val FIELD_FILE_NAME = "fileName"
    private const val FIELD_FILE_URI = "fileUri"
    private const val FIELD_FILE_SIZE_BYTES = "fileSizeBytes"
    private const val FIELD_CREATED_AT = "createdAt"
}
