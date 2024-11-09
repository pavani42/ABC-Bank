// node {
//   stage("Clone the project") {
//     git branch: 'master', url: 'https://github.com/pavani42/ABC-Bank.git'
//   }

//   stage("Compilation") {
//     sh "./mvnw clean install -DskipTests"
//   }

 pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from version control
                git branch: 'master', url: 'https://github.com/pavani42/ABC-Bank.git'
            }
        }
        stage('Build') {
            steps {
                // Build the project using Maven
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Run unit tests
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                // Package the application
                sh 'mvn package'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy the application (example for a Tomcat server)
                sh '''
                cp target/your-app.jar /path/to/deployment/folder/
                cd /path/to/deployment/folder/
                nohup java -jar your-app.jar &
                '''
            }
        }
    }

    post {
        always {
            // Clean up workspace
            cleanWs()
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
