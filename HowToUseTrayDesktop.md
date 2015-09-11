# Introduction #

Traydesktop is an application that fit into the notification bar and check for update of your fsnet account.



![http://img560.imageshack.us/img560/5108/traydesktopwin.png](http://img560.imageshack.us/img560/5108/traydesktopwin.png)

# Lauch #

To use tray desktop use this commande line :

```
java -jar trayDesktop.jar
```


If you are behind a proxy you must specify like this:

```
java -Dhttp.proxyHost=url.of.the.proxy.com -Dhttp.proxyPort=3128 -jar trayDesktop.jar
```

# Configure #

![http://img695.imageshack.us/img695/5795/fsnet.png](http://img695.imageshack.us/img695/5795/fsnet.png)

Now in the configuration panel of traydesktop specify:
  * your login
  * password
  * the url where your fsnet is installed (example: http://site/fsnetWeb/ )
  * the url of the webservice (example: http://site/webservice/jaxws)
  * choose your langage
  * the delay that the application have to wait between two verification