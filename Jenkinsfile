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
       myPipeline(branch: 'master', scmUrl: 'https://github.com/DineshNataraj/spring-framework-petclinic.git')
       // myDeliveryPipeline('master', 'https://github.com/DineshNataraj/spring-framework-petclinic')
     }
    }
    stage('mvn build')
    {
      steps {
        MavenCompile()
      }
    }
//    stage ('sonar analysis')
//    {
//      steps {
//        MavenSonarInt()
//      }
//    }
    stage ('Build Docker') {
      steps {
        mybuilddocker.call('dineshkumar55', 'sprintbootpetclini', 'petclinicimage')
      }
    }     
   stage ('Kube Deploy') {
      steps {
        mykubeconfig.call('myawskeys','us-west-2', 'petclinic-cluster55')
      }
    }  
  }
}
