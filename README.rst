This plugin allows your to start processes using the spring-loaded_ Java class reloading agent, 
a FOSS alternative to JRebel (albiet a somewhat limited alternative).

This has been minimally tested against spray and akka classes YMMV. 

This is the authors first "from-scratch" sbt plugin, so please be gentle.

Installation
============
sbt-spring-loaded requires SBT_ >= 0.13.5 (for AutoPlugin).  Add the following to your project/plugins.sbt::

  addSbtPlugin("me.browder" % "sbt-spring-loaded" % "0.2.0")

Usage
=====
Since this requires forking the jvm and we don't want to wait for the application to recompile there are special commands to run your code:  

:code:`reStart`
  start the mainClass of your project using spring-loaded, all arguments are passed though to it.

:code:`reStop`
  stop a previous invocation of :code:`reStart`

:code:`reList`
  list what projects that have :code:`reStart` JVMs running
  
Typical use case::

  reStart
  ~compile

When you change your source you'll get new compiles which spring-loaded will pick up.

By default version 0.2.0 and greater now uses the Concurrent Mark Sweep GC so that class unloading can be used.
Otherwise classes are kept forever which can lead to OOM: PermGen when doing class reloading, esp. on JDK < 8 which pulls from a fixed PermGen pool.
JDK 8 introduces a new Mataspace which can use all of your memory (reducing the occurence of the issue)
You can override this behavior by doing the following in sbt::

  set noClassUnloading := true

Or you can add the following after the plugin load in plugins.sbt::

  noClassUnloading := true
  


 
Special Thanks
==============
* SBT_
* `spray/sbt-revolver`_ for inspiration (the command lines are similar, hope you don't mind)
* spring-loaded_ - really really under apprecieted (at least by those outside of the Grails community)

TODO
====
* Determine if it's possible to parameterize the spring-loaded_ dependency. 
* Better logging
* Testing

Copyright and License
=====================

Released under GPLv3_.

Copyright (C) 2014 Kevin Browder.

.. _SBT: http://www.scala-sbt.org/
.. _GPLv3: http://www.gnu.org/licenses/gpl-3.0.txt
.. _spring-loaded: https://github.com/spring-projects/spring-loaded
.. _spray/sbt-revolver: https://github.com/spray/sbt-revolver