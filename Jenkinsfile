pipeline {
    agent any

    tools {
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
                        def subject = "Test and Build Check"
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
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
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

        // stage('Deploy to Nexus') {
        //     steps {
        //         dir('DevOps_Project') {
        //             script {
        //                 sh 'mvn deploy'
        //             }
        //         }
        //     }
        // }

        stage('SonarQube analysis') {
            steps {
                dir('DevOps_Project') {
                    script {
                        withSonarQubeEnv(installationName: 'MySonarQubeServer') {
                            sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
                        }
                    }
                }
            }
        }

        stage('Login Docker') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Build & Push Docker Image (Backend)') {
            steps {
                script {
                    def dockerImage = 'roporos/devops_project'
                    def imageExists = sh(script: "docker inspect --type=image $dockerImage", returnStatus: true) == 0

                    if (!imageExists) {
                        dir('DevOps_Project') {
                            sh "docker build -t $dockerImage ."
                            sh "docker push $dockerImage"
                        }
                    } else {
                        echo "Docker image $dockerImage already exists. Skipping the build and push steps."
                    }
                }
            }
        }

        stage('Build & Push Docker Image (Frontend)') {
            steps {
                script {
                    def dockerImage = 'roporos/devops_project_front'
                    def imageExists = sh(script: "docker inspect --type=image $dockerImage", returnStatus: true) == 0

                    if (!imageExists) {
                        dir('DevOps_Project_Front') {
                            sh "docker build -t $dockerImage ."
                            sh "docker push $dockerImage"
                        }
                    } else {
                        echo "Docker image $dockerImage already exists. Skipping the build and push steps."
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

        stage('Deploy Grafana and Prometheus') {
            steps {
                script {
                    sh 'docker-compose -f docker-compose-prometheus.yml -f docker-compose-grafana.yml up -d'
                }
            }
        }
    }
    
    post {
        always {
            script {
                currentBuild.result = 'Pipeline Completed'
            }
        }

        success {
            script {
                def subject = "Pipeline Successfully Completed - ${currentBuild.fullDisplayName}"
                def body = "The Jenkins pipeline has completed successfully."
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
                def subject = "Pipeline Failed - ${currentBuild.fullDisplayName}"
                def body = "The Jenkins pipeline has failed. Please investigate and take appropriate action."
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
