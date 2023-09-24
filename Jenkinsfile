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
                sh 'cat README.md | sendmail -t -i -f omgyeah9@gmail.com -s "Nouveau commit sur le dépôt" omgyeah9@gmail.com'

            }
        }
    }
}
