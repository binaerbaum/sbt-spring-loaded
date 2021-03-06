//resolvers += "me.browder staging" at "https://oss.sonatype.org/content/repositories/mebrowder-1002/"
{
  val pluginVersion = System.getProperty("plugin.version")
  if(pluginVersion == null)
    throw new RuntimeException("""|The system property 'plugin.version' is not defined.
                                  |Specify this property using the scriptedLaunchOpts -D.""".stripMargin)
  else addSbtPlugin("me.browder" % "sbt-spring-loaded" % pluginVersion)
}