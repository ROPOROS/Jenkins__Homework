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
                sh 'cat README.md | mail -s "Nouveau commit sur le dépôt" omgyeah9@gmail.com'
            }
        }
    }
}
