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
       CheckoutWorkspace(branch: 'master', scmUrl: 'https://github.com/DineshNataraj/spring-framework-petclinic.git')
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
        mybuilddocker('dineshkumar55', 'sprintbootpetclinic', 'petclinicimage')
      }
    }     
   stage ('Kube Deploy') {
      steps {
        mykubeconfig('myawskeys','us-west-2', 'petclinic-cluster55')
      }
    }  
  }
}
