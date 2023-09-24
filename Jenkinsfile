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
                    def catCommandOutput = sh(script: 'cat README.md', returnStdout: true).trim()
                    
                    emailext(
                        subject: emailSubject,
                        body: emailBody,
                        to: 'hydraesport20@gmail.com', // Change this to your recipient's email address
                        attachLog: true,
                    )
                }
            }
        }
    }
    
}
