MediaType.VIDEO = [".avi"];

window.discover = function(types, callback) {
    cordova.exec(callback, function(err) {
        callback([]);
    }, "MediaDiscoverer", "discover", [types]);
};
