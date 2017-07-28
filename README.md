<h3>Usage</h3>
Tests can be run with the following gradlew command:

```
$ ./gradlew runAllTests
```

<br>This task will run all TestNg tests and create allure report at tne end.
<br>You should see failed tests and generated Allure XML files in build/reports/allure-report directory.
<h3>Tasks</h3>
 <i><b>runAllTests</i></b> - runs all TestNg tests
 <br><b><i>runTemperatureServiceTests</i></b> - run TestNG tests with 'temperatureService' group
<h3>Overview</h3>
Create test framework based on:

```
Appium
Java
TestNG
Allure
Gradle
```
As app for testing was taken 'Unit converter' which can 
convert different measurements like length, weight, area, temperature and other.

<h3>Test plan</h3>
Common test cases:

<ul>
<li>User opens application. The home page should appear with action bar title
with default name ('Area')</li>
<li>User opens application and selects "Temperature" service from menu.
Than the "Temperature" service page should be displayed with action bar title "Temperature".</li>
<li>User opens application and selects "Temperature" service from menu.
Than user click on 'home' button. The home page should appear with action bar title the same as prevoiusly selected service</li>
</ul>

Test cases for temperature service:
<br>
Prestep: User opens application and selects "Temperature" service
from menu.

<ul>
<li>User enters number with the all different numbers in input field.
Verify that entered value is displayed in input field</li>
<li>User clicks on 'calculator' icon on the bottom of the page.
 The panel with numbers should appear. User clicks on 'ok' button
  and the panel with numbers should disappear</li>
<li>User opens panel with numbers and enters number.
After clickng on "clear" button the input field becomes empty.
</li>
<li>User opens panel with numbers and enters number.
    After clickng on "back" button the last symbol from entered number disappears.</li>
<li>
User opens panel with numbers and enters numbers.
After clickng on "+/-" button at first time the entered number becomes negative.
After clickng on "+/-" button at second time the entered number becomes positive again. </li>
<li>User opens panel with numbers and enters number.
The degree icons should be displayed correctly:

<ol>Celsius degree - °C</ol>
<ol>Fahrenheit degree - °F</ol>
<ol>Kelvin degree - K</ol>

</li>
<li>User opens panel with numbers and enters positive numbers.
User closes panel with number.
The degree values should be converted by next formula:
<ol>Celsius degree(C) = (entred value)</ol>
<ol>Fahrenheit degree(F) = (entered value)*1.8 + 32</ol>
<ol>Kelvin degree(K) = (entered value) + 273.15</ol>
</li>
<li>User opens panel with numbers and enters negative numbers.
    User closes panel with number.
    The degree values should be converted by next formula:
    <ol>Celsius degree(C) = (entred value)</ol>
    <ol>Fahrenheit degree(F) = (entered value)*1.8 + 32</ol>
    <ol>Kelvin degree(K) = (entered value) + 273.15</ol>
</li>
</ul>


