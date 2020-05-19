@Library('PetClinicSharedLib') _
//import groovy.json.JsonSlurperClassic
pipeline
{
agent any
  stages {
   stage('checkout'){
     steps {
         //ef projects = readJSON file: 'Projects.json'
         //def data = new JsonSlurperClassic().parseText(projects)
       myDeliveryPipeline(branch: 'master', scmUrl: 'https://github.com/DineshNataraj/spring-framework-petclinic.git')
       // myDeliveryPipeline('master', 'https://github.com/DineshNataraj/spring-framework-petclinic')
     }
    }
    stage('mvn build')
    {
      steps {
        Dockerbuild()
      }
    }
    stage ('sonar analysis')
    {
      steps {
        
        sonar()
      }
    }
    
  }
}
