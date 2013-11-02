This Way Up
===========

Sample app for my Droidcon London 2013 talk - "This Way Up: Implementing Effective Navigation".

Slides: TBA

![Example Image][1]

<a href="https://play.google.com/store/apps/details?id=com.readystatesoftware.example.thiswayup">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_60.png" />
</a>

Samples
-------

This app includes sample code demonstrating the following:

- Ancestral Up navigation using Jelly Bean APIs
- Ancestral Up navigation using Support Library APIs (NavUtils)
- Deep navigation from direct notifications
- Deep navigation from home screen widgets
- Navigation from outside tasks
- Retaining a parent activity instance on Up
- Launching external app activities
- Navigation drawer usage at root and deep level activities


Action Bar Compatibility
------------------------

This app is set to support only API 14 and above to simplify dependencies, but the code will work perfectly well (with minor modifications) for which ever Action Bar compatibility library you use:

- Use the samples in `com.example.thiswayup.jellybean` for ActionBarCompat
- Use the samples in `com.example.thiswayup.support` for ActionBarSherlock

License
-------

    Copyright 2013 readyState Software Ltd
    Copyright 2012 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




 [1]: https://raw.github.com/jgilfelt/ThisWayUp/master/art/framed.png
