pipeline {
    agent any

    stages {
        stage('Checkout GIT') {
            steps {
                checkout scm
            }
        }

        stage('Run Unit Tests JUNIT') {
            steps {
                script {
                    // Add the command to run your Spring Boot unit tests here
                    sh 'mvn test' // Example for Maven-based projects
                }
            }
        }

        stage('Build and Test Backend') {
            steps {
                dir('DevOps_Project') {
                    script {
                        try {
                            // Use Maven to build the Spring Boot application
                            sh 'mvn clean package' // Replace with your actual Maven build command
                        } catch (Exception e) {
                            currentBuild.result = 'FAILURE' // Mark the build as unstable
                            error("Build failed: ${e.message}")
                        }
                    }
                }
            }
        }

        stage('Build and Test Frontend') {
            steps {
                dir('DevOps_Project_Front') {
                    // Build and test the Angular frontend
                    sh 'npm install'  // Install frontend dependencies
                    sh 'ng build'      // Use the appropriate build command for your Angular project
                }
            }
        }
    }

    post {
        success {
            script {
                def subject = "HURRAAYYY"
                def body = "BUILD GOOD"
                def to = 'raedking779@gmail.com' // Replace with your email address

                mail(
                    subject: subject,
                    body: body,
                    to: to,
                )
            }
        }
        failure {
            script {
                def subject = "Build Failure - ${currentBuild.fullDisplayName}"
                def body = "The build has failed in the Jenkins pipeline. Please investigate and take appropriate action."
                def to = 'raedking779@gmail.com' // Replace with your email address

                mail(
                    subject: subject,
                    body: body,
                    to: to,
                )
            }
        }
    }
}
