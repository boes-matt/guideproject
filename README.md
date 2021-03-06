# Guide Project

Guide Project is my experiment in building a better Android app.

I use interfaces, a dependency injection pattern, and follow a test-driven development approach.

I use Espresso and Mockito in tests.

I move all business logic outside of Android system into pure Java library.

The data for the app is mocked, and there is only one guide in the mocked data set.

## Screenshots

<img src="https://raw.githubusercontent.com/boes-matt/guideproject/master/app/screenshots/map.png" height="375" />
&nbsp;&nbsp;
<img src="https://raw.githubusercontent.com/boes-matt/guideproject/master/app/screenshots/stanford.png" height="375" />
&nbsp;&nbsp;
<img src="https://raw.githubusercontent.com/boes-matt/guideproject/master/app/screenshots/hoover.png" height="375" />

## Packages

[com.boes.guideproject.app](https://github.com/boes-matt/guideproject/tree/master/app/src/main/java/com/boes/guideproject/app):
Thin activities and Android implementations of interfaces defined in core package.

[com.boes.guideproject.app.test](https://github.com/boes-matt/guideproject/tree/master/app/src/androidTest/java/com/boes/guideproject/app/test):
Espresso acceptance tests and unit tests with Mockito.

[com.boes.guideproject.core](https://github.com/boes-matt/guideproject/tree/master/core/src/main/java/com/boes/guideproject/core):
Business logic.  TourGuide is main controller.

[com.boes.guideproject.core.test](https://github.com/boes-matt/guideproject/tree/master/core/src/test/java/com/boes/guideproject/core/test):
Integration testing of business logic.

## Outline of dependency graph

<img src="https://raw.githubusercontent.com/boes-matt/guideproject/master/app/screenshots/graph.jpg" />