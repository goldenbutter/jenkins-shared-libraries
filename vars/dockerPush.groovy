def call(String imageName, String imageTag, String credentialsId) {
  withCredentials([usernamePassword(
    credentialsId: credentialsId,
    usernameVariable: 'DOCKERHUB_USER',
    passwordVariable: 'DOCKERHUB_PASS'
  )]) {
    sh """
      echo "\$DOCKERHUB_PASS" | docker login -u "\$DOCKERHUB_USER" --password-stdin
      docker tag ${imageName}:${imageTag} "\$DOCKERHUB_USER/${imageName}:${imageTag}"
      docker push "\$DOCKERHUB_USER/${imageName}:${imageTag}"
    """
  }
}
