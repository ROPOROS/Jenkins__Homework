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
                    def emailBody = readFile('README.md') // Read the content of README.txt
                    
                    emailext(
                        subject: emailSubject,
                        body: emailBody,
                        to: 'omgyeah9@gmail.com', // Change this to your recipient's email address
                        attachLog: true,
                    )
                }
            }
        }
    }
    
}
