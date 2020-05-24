@Library('PetClinicSharedLib') _
import groovy.json.*
//import groovy.json.JsonSlurperClassic
  
   //def filename='/var/lib/jenkins/workspace/SharedLibrary-Demo/MyInputsFile.json' 
   //jsonSlurper = new JsonSlurper()
   //def data = jsonSlurper.parse(new File(filename))
  
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
    stage ('sonar analysis')
    {
      steps {
        MavenSonarInt()
      }
    }
    stage ('Build Docker') {
      steps {
        withCredentials([usernamePassword(
            credentialsId: "mydockerhubcredentials",
            usernameVariable: "Username",
            passwordVariable: "Password"
        )]) {
        mybuilddocker('dineshkumar55', 'sprintbootpetclinic', 'petclinicimage')
        }
      }
    }     
   stage ('Kube Deploy') {
      steps {
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'myawskeys', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) //{
        mykubeconfig('us-west-2', 'petclinic-cluster55')
        //} 
      }
    }  
}
}
//}
        //mykubeconfig('myawskeys','us-west-2', 'petclinic-cluster55')
      //}
   // }  
 // }
//}
