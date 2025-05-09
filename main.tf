provider "aws" {
  region = "us-east-1"
}

resource "aws_instance" "jenkins_target" {
  ami                    = "ami-0c7217cdde317cfec" 
  instance_type          = "t2.micro"
  key_name               = aws_key_pair.default.key_name
  associate_public_ip_address = true

  tags = {
    Name = "jenkins-target"
  }
}



resource "aws_key_pair" "default" {
  key_name   = "jenkins"
  public_key = file("~/.ssh/id_rsa.pub")
}
