pipeline {
    agent any
    tools{
        nodejs 'NodeJSInstaller'
    }

    environment {
    DOCKERHUB_CREDENTIALS = credentials('DockerHub')
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
            //
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
                            sh 'mvn clean install -DskipTests' 
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
                        sh 'ng build '      
                    }
                }
            }
        }

        //stage('Deploy to Nexus') {
        //    steps {
        //        dir('DevOps_Project') {
        //            script {
        //                sh 'mvn deploy -DskipTests' 
        //            }
        //        }
        //    }
        //}

        // stage('SonarQube analysis') {
        //     steps {
        //         dir('DevOps_Project') {
        //             script {
        //                 withSonarQubeEnv(installationName: 'MySonarQubeServer') { 
        //                 sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
        //                 }
        //             }
        //         }
        //     }
        // }

        stage('Login Docker') {

            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Build & Push Docker Image (Backend)') {
            steps {
                dir('DevOps_Project') {
                    script {
                        sh 'docker build -t roporos/devops_project .'
                        sh 'docker push roporos/devops_project'
                    }
                }
            }
        }

        stage('Build Docker Image (Frontnd)') {
            steps {
                dir('DevOps_Project_Front') {
                    script {
                        sh 'docker build -t roporos/devops_project_front .'
                        sh 'docker push roporos/devops_project_front'
                        
                    }
                }
            }
        }

        stage('Deploy Front/Back/DB') {
            steps {
                script {
                    sh 'docker-compose -f docker-compose.yml up -d'                        
                }
                
            }
        }

    }
}
