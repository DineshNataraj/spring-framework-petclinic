def call(Map pipelineParams) {
  
  git branch: pipelineParams.branch, url: pipelineParams.scmUrl

  //git branch: ${branch}, url: ${url}
             
}
