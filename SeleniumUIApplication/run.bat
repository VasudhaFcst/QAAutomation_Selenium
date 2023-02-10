et projectLocation=D:\Selenium_Workspace\KeywordReal
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*;
java org.testng.TestNG %projectLocation%\testng1.xml
pause
