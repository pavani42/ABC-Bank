// node {
//   stage("Clone the project") {
//     git branch: 'master', url: 'https://github.com/pavani42/ABC-Bank.git'
//   }

//   stage("Compilation") {
//     sh "./mvnw clean install -DskipTests"
//   }
pipeline {
    agent any
    triggers { // Poll SCM every 5 minutes 
        pollSCM('H/5 * * * *') 
    }

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
                copy target\\example-application.jar C:\\Users\\S S Rao\\capstone\\banking-application\\target\\
                cd C:\\Users\\S S Rao\\capstone\\banking-application\\target\\
                start java -jar example-application.jar
                '''
            }
        }
    }
 post {
        success {
            script {
                def gitCommit = bat(script: 'git rev-parse HEAD', returnStdout: true).trim()
                withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                    def command = """curl -H "Authorization: token %GITHUB_TOKEN%" -X POST -d "{\\"state\\": \\"success\\", \\"target_url\\": \\"${env.BUILD_URL}\\", \\"description\\": \\"The build succeeded.\\", \\"context\\": \\"jenkins-ci\\"}" https://api.github.com/repos/pavani42/ABC-Bank/statuses/${gitCommit}"""
                    bat script: command, returnStatus: true
                }
            }
            echo 'Pipeline succeeded!'
        }
        failure {
            script {
                def gitCommit = bat(script: 'git rev-parse HEAD', returnStdout: true).trim()
                withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                    def command = """curl -H "Authorization: token %GITHUB_TOKEN%" -X POST -d "{\\"state\\": \\"failure\\", \\"target_url\\": \\"${env.BUILD_URL}\\", \\"description\\": \\"The build failed.\\", \\"context\\": \\"jenkins-ci\\"}" https://api.github.com/repos/pavani42/ABC-Bank/statuses/${gitCommit}"""
                    bat script: command, returnStatus: true
                }
            }
            echo 'Pipeline failed!'
        }
    }
}
