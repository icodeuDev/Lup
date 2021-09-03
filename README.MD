"Runtime Exception" logger and handler. We will write full documentation later after a cup of coffe

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.icodeuDev:Lup:Tag'
	}
