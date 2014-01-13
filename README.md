# Media Discoverer Plugin

Plugin for Steroids.js. Compatible with Cordova and [plugman](https://github.com/apache/cordova-plugman).

## Usage

MediaDiscoverer helps you find the media files available on the devices.
It back a list of media with their full path.

```
window.discover(MediaType.VIDEO, function(videoList) {
    
});
```

## Return Format

        [{
           "data": "/storage/sdcard/Movies/Doctor.Who.2005.6x10.The.Girl.Who.Waited.avi",
           "display_name": "Doctor Who - Series 6 - The Girl Who Waited",
           "title": "Doctor.Who.2005.6x10.The.Girl.Who.Waited",
           "duration": "2516008",
           "mime_type": "video/avi",
           "size": "367358782"
        }]
