# Introduction

Lup is a small android library that can help you to tracking bug that causes<br>
application stopped working (force close). Whiting this library included default<br> dialog (look demo below) and of course you customize it, we will talk about that later..



![demo](https://github.com/icodeuDev/Lup/blob/master/documentation/lup_demo.gif)


## Requirements

```
    org.jetbrains.kotlin:kotlin-gradle-plugin >= 1.5.30
```

## Download

Add it in your root build.gradle at the end of repositories:

```	
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```	
dependencies {
	        implementation 'com.github.icodeuDev:Lup:0.2.1'
	}
```

## Usage

### 1. Creating application class

First thing you need to do is creating class that extending `Application()`.
Then, define it in you `AndroidManifest.xml`


```    
    <application
        android:name="com.icodeu.lupdemo.LupDemoApp"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:requestLegacyExternalStorage="true"
```

### 2. Creating `Lup` instance

In order to creating `Lup` instance/object you can use `Lup.Builder` class.
Open your `application` class and look the code below for creating `lup` instance.

```  
class LupDemoApp : Application() {
override fun onCreate() {
super.onCreate()

    val lup = Lup.Builder(this)
        .showDefaultDialog("Oopss!","https://forms.gle/2P4NXJZWMAfKhg538")
        .addOnException { thread, throwable ->
            .doSomething()
        }
        .build()

        lup.start()
```

### 3. Starting `Lup`

Just call `.start()` (look the code above) method from `lup` instance you just created.

## References

### Lup Builder
```
When creating `lup` instance, you can customize `lup` by it's builder.
Here is all methods you can use in `builder`.
```
| Method | Description |
| --- | --- |
| .showDefaultDialog("Oopss!","https://forms.gle/2P4NXJZWMAfKhg538") | Customize dialog title and report Url |
| .disableDialog() | Disabling default dialog |
| .addOnException { thread, throwable -> } | Add custom exception when `Uncaugh Exception occurs`, you can do anything you want here. *Note :* If error occurs when calling this method using lambda, create `ExceptionHandler` instance and put in the parameter instead. |
| .restartAfterCrash(SecondActivity::class.java) | Restarting application when application force close to given `Activity` |
| .build() | Returning `Lup` instance |

### Logs

All logs that caused application crash are saved in `Android/data/~your app package~/files/lup/LupLog.txt`

## License

```
Copyright 2021 icodeuDev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```