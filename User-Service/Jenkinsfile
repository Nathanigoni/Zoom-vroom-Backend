pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git url: 'http://github.com/allcodez/calculator.git', branch: 'main'
        }
    }
    stage("Compile"){
        steps{
            sh "./gradlew compileJava"
        }
    }
    stage("Unit test"){
        steps{
            sh "./gradlew test"
        }
    }
}
}
