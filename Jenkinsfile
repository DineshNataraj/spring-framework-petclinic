@Library('PetClinicSharedLib') _
def readValuesYml(){
//def props = readYaml (file: 'myvariables.yml')
 def props = readProperties  file: 'dir/myvariables.properties'
return props;
 }
import groovy.json.*

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
       //CheckoutWorkspace(branch: 'master', scmUrl: 'https://github.com/DineshNataraj/spring-framework-petclinic.git')
      echo "checkout workspace print"
       echo "${branch}"
       //echo ${branch}
       echo "${props.branch}"
       //echo ${props.branch}
       CheckoutWorkspace(branch: props['branch'] , scmUrl: props['repo']) 
       // myDeliveryPipeline('master', 'https://github.com/DineshNataraj/spring-framework-petclinic')
     }
    }
    stage('mvn build')
    {
      steps {
        MavenCompile()
      }
    }
    stage('Junit Testing') {
       steps {
          testingbyjunit(props['testpath'])
       }
    }
    stage ('sonar analysis')
    {
     steps {
          //withSonarQubeEnv(props.sonar.server) {
                         mysonaranalysis(props['server'],props['scanner'],props['scannerproperties'])                  
          //}
         }
        } 
    stage ('Build Docker') {
      steps {
        withCredentials([usernamePassword(
            credentialsId: props.dockercredid,
            usernameVariable: "Username",
            passwordVariable: "Password"
        )]) {
        mybuilddocker(props.dockhubuser, props.dockhubrepo, props.dockhubtag)
        }
      }
    }     
   stage ('K8s Deployment') {
      steps {
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: props.awsaccesskeyid, secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
        mykubeconfig(props.eksclusterregion, props.ekscluster)
        } 
      }
    }  
}
}
