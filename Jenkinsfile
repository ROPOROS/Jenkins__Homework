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
                sh 'cat README.md | mail -s "Nouveau commit sur le dépôt" raed.chebbi@esprit.tn'
            }
        }
    }
}
