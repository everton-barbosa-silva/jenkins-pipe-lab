output "instance_ip" {
  value = aws_instance.jenkins_target.public_ip
}