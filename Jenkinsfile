pipeline {
    agent any
    tools{
        nodejs 'NodeJSInstaller'
    }

    stages {
        stage('Checkout GIT') {
            steps {
                echo "Getting Project from Git"
                checkout scm
            }
        }

        stage('Run Unit Tests JUNIT') {
            steps {
                dir('DevOps_Project') {
                    script {
                        sh 'mvn clean test' 
                    }
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Build and Test Backend') {
            steps {
                dir('DevOps_Project') {
                    script {
                        try {
                            sh 'mvn clean install' 
                        } catch (Exception e) {
                            currentBuild.result = 'FAILURE' 
                            error("Build failed: ${e.message}")
                        }
                    }
                }
            }

            post {
                success {
                    script {
                        def subject = "HURRAAYYY"
                        def body = "BUILD GOOD"
                        def to = 'raedking779@gmail.com' 

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

        stage('Build Frontend') {
            steps {
                dir('DevOps_Project_Front') {
                    script {
                        
                        sh 'npm install' 
                        sh 'ng build'      
                    }
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                dir('DevOps_Project') {
                    script {
                        sh 'mvn deploy -DskipTests' 
                    }
                }
            }
        }

        stage('SonarQube analysis') {
            withSonarQubeEnv(installationName: 'MySonarQubeServer') { // You can override the credential to be used
            sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    }
  }
    }
}
