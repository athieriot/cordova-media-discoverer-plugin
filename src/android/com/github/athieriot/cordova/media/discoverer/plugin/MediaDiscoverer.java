package com.github.athieriot.cordova.media.discoverer.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import android.database.Cursor;
import android.provider.MediaStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import static java.lang.String.valueOf;

import java.util.HashMap;

public class MediaDiscoverer extends CordovaPlugin {

    private static final String[] VIDEO_PROJECTION = {
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.RESOLUTION,
            MediaStore.Video.Media.DATA
    };

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("discover")) {
            int type = args.getInt(0);
            this.discover(type, callbackContext);
            return true;
        }

        return false;
    }

    private void discover(int type, CallbackContext callbackContext) throws JSONException {
        if (type > 0) {

            Cursor cursor = MediaStore.Video.query(
                    this.webView.getContext().getContentResolver(),
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    this.VIDEO_PROJECTION
            );

            callbackContext.success(getMediasAsJSON(cursor));
        } else {
            callbackContext.error("Expected one non-empty Array argument.");
        }
    }

    private JSONArray getMediasAsJSON(Cursor cursor) {
        List<JSONObject> medias = new ArrayList<JSONObject>();

        while (cursor.moveToNext()) {
          Map<String, String> currentMedia = new HashMap<String, String>();

          for (int i = 0; i < cursor.getColumnCount(); i++) {
            switch (cursor.getType(i)) {
              case Cursor.FIELD_TYPE_NULL:                                                                              break;
              case Cursor.FIELD_TYPE_INTEGER: currentMedia.put(this.fieldName(cursor, i), valueOf(cursor.getInt(i)));   break;
              case Cursor.FIELD_TYPE_FLOAT:   currentMedia.put(this.fieldName(cursor, i), valueOf(cursor.getFloat(i))); break;
              case Cursor.FIELD_TYPE_STRING:
                                    default:  currentMedia.put(this.fieldName(cursor, i), cursor.getString(i));         break;
            }
          }

          medias.add(new JSONObject(currentMedia));
        }

        return new JSONArray(medias);
    }

    private String fieldName(Cursor cursor, int index) {
      return cursor.getColumnName(index).replaceFirst("^_", "");
    }
}
