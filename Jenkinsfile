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
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Run unit tests
                bat 'mvn test'
            }
        }
        stage('Package') {
            steps {
                // Package the application
                bat 'mvn package'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy the application
                bat '''
                copy target\\your-app.jar C:\\path\\to\\deployment\\folder\\
                cd C:\\path\\to\\deployment\\folder\\
                start java -jar your-app.jar
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
}
