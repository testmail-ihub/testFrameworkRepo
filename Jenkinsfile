pipeline {
    agent any

    triggers {
        githubPullRequest()
    }

    stages {
        stage('Build and Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}