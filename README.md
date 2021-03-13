# Using Retrofit with Kotlin Coroutines in Android to Collect Data From The New York Times APIs

This repository contains a sample app that implements MVVM architecture using Kotlin, ViewModel, Retrofit, Coroutines and etc to get popular articles provided by NY APIs
and set it in recyclerview with custom adapter.
<p align="center">
  <img src="https://i.ibb.co/kqDNNmj/20210313-221358.jpg"> 
</p>
<br>
<br>

1. Where to find The New York Times article metadata
2. How to request article metadata from The New York Times

The New York Times offers a developer network consisting of several APIs to let non-employees request articles and metadata from them, which is super convenient for this analysis

<p align="center">
  <img src="https://miro.medium.com/max/2835/1*6i5EW5Dw-aon5fTvl7CP7g.png">
</p>
<br>
<br>

1. **Register a new app in your developer’s account.**
2. **Click on your e-mail address in the upper-right corner.**
3. **Select Apps in the drop-down menu that appears.**
4. **Select +NEW APP on your Apps page.**
5. **Enter any name and description.**
6. **Activate the Archive API.**
7. **Select Create.**

Take note of your just registered app’s API key. This is the only piece of information we need to get data from The New York Times, and we will need it soon.
<br>
<br>
<p align="center">
  <img src="https://miro.medium.com/max/1500/1*YG3dlMz2lz23iO_QRl1o5A.png">
</p>
<br>
<br>
<p align="center">
  <img src="https://miro.medium.com/max/1500/1*2a-f-oGP7RBW1VRON51M_w.png">
</p>
<br>
<br>

#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **ui**: View classes along with their corresponding ViewModel.
3. **utils**: Utility classes.

### Reference resources:
1. Coroutines: [Check here](https://blog.mindorks.com/mastering-kotlin-coroutines-in-android-step-by-step-guide)
2. Retrofit: [Check here](https://square.github.io/retrofit/)

