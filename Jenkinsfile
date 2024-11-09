node {
  stage("Clone the project") {
    git branch: 'master', url: 'https://github.com/pavani42/ABC-Bank.git'
  }

  stage("Compilation") {
    sh "./mvnw clean install -DskipTests"
  }

  stages {
        stage('Example') {
            steps {
                echo 'Hello World'
            }
        }
    }

  stage("Tests and Deployment") {
    stage("Runing unit tests") {
      sh "./mvnw test -Punit"
    }
    stage("Deployment") {
      sh 'nohup ./mvnw spring-boot:run -Dserver.port=8001 &'
    }
  }
}
