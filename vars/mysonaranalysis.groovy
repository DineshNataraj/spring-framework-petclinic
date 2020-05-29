def call(String server, String scanner,String sonarproperties) {
  
  withSonarQubeEnv("${server}") {

     sh "${tool("${scanner}")}/bin/sonar-scanner -Dproject.settings=${sonarproperties}"
}
}
