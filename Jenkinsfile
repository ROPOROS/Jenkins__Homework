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
                    
                    // Use the 'echo' command to set the email body
                    echo catCommandOutput
                    
                    emailext(
                        subject: emailSubject,
                        body: catCommandOutput,
                        to: 'raedking779@gmail.com', // Change this to your recipient's email address
                        attachLog: true
                    )
                }
            }
        }
    }
}
