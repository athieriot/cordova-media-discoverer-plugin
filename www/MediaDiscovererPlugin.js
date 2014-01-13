var MediaType = {
  'VIDEO': 10
}

window.discover = function(type, callback) {
    cordova.exec(callback, function(err) {
        callback(err);
    }, "MediaDiscoverer", "discover", [type]);
};
