def call(String region, String ekscluster) {
              echo "Login Successfull"
              sh "aws eks --region '${region}'  update-kubeconfig --name '${ekscluster}'"
              sh 'sed -i s/"BUILD_NUMBER"/"v$BUILD_NUMBER"/g Deploy.yaml'
              sh 'kubectl apply -f Deploy.yaml'
              sh 'kubectl apply -f service.yaml'
              sh 'kubectl get svc'
              }
