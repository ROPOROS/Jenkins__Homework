pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Send Email') {
            mail bcc: '', body: '', cc: 'New Commit', from: '', replyTo: '', subject: 'New Commit', to: 'omgyeah9@gmail.com'
        }
    }
}
