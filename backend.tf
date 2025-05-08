terraform {
  backend "s3" {
    bucket = "vegeta-barbosa-tfstate"
    key    = "jenkins-lab/terraform.tfstate"
    region = "us-east-1"
  }
}
