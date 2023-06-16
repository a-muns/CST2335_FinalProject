# NASA-Image-of-the-Day-App
Welcome to the NASA Image of the Day application: an Android app made to facilitate searching the NASA Astronomy Picture of the Day (APOD) API.
Users can choose a date to query the APOD API, and add dates to a favourites list. Favourites are saved locally to the user's device using an SQLite database.
<br>By using string resources, the application's native text is dynamically presented in English or Spanish, determined by the language set on the user's device. This does not apply to text drawn from the NASA API (i.e., text from NASA is not translated).
<br><br>
APOD Website: https://apod.nasa.gov/apod/astropix.html 
<br>APOD GitHub: https://github.com/nasa/apod-api)
<br><br>
<em>Application created in Android Studio with Java, XML, SQLite and Gradle.</em>
<br>

## Title Page
Entry point to the application. Can optionally take the user's name and favourite celesital body and save them to the device's SharedPreferences, displaying them later for a personalized experience.
<br><br>
![App title page](https://github.com/a-muns/NASA-Image-of-the-Day-App/blob/df37090d5cf0a4ceb42c0d94be0aeeac59983b40/Photos/MainActivity.jpg)

## Home Page
A central hub linking to important features of the app - searching and the user's list of favourites.
<br><br>
![App home page](https://github.com/a-muns/NASA-Image-of-the-Day-App/blob/df37090d5cf0a4ceb42c0d94be0aeeac59983b40/Photos/HomeActivity.jpg)

## Search
A calendar widget facilitates searching dates. Dates can be searched back to June 16, 1995 - the first APOD posting!
<br><br>
![Search calendar](https://github.com/a-muns/NASA-Image-of-the-Day-App/blob/df37090d5cf0a4ceb42c0d94be0aeeac59983b40/Photos/SearchActivity.jpg)

## View Results
Results from the APOD API are shown here. Date, title, and description of the result are displayed in an easy to read format. The browser can be invoked to display the image. The result can also be saved to the user's favourites list. 
<br><br>
![Displaying search results](https://github.com/a-muns/NASA-Image-of-the-Day-App/blob/df37090d5cf0a4ceb42c0d94be0aeeac59983b40/Photos/ResultActivity.jpg)

## Open Images in Browser
Click "Open Image in Browser" on the results page to display the image.
<br><br>
![Image displayed in browser](https://github.com/a-muns/NASA-Image-of-the-Day-App/blob/df37090d5cf0a4ceb42c0d94be0aeeac59983b40/Photos/BroswerImg.jpg)

## Track and Manage Favourites
Here the user can see the results they've favourited. A favourite can be opened by clicking it. Favourites can be removed from the list individually as desired by the user.
<br><br>
![List of user's favourites](https://github.com/a-muns/NASA-Image-of-the-Day-App/blob/df37090d5cf0a4ceb42c0d94be0aeeac59983b40/Photos/FavouritesActivity.jpg)
