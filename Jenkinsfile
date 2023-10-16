pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

         stage('Run Unit Tests') {
                    steps {
                        script {
                            // Add the command to run your Spring Boot unit tests here
                            sh 'mvn test' // Example for Maven-based projects

                            // You can adjust the command to match your project's build tool
                        }
                    }
                }
        
        stage('Send Email Notification') {
            steps {
                script {
                    def contenuReadMe = readFile('README.txt')
                    
                    def subject = 'New Project Commit - Mario'
                    def body = "A new commit has been made to the repository..\n\n${contenuReadMe}"
                    def to = 'raedking779@gmail.com'
                    
                    mail(
                        subject: subject,
                        body: body,
                        to: to,
                    )
                }
            }
        }
    }
}
