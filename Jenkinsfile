pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Send Email') {
            steps {
                script {
                    def emailSubject = 'Nouveau commit sur le dépôt'
                    def emailBody = readFile('README.txt') // Read the content of README.txt
                    
                    emailext(
                        subject: emailSubject,
                        body: emailBody,
                        to: 'recipient@example.com', // Change this to your recipient's email address
                        attachLog: true,
                    )
                }
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline succeeded. Sending email...'
        }
        failure {
            echo 'Pipeline failed. Sending email...'
        }
    }
}
