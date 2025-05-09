
pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        git url: https://github.com/everton-barbosa-silva/jenkins-pipe-lab

      }
    }

    stage('Terraform Init & Apply') {
      steps {
        dir('infra') {
          sh 'terraform init'
          sh 'terraform apply -auto-approve'
        }
      }
    }

    stage('Pegar IP da EC2') {
      steps {
        script {
          def ip = sh(script: "terraform -chdir=infra output -raw instance_ip", returnStdout: true).trim()
          env.EC2_IP = ip
        }
      }
    }

    stage('Ping com Ansible') {
      steps {
        dir('ansible') {
          writeFile file: 'hosts', text: "[ec2]\n${env.EC2_IP}\n"
          sh 'ansible-playbook -i hosts ping_ec2.yml'
        }
      }
    }
  }
}
