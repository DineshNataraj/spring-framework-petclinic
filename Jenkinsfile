@Library('PetClinicSharedLib') _
def readValuesYml(){
def props = readYaml (file: 'myvariables.yml')
return props;
 }
import groovy.json.*
//import groovy.json.JsonSlurperClassic
   //def filename='/var/lib/jenkins/workspace/SharedLibrary-Demo/MyInputsFile.json' 
   //jsonSlurper = new JsonSlurper()
   //def data = jsonSlurper.parse(new File(filename))
pipeline
{
agent any
  
  stages {
     stage ('Read variables') {
     steps {
        script {
                props = readValuesYml()
                println props.getClass()
     }
    }
  }
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
    /*stage('Junit Testing') {
       steps {
          testingbyjunit(props.junitloc.testpath)
       }
    }  */
    stage ('sonar analysis')
    {
     steps {
          withSonarQubeEnv(props.sonar.server) {
                         sonaranalysis(props.sonar.scannerproperties)                  
          }
         }
        } 
      /*steps {
        MavenSonarInt()
      }*/
     /*steps {
            script {
                 def scannerHome = tool 'mySonarqubepath';
                 withSonarQubeEnv("MySonarqube") {
                 sh "${tool("mySonarqubepath")}/bin/sonar-scanner"
               }
              }
             }
          }*/
    //}
    stage ('Build Docker') {
      steps {
        withCredentials([usernamePassword(
            credentialsId: props.CredId.dockercredid,
            usernameVariable: "Username",
            passwordVariable: "Password"
        )]) {
        mybuilddocker(props.dockerhub.dockhubuser, props.dockerhub.dockhubrepo, props.dockerhub.dockhubtag)
        }
      }
    }     
   stage ('Kube Deploy') {
      steps {
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: props.CredId.awsaccesskeyid, secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
        mykubeconfig(props.eks.eksclusterregion, props.eks.ekscluster)
        } 
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
