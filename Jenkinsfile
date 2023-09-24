pipeline {
    
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Email Jenkins Pipeline') {
            steps {
                mail bcc: '', body: 'Hello, This is an email from jenkins pipeline.', cc: '', from: '', replyTo: '', subject:​​ 'EmailJenkinsPipeline', to: 'omgyeah9@gmail.com'
            }
        }
    }
}
