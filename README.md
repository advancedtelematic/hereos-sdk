# HereOS Embedded SDK

Welcome to the HereOS Embedded SDK. This tool allows you to build simple gRPC-enabled microservices and test them in an emulated Linux environment with OTA support.

Prerequisites
---

The SDK is designed to run in Ubuntu Linux (tested on Ubuntu 18.04). You can find details of how to obtain Ubuntu here:

http://releases.ubuntu.com/18.04/

In addition to Ubuntu, your system will need some basic tools installed to run Yocto:

https://www.yoctoproject.org/docs/1.8/yocto-project-qs/yocto-project-qs.html#ubuntu

and the Python YAML library installed:

```
  pip install pyyaml
```

Credentials
---

The HereOS Embedded SDK comes with support for Here's OTA Connect solution to support over-the-air updates, and Here's Open Location Platform for the ingestion of data.

## OTA Connect Setup

You can sign up for an OTA Connect account at

https://connect.ota.here.com

Once logged in, you can create and download "Provisioning Credentials", which you can find in the 'Provisioning Keys' submenu of the profile menu in the top right. Create a new Provisioning Key, and download the `credentials.zip` file to your computer.

## Open Location Platform Setup

You can sign up for an account for the Open Location Platform at

https://platform.here.com

From the profile menu in the top right, select 'Apps and Keys' and select 'Register New App'. From your new app, select 'Create Key' to create a new key, and download the `credentials.properties` file. This file will include the Client ID and Client Secret for accessing your OLP application. With this key and secret, create a file `credentials-olp.yaml` as follows:

```
client_id: <your_client_id>
client_secret: <your_client_secret>
```

Add this file to the `credentails.zip` archive that you downloaded from the OTA Connect Setup.

## Yocto Setup

To make the credentials available to Yocto, you will need to create a file `${USER_HOME}/.hereos/config/yocto-extra`, that includes information about where to find the `credentials.zip` file:

```
SOTA_PACKED_CREDENTIALS = "/path/to/your/credentials.zip"
```


Installing the SDK
---

To install the SDK, run the command:

```
  curl -sSL http://prospero.talkingcode.co.uk/hereos-installer | bash
```

You will be prompted to run the command

```
  . ~/.hereos/scripts/register
```
Which will register the command `hereos` in your environment and make the SDK available. You will need to do this in every new terminal (you can add this to your Bash Profile if you want this to happen automatically).

Creating your first Project
---

Type

```
hereos init my-first-project
```

to create a new HereOS project in the folder `my-first-project` in the current folder. This will trigger a lot of downloading and copying, which may take around 5 minutes. When this is done, enter this directory with

```
cd my-first-project
```

When you execute the `hereos` command from this folder, you will configuring, building and running this new project.

Building your Project
---

Type

```
hereos build
```

from your project folder to kick off a build. Because this will build the whole SDK image (including the whole of Linux) from scratch, this may take up to 10 hours to complete. Subsequent builds will be faster because of the caching Yocto does.

Running your Project
---

Type

```
hereos runqemu
```

to launch the emulator. This will run an instance of QEMU using the image you just built. If you have correctly configured your credentials, this QEMU emulator will automatically connect to OTA Connect, and you will be able to see your new device live at https://connect.ota.here.com

Creating your first microservice
---

Type

```
hereos create-client hello-world
```

to create a basic gRPC client service. This will create a template in the folder `services/hello-world` in your project. If you type `hereos list-services`, you will see that the service has been registered as part of the build. If you type `hereos build`, the project will be rebuilt including the new microservice, and the new image will automatically be pushed to the OTA Connect server. You can install the image on the running QEMU device using the OTA Connect admin interface, or you can enable 'Automatic Updates' in the OTA Connect admin interface to automatically install any newly-built images.
