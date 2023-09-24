pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

          stage("Create artifacts or make changes") {
            steps {
                sh "git commit -m 'Add testfile from Jenkins Pipeline'"
            }
          }
        stage('Send Email') {
            steps {
                sh 'cat README.md | mail -s "Nouveau commit sur le dépôt" omgyeah9@gmail.com'
            }
        }
    }
}
