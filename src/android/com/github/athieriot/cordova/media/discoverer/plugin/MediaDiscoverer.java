package com.github.athieriot.cordova.media.discoverer.plugin;

import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

public class MediaDiscoverer extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("discover")) {
            String types = args.getJSONArray(0);
            this.discover(types, callbackContext);
            return true;
        }
        return false;
    }

    private void discover(JSONArray types, CallbackContext callbackContext) {
        if (types != null && types.length() > 0) {
            callbackContext.success(types.getString(0));
        } else {
            callbackContext.error("Expected one non-empty Array argument.");
        }
    }
}