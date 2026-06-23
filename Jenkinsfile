pipeline {
    agent any

    environment {
        APPIUM_IP = "${params.APPIUM_IP ?: '127.0.0.1'}"
        APPIUM_PORT = "${params.APPIUM_PORT ?: '4723'}"
    }

    parameters {
        string(name: 'APPIUM_IP', defaultValue: '127.0.0.1', description: 'Appium Server IP')
        string(name: 'APPIUM_PORT', defaultValue: '4723', description: 'Appium Server Port')
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "📥 Pulling project from Git..."
                checkout scm
            }
        }

        stage('Install Dependencies') {
            steps {
                echo "📦 Installing Maven dependencies..."
                bat "mvn -q -DskipTests clean install"
            }
        }

        stage('Run Tests') {
            steps {
                echo "🚀 Running Appium tests..."
                bat "mvn clean test -Dappium.ip=%APPIUM_IP% -Dappium.port=%APPIUM_PORT%"
            }
        }

        stage('Archive Reports') {
            steps {
                echo "📁 Archiving test reports..."
                archiveArtifacts artifacts: 'reports/*.html', fingerprint: true
            }
        }
    }

    post {
        always {
            echo "🧹 Cleaning up processes..."
            bat 'taskkill /F /IM adb.exe /T || exit 0'
            bat 'taskkill /F /IM node.exe /T || exit 0'
            sleep(time: 5, unit: 'SECONDS')
            echo "🧹 Cleaning workspace..."
            cleanWs()
        }
    }
}