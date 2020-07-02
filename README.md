# Base

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/devloopsnet/Base.svg)](https://jitpack.io/#devloopsnet/Base)

Base is an Easy to use boilerplate module for Android Applications!
It has the following set of Helpers:
* BaseApp
* BaseActivity
* BaseFragment
* ApiClient (Retrofit http client)
* Api (Volley http client for direct Web Calls)
* Alerts
* AppSession
* ConnectionDetector
* DateTimeFormatter
* ImageLoader (support any type of image including SVGs)
* Logger
* Utils (helper methods)
* Validator
* WebViewActivity

### Installing
This Module is available through jitpack.io. You can also import this library as a module.

### Add the following to your app level build.gradle file
```
defaultConfig {
    ...
     multiDexEnabled = true
}
```
### Base uses lambdas and view binding 
add the following to build.gradle file inside ```android {}``` tag  
```
android{
    ...
    compileOptions {
            sourceCompatibility = '1.8'
            targetCompatibility = '1.8'
    }

     buildFeatures {
            viewBinding = true
     }
}
```

### Add it in your project level build.gradle at the end of repositories:
```
allprojects {
       repositories {
           ...
           maven { url 'https://jitpack.io' }
         }
    }
```    

### Add the dependency
```
dependencies {
	     implementation 'com.github.devloopsnet:Base:$LATEST_VERSION'
}
```

# Usage
* create Application Class and Extend BaseApp.
* define Application class in your manifest
* extend your activities with BaseActivity
* extent your fragments with BaseFragment

And your good to go...
 
## To Use retrofit http client

##### 1- Define BASE URL to use
inside ```onCreate();``` on Application Class  

```BaseApp.setBaseUrl("https://example.com/apis/");```

##### 2- Create ApiRequester Class 
find example class here [ApiRequester](https://github.com/devloopsnet/Base/blob/master/app/src/main/java/com/devloops/activities/http/ApiRequester.java)

##### 3- Create ApiMethods Class  
find example class here [ApiMethods](https://github.com/devloopsnet/Base/blob/master/app/src/main/java/com/devloops/activities/http/ApiMethods.java)

##### 4- Create instance of ApiRequester
```
        requester = ApiRequester.getInstance(context);
```
Then call your api..
```
        requester.getTestRequest(new ApiRequester.ApiCallback() {
            @Override
            public void onSuccess(BaseModel model, String method) {
                //handle results
                Logger.i(Constants.TAG, "success");
            }

            @Override
            public void onError(String error) {
                //log errors
                Logger.i(Constants.TAG, error);
            }
        }, ApiMethods.Methods.EXAMPLE_PATH);
```

## To Use Volley http client

Volley client support 4 methods
- get
- post
- put
- delete

##### To use it just call...

```
       //Create a Map of headers (optional)
        Map<String, String> headers = new HashMap<>();

        Api.with(context).addHeaders(headers).get("",
                response -> {

                }, error -> {

                });
``` 

## Author
[Odey M. Khalaf](https://github.com/OdeyFox)

## License
   Copyright (c) [2020] [Devloops LLC](https://devloops.net/)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc