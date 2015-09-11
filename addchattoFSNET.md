## Integrating a talk to  FSNET ##



# First solution: #
### Using the XMPP service to Google App Engine ###

> #### inconveniant: ####
    * you have to create a project like Google App Engine with an eclipse plugin, using the Google SDK.
    * If I create a project with SDK google chat, I dont know how
integrate it into FSNET.
    * it is a google project with configuration files of the google api, But the talk module  will  be  integrated to FSNET



> #### Point de blocage ####
    * oogle XMPP server is built into the SDK, I do not know how to handle it
> > > and add contact.
    * have to work with the embedded server sdk google.
    * can't see how Xmpp goggle built.
    * ntegrate the XMPP API for App Engine in FSNET.







# Second solution: #
### Install our own XMPP server. ###


> Server version: openfire, lisence GPL, open source
> Development of the client with the smack API.