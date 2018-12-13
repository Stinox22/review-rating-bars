# ReviewRatingBars

So you want to add a ratings graph to your project!
And you tried searching online but couldn't find any library...right?
Well! You are in the right place!

I had this same issue, was searching for such library that could've saved my precious development time, but unlucky!
Thought there might be a huge community searching for the same and maybe I could help them and save their valuable time!

So here it is for you geeks!

Fork it! Pull it! Raise issues! Create PRs! 
Much Appreciated!

*Also note that this is my first library(noob in it) and created and uploaded it in hurry. Kindly bare with me.

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Stinox22:review-rating-bars:v2.0.0'
	}
