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
                    
                    // Use the 'sh' step to execute the 'cat' command and capture its output
                    def catCommandOutput = sh(script: 'cat README.md', returnStdout: true).trim()
                    
                    emailext(
                        subject: emailSubject,
                        body: catCommandOutput,
                        recipientProviders: [[$class: 'RequesterRecipientProvider']],
                        to: emailextrecipients([[$class: 'RequesterRecipientProvider']]),
                        attachLog: true
                    )
                }
            }
        }
    }
}
